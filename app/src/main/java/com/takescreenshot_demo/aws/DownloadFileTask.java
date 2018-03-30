package com.takescreenshot_demo.aws;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.Protocol;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.util.Base64;
import com.takescreenshot_demo.R;
import com.takescreenshot_demo.app.Constants;
import com.takescreenshot_demo.utils.FileDownloadCallback;
import com.takescreenshot_demo.utils.SharedPrefClass;
import com.takescreenshot_demo.utils.Util;


import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.ArrayList;

import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by Chirag Chaudhari on 09,May,2017
 */
public class DownloadFileTask extends AsyncTask<String, Void, Exception> {
    private static final int MEGABYTE = 1024 * 1024;
    public static SecretKey yourKey = null;
    private static AmazonS3Client sS3Client;
    private static String algorithm = "AES";
    public File folder;
    public File pdfFile;
    String documentID;
    String BucketName;
    ArrayList<String> docDetails;
    int totalFileCount;
    int mImageDownloadCount;
    /*
  Download Document Status
  1) Downloading=11
  2) Failed=12
  3) Completed=13
* */
    int documentStatus = 11;
    FileDownloadCallback callback;
    private Context mContext;
    private ProgressDialog pDialog;
    private String mUserId;
    private boolean mIsProgressVisible;

    public DownloadFileTask(Context context, String UserID, FileDownloadCallback listener, boolean isVisible) {
        mContext = context;
        mUserId = UserID;
        callback = listener;
        mIsProgressVisible = isVisible;
    }

    public static SecretKey generateKey(char[] passphraseOrPin, byte[] salt)
            throws NoSuchAlgorithmException, InvalidKeySpecException {
        // Number of PBKDF2 hardening rounds to use. Larger values increase
        // computation time. You should select a value that causes computation
        // to take >100ms.
        final int iterations = 1000;

        // Generate a 256-bit key
        final int outputKeyLength = 256;

        SecretKeyFactory secretKeyFactory = SecretKeyFactory
                .getInstance("PBKDF2WithHmacSHA1");
        KeySpec keySpec = new PBEKeySpec(passphraseOrPin, salt, iterations,
                outputKeyLength);
        yourKey = secretKeyFactory.generateSecret(keySpec);
        // get base64 encoded version of the key

        return yourKey;
    }

    public static SecretKey generateSalt() throws NoSuchAlgorithmException {
        // Generate a 256-bit key
        final int outputKeyLength = 256;

        SecureRandom secureRandom = new SecureRandom();
        // Do *not* seed secureRandom! Automatically seeded from system entropy.
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(outputKeyLength, secureRandom);
        SecretKey key = keyGenerator.generateKey();
        return key;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

           /* try {
                if (!MemberDocumentsList.MEMBER_DOCUMENT_LIST_INSTANCE.mDialog.isShowing())
                    MemberDocumentsList.MEMBER_DOCUMENT_LIST_INSTANCE.mDialog.show();
            } catch (Exception e) {
                e.printStackTrace();
            }*/
           if (mIsProgressVisible) {
               pDialog = new ProgressDialog(mContext);
               pDialog.setMessage("");/*Loading...*/
               pDialog.setProgressDrawable(new ColorDrawable(mContext.getResources().getColor(android.R.color.transparent)));
               pDialog.setCancelable(true);
               pDialog.show();
           }
    }

    @Override
    protected Exception doInBackground(String... strings) {

        String fileUrl = mUserId+"/" + strings[0];// Containtas AWS KEY
        this.documentID = strings[0];
        this.BucketName = strings[1];
        this.totalFileCount = Integer.parseInt(strings[2]);
        String fileName = strings[0];
        InputStream inputStream = null;
        this.mImageDownloadCount = Integer.parseInt(strings[3]);


        char[] p = {'p', 'a', 's', 's'};


        try {
            yourKey = generateKey(p, generateSalt().toString()
                    .getBytes());

            SharedPrefClass.getmInstance(mContext).putString(Constants.SHAREDPREF_PROFILE_SECRET_KEY,  Base64.encodeBytes(yourKey.getEncoded()));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }


        try {
            String extStorageDirectory = Environment.getExternalStorageDirectory().toString();
            File folder = new File(extStorageDirectory, ".Inphormd/" + mUserId);
            folder.mkdirs();

            pdfFile = new File(folder, fileName);


            GetObjectRequest putRequest = new GetObjectRequest(
                    BucketName, fileUrl);
            // Turn off HTTPS because our objects are already encrypted
            ClientConfiguration s3Config = new ClientConfiguration();
            s3Config.setProtocol(Protocol.HTTPS);
            s3Config.setConnectionTimeout(s3Config.getSocketTimeout() * 5);
            s3Config.setSocketTimeout(s3Config.getSocketTimeout() * 5);
            sS3Client = new AmazonS3Client(Util.getCredProvider(mContext), s3Config);
            sS3Client.setRegion(Region.getRegion(Regions.AP_SOUTH_1));
            sS3Client.setEndpoint("s3.ap-south-1.amazonaws.com");

            // sS3Client.setEndpoint("s3.ap-northeast-1.amazonaws.com");


            S3Object object = sS3Client.getObject(putRequest);

            inputStream = object.getObjectContent();
            try {
                //pdfFile=encryptFile(yourKey,pdfFile,inputStream);
                int read = 0;
                byte[] data = yourKey.getEncoded();
              /*  String str = new String(data);*/
                // get base64 encoded version of the key

                SecretKeySpec skeySpec = new SecretKeySpec(data, 0, data.length,
                        algorithm);
                Cipher cipher = Cipher.getInstance(algorithm);
                cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
                FileOutputStream encfos = new FileOutputStream(pdfFile);
                CipherOutputStream cos = new CipherOutputStream(encfos, cipher);
                byte[] buffer = new byte[MEGABYTE];
                while ((read = inputStream.read(buffer, 0, MEGABYTE)) != -1) {
                    cos.write(buffer, 0, read);
                }

                cos.close();
                inputStream.close();

                return null;
            } catch (Exception e) {
                e.printStackTrace();
                return e;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return e;

        }


    }

    @Override
    protected void onPostExecute(Exception exception) {

        if (mIsProgressVisible) {
            if (pDialog != null) {
                if (pDialog.isShowing()) {
                    pDialog.dismiss();
                }
            }
        }
        Log.e("file ", "downloaded successfully");
        if (exception == null) {

            callback.onDownloadSuccess();
            Log.e("Upload status", "Upload Success");

        } else {
            callback.onDownloadError(exception);

            Log.e("Upload status", "Upload Errror");
        }

    }
}
