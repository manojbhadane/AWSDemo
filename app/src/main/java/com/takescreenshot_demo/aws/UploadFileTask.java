package com.takescreenshot_demo.aws;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.util.Log;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.event.ProgressEvent;
import com.amazonaws.event.ProgressListener;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

import com.takescreenshot_demo.R;
import com.takescreenshot_demo.app.Constants;
import com.takescreenshot_demo.utils.FileUploadCallback;
import com.takescreenshot_demo.utils.Util;

import java.io.File;


/**
 * Created by mahesh.jadkar on 03-09-2016.
 */
public class UploadFileTask extends AsyncTask<Void, Long, Exception> {


    private AWSCredentials credentialsProvider;
    private String key;
    private boolean publicRead;
    private String bucket;
    private AmazonS3Client sS3Client = null;
    private boolean cancelled = false;
    private long totalSize = 0;
    private long totalTransferred = 0;
    private File file;
    Context mContext;
    private volatile boolean running = true;
    private ProgressDialog pDialog;
    FileUploadCallback callback;
    private boolean mIsProgressDialogVisible, mIsPublicRead;


    public UploadFileTask(String key, File file, Context mcontext, FileUploadCallback listener, boolean isProgressDialogVisible, boolean isPublicRead) {
        this.key = key;
        this.file = file;
        this.mContext = mcontext;
        this.callback = listener;
        mIsProgressDialogVisible = isProgressDialogVisible;
        mIsPublicRead = isPublicRead;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pDialog = new ProgressDialog(mContext);
        pDialog.setMessage("");/*"Loading..."*/
        pDialog.setProgressDrawable(new ColorDrawable(mContext.getResources().getColor(android.R.color.transparent)));
        pDialog.setCancelable(false);
        if (mIsProgressDialogVisible) {
            pDialog.show();
        }

    }

    protected Exception doInBackground(Void... urls) {

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        sS3Client = new AmazonS3Client(Util.getCredProvider(mContext));


        PutObjectRequest putRequest = new PutObjectRequest(
                Constants.BUCKET_NAME, key, file);
        if (mIsPublicRead) {
            putRequest.withCannedAcl(CannedAccessControlList.PublicRead);
        }
// Request server-side encryption.
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setSSEAlgorithm(ObjectMetadata.AES_256_SERVER_SIDE_ENCRYPTION);

        putRequest.setMetadata(objectMetadata);


        sS3Client.setRegion(Region.getRegion(Regions.AP_SOUTH_1));
        sS3Client.setEndpoint("s3.ap-south-1.amazonaws.com");

        putRequest.setGeneralProgressListener(new ProgressListener() {
            @Override
            public void progressChanged(ProgressEvent progressEvent) {
                //Log.i("PROGRESS",progressEvent.getEventCode() + " - " + progressEvent.getBytesTransferred());
                if (progressEvent.getEventCode() == 0) {
                    totalTransferred += progressEvent.getBytesTransferred();
                    publishProgress(totalTransferred);
                } else if (progressEvent.getEventCode() == ProgressEvent.CANCELED_EVENT_CODE) {
                    cancelled = true;
                }

            }
        });

        try {
            sS3Client.putObject(putRequest);

            //Notify success
            return null;
        } catch (Exception e) {
            //Notify error
            return e;
        }


    }

    protected void onProgressUpdate(Long... progress) {

        Util.Log("Progress", "" + progress[0].longValue() + " " + totalSize);

    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
        Log.e("oncancelled", "oncancelled");
        running = false;
    }

    protected void onPostExecute(Exception exception) {
        if (pDialog.isShowing()) {
            pDialog.dismiss();
        }
        if (exception == null) {
            if (!cancelled) {
                callback.onUploadSuccess();
                Log.e("Upload status", "Upload Success");

            } else {
                callback.onUploadCanceled();
                Log.e("Upload status", "Upload cancel");
            }
        } else {
            callback.onUploadError(exception);

            Log.e("Upload status", "Upload Errror");
        }

    }
}