package com.takescreenshot_demo.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.StrictMode;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferObserver;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferUtility;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.takescreenshot_demo.app.Constants;
import com.takescreenshot_demo.aws.DeveloperAuthenticationProvider;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.net.NetworkInterface;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.UUID;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import static android.widget.Toast.LENGTH_LONG;

/**
 * Created by nilesh.patil on 24-02-2017.
 */

public class Util {
    public static String APP_NAME = "Inphormed";
    // We only need one instance of the clients and credentials provider
    private static AmazonS3Client sS3Client;
    private static CognitoCachingCredentialsProvider sCredProvider;
    private static TransferUtility sTransferUtility;
    public Activity mContext;

    public Util(Activity lactivity) {
        mContext = lactivity;
    }

    public static void showSoftKeyBoard(Activity activity) {
        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputMethodManager != null) {
            inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
        }
    }

    public static void hideSoftKeyboard(Activity activity) {

        try {
            InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void hideKeyboardFromFragment(Context mContext, View view) {
        if (((Activity) mContext).getCurrentFocus() != null && ((Activity) mContext).getCurrentFocus() instanceof EditText) {
            InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public static void hideKeyboardFromDialog(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    // to set font for activity
    public static void setFont(ViewGroup group, Typeface lTypeface) {
        // TODO function to set font for entire layout
        int count = group.getChildCount();
        View v;
        for (int i = 0; i < count; i++) {
            v = group.getChildAt(i);
            if (v instanceof TextView) {
                ((TextView) v).setTypeface(lTypeface);
            } else if (v instanceof EditText) {
                ((EditText) v).setTypeface(lTypeface);
            } else if (v instanceof Button) {
                ((Button) v).setTypeface(lTypeface);
            } else if (v instanceof RadioButton) {
                ((RadioButton) v).setTypeface(lTypeface);
            } else if (v instanceof CheckBox) {
                ((CheckBox) v).setTypeface(lTypeface);
            }
        }
    }

    /*
    get device width
     */

    public static void setdropdownverticaloffset(final Spinner spinner) {

        spinner.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                spinner.setDropDownVerticalOffset(
                        spinner.getDropDownVerticalOffset() + spinner.getHeight());
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    spinner.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                } else {
                    spinner.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                }
            }
        });

    }

    public static void setdropdownverticaloffset(final AutoCompleteTextView spinner) {

        spinner.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                spinner.setDropDownVerticalOffset(
                        spinner.getDropDownVerticalOffset() + spinner.getHeight());
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    spinner.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                } else {
                    spinner.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                }
            }
        });

    }

    public static int getMinWidth(Context context) {
        int mwidth = context.getResources().getDisplayMetrics().widthPixels / 2;
        return mwidth;
    }

    public static String covertDate(String startDate) {
        SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat format2 = new SimpleDateFormat("dd MMM yyyy");
        Date date = null;
        try {
            date = format1.parse(startDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return format2.format(date);
    }

    public static String addMetricsDateFormat(String startDate) {
        SimpleDateFormat format1 = new SimpleDateFormat("dd MMM yyyy");
        SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = format1.parse(startDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return format2.format(date);
    }

    public static String GetTimeFromTimeWithAMPM(String dt) {
        try {
            SimpleDateFormat inFormat = new SimpleDateFormat("hh:mm a");
            Date date = inFormat.parse(dt);
            SimpleDateFormat outFormat = new SimpleDateFormat("HH:mm:ss");
            String goal = outFormat.format(date);
            return goal;
        } catch (ParseException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String GetAMPMFromTime(String dt) {
        try {
            SimpleDateFormat inFormat = new SimpleDateFormat("HH:mm:ss");
            Date date = inFormat.parse(dt);
            SimpleDateFormat outFormat = new SimpleDateFormat("hh:mm a");
            String goal = outFormat.format(date);
            return goal;
        } catch (ParseException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String covertCreatedDate(String startDate) {
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat format2 = new SimpleDateFormat("dd MMM, yyyy hh:mm a");
        Date date = null;
        try {
            date = format1.parse(startDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return format2.format(date);
    }

    public static String formateDateFromstring(String inputFormat, String outputFormat, String inputDate) {

        Date parsed = null;
        String outputDate = "";

        SimpleDateFormat df_input = new SimpleDateFormat(inputFormat, java.util.Locale.getDefault());
        SimpleDateFormat df_output = new SimpleDateFormat(outputFormat, java.util.Locale.getDefault());

        try {
            parsed = df_input.parse(inputDate);
            outputDate = df_output.format(parsed);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return outputDate;

    }

    public static final int getColor(Context context, int id) {
        final int version = Build.VERSION.SDK_INT;
        if (version >= 23) {
            return ContextCompat.getColor(context, id);
        } else {
            return context.getResources().getColor(id);
        }
    }

    /**
     * For logging messages in APP with tag and message
     *
     * @param msg
     */
    public static void Log(String msg) {

        Log.e(APP_NAME, msg);
    }

    /**
     * For logging messages in APP with tag and message
     *
     * @param tag
     * @param msg
     */
    public static void Log(String tag, String msg) {
        Log.e(APP_NAME, msg);
        Log.e(tag, msg);
    }

    /**
     * for making toast message
     *
     * @param context
     * @param msg
     */
    public static void makeToast(Context context, String msg) {
        // Lounch activity
        Toast.makeText(context, "" + msg, Toast.LENGTH_SHORT).show();

    }

    /**
     * Function to display toast with duration
     *
     * @param context
     * @param msg
     * @param TOAST_LENGHT
     */
    public static void makeToast(Context context, String msg, int TOAST_LENGHT) {
        if (TOAST_LENGHT == LENGTH_LONG) {
            //	Toast.makeText(context,""+msg ,Toast.LENGTH_LONG).show();
        } else {
            //	Toast.makeText(context,""+msg ,Toast.LENGTH_SHORT).show();
        }
    }

    //get age from dob
    public static int calculateAge(Date birthdate) {
        Calendar birth = Calendar.getInstance();
        birth.setTime(birthdate);
        Calendar today = Calendar.getInstance();

        int yearDifference = today.get(Calendar.YEAR)
                - birth.get(Calendar.YEAR);

        if (today.get(Calendar.MONTH) < birth.get(Calendar.MONTH)) {
            yearDifference--;
        } else {
            if (today.get(Calendar.MONTH) == birth.get(Calendar.MONTH)
                    && today.get(Calendar.DAY_OF_MONTH) < birth
                    .get(Calendar.DAY_OF_MONTH)) {
                yearDifference--;
            }

        }

        return yearDifference;
    }

    /**
     * Gets an instance of CognitoCachingCredentialsProvider which is
     * constructed using the given Context.
     *
     * @param context An Context instance.
     * @return A default credential provider.
     */
    public static CognitoCachingCredentialsProvider getCredProvider(Context context) {
        if (sCredProvider == null) {
            DeveloperAuthenticationProvider developerProvider = new DeveloperAuthenticationProvider("861444452953", SharedPrefClass.getmInstance(context).getString(Constants.SHAREDPREF_IDENTITYID), context, Regions.AP_SOUTH_1);
            sCredProvider = new CognitoCachingCredentialsProvider(
                    context.getApplicationContext(),
                    developerProvider,
                    Regions.AP_SOUTH_1);


        }
        return sCredProvider;
    }

    /**
     * Gets an instance of a S3 client which is constructed using the given
     * Context.
     *
     * @param context An Context instance.
     * @return A default S3 client.
     */
    public static AmazonS3Client getsS3ClientEncypr(Context context, String bucketName, String key, File file) {
        if (Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        sS3Client = new AmazonS3Client(getCredProvider(context.getApplicationContext()));
        /*PutObjectRequest request = new PutObjectRequest(bucketName, key, file);

// Request server-side encryption.
        ObjectMetadata objectMetadata = new ObjectMetadata();

        objectMetadata.setServerSideEncryption(
                ObjectMetadata.AES_256_SERVER_SIDE_ENCRYPTION);
        request.setMetadata(objectMetadata);*/
        PutObjectRequest putRequest = new PutObjectRequest(
                bucketName, key, file);
// Request server-side encryption.
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setSSEAlgorithm(ObjectMetadata.AES_256_SERVER_SIDE_ENCRYPTION);

        putRequest.setMetadata(objectMetadata);


        sS3Client.setRegion(Region.getRegion(Regions.AP_SOUTH_1));
        sS3Client.setEndpoint("s3.ap-south-1.amazonaws.com");

        // sS3Client.setEndpoint("s3.ap-northeast-1.amazonaws.com");
        PutObjectResult response = sS3Client.putObject(putRequest);


        return sS3Client;
    }

    public static AmazonS3Client getS3Client(Context context) {
        if (sS3Client == null) {
            sS3Client = new AmazonS3Client(getCredProvider(context.getApplicationContext()));

            sS3Client.setEndpoint("s3.ap-south-1.amazonaws.com");
           /* new Thread(new Runnable() {
                @Override
                public void run() {
                    sS3Client.setObjectAcl(Constants.BUCKET_NAME, Fragment_singale_page_uploade2.KEY_NAME, CannedAccessControlList.PublicReadWrite);
                }
            });
*/
        }
        return sS3Client;
    }

    /**
     * Gets an instance of the TransferUtility which is constructed using the
     * given Context
     *
     * @param context
     * @return a TransferUtility instance
     */
    public static TransferUtility getTransferUtility1(Context context) {
        if (sTransferUtility == null) {
            sTransferUtility = new TransferUtility(getS3Client(context.getApplicationContext()),
                    context.getApplicationContext());

        }

        return sTransferUtility;
    }

    public static TransferUtility getTransferUtility(Context context, String bucketName, String key, File file) {
        if (sTransferUtility == null) {
            sTransferUtility = new TransferUtility(getsS3ClientEncypr(context.getApplicationContext(), bucketName, key, file),
                    context.getApplicationContext());

        }

        return sTransferUtility;
    }

    /**
     * Converts number of bytes into proper scale.
     *
     * @param bytes number of bytes to be converted.
     * @return A string that represents the bytes in a proper scale.
     */
    public static String getBytesString(long bytes) {
        String[] quantifiers = new String[]{
                "KB", "MB", "GB", "TB"
        };
        double speedNum = bytes;
        for (int i = 0; ; i++) {
            if (i >= quantifiers.length) {
                return "";
            }
            speedNum /= 1024;
            if (speedNum < 512) {
                return String.format("%.2f", speedNum) + " " + quantifiers[i];
            }
        }
    }

    /**
     * Copies the data from the passed in Uri, to a new file for use with the
     * Transfer Service
     *
     * @param context
     * @param uri
     * @return
     * @throws IOException
     */
    public static File copyContentUriToFile(Context context, Uri uri) throws IOException {
        InputStream is = context.getContentResolver().openInputStream(uri);
        File copiedData = new File(context.getDir("SampleImagesDir", Context.MODE_PRIVATE), UUID
                .randomUUID().toString());
        copiedData.createNewFile();

        FileOutputStream fos = new FileOutputStream(copiedData);
        byte[] buf = new byte[2046];
        int read = -1;
        while ((read = is.read(buf)) != -1) {
            fos.write(buf, 0, read);
        }

        fos.flush();
        fos.close();

        return copiedData;
    }

    /*
     * Fills in the map with information in the observer so that it can be used
     * with a SimpleAdapter to populate the UI
     */
    public static void fillMap(Map<String, Object> map, TransferObserver observer, boolean isChecked) {
        int progress = (int) ((double) observer.getBytesTransferred() * 100 / observer
                .getBytesTotal());
        map.put("id", observer.getId());
        map.put("checked", isChecked);
        map.put("fileName", observer.getAbsoluteFilePath());
        map.put("progress", progress);
        map.put("bytes",
                getBytesString(observer.getBytesTransferred()) + "/"
                        + getBytesString(observer.getBytesTotal()));
        map.put("state", observer.getState());
        map.put("percentage", progress + "%");
    }

    public static String getMacAddress(Context context) {
        if (Build.VERSION.SDK_INT < 23) {
            WifiManager m_wm = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
            String m_wlanMacAdd = m_wm.getConnectionInfo().getMacAddress();
            return m_wlanMacAdd;
        } else {
            try {
                List<NetworkInterface> all = Collections.list(NetworkInterface.getNetworkInterfaces());
                for (NetworkInterface nif : all) {
                    if (!nif.getName().equalsIgnoreCase("wlan0")) continue;

                    byte[] macBytes = nif.getHardwareAddress();
                    if (macBytes == null) {
                        return "";
                    }

                    StringBuilder res1 = new StringBuilder();
                    for (byte b : macBytes) {
                        res1.append(Integer.toHexString(b & 0xFF) + ":");
                    }

                    if (res1.length() > 0) {
                        res1.deleteCharAt(res1.length() - 1);
                    }
                    return res1.toString();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return "02:00:00:00:00:00";

        }
    }

    public static boolean isVideo(String FileName) {
        boolean flag;

        if (FileName.contains(".mp4") || FileName.contains(".MP4") || FileName.contains(".MOV") || FileName.contains(".mov") || FileName.contains(".3gp") || FileName.contains(".3GP") || FileName.contains(".AVI") || FileName.contains(".avi") || FileName.contains(".WMV") || FileName.contains(".wmv") || FileName.contains(".MKV") || FileName.contains(".mkv") || FileName.contains(".GIF") || FileName.contains(".gif") || FileName.contains(".VOB") || FileName.contains(".vob") || FileName.contains(".FLV") || FileName.contains(".flv")) {

            flag = true;
        } else {
            flag = false;
        }


        return flag;
    }

    public static SecretKey generateKey(char[] passphraseOrPin, byte[] salt)
            throws NoSuchAlgorithmException, InvalidKeySpecException {
        // Number of PBKDF2 hardening rounds to use. Larger values increase
        // computation time. You should select a value that causes computation
        // to take >100ms.

        SecretKey yourKey = null;
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

    public static String dashDateFormat(String startDate) {

        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat format2 = new SimpleDateFormat("dd MMM yyyy");
        Date date = null;
        try {
            date = format1.parse(startDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return format2.format(date);
    }

    public static String dashDoctorVisitDateFormat(String startDate) {

        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat format2 = new SimpleDateFormat("dd MMM");
        Date date = null;
        try {
            date = format1.parse(startDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return format2.format(date);
    }

    public static String paddingRemove(int input) {
        String str = "";
        if (input >= 10) {
            str = Integer.toString(input);
        } else {
            str = Integer.toString(input).replaceAll("0", "");
        }
        return str;
    }

    public static String getCurrentTimeStamp() {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            return dateFormat.format(new Date());
        } catch (Exception e) {
            e.printStackTrace();

            return null;
        }
    }

    public static String getCurrentDate() {
        try {

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy");

            return dateFormat.format(new Date());
        } catch (Exception e) {
            e.printStackTrace();

            return null;
        }
    }

    public static String convertDtmToDate(String datemillis) {

        String shortTimeStr = null;
        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = null;
            date = df.parse(datemillis);
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
            shortTimeStr = sdf.format(date);

        } catch (ParseException e) {
            // To change body of catch statement use File | Settings | File Templates.
            e.printStackTrace();
        }
        return shortTimeStr;
    }

    public static String convertDtmToOnlyDate(String datemillis) {

        String shortTimeStr = null;
        try {
            SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
            Date date;
            date = df.parse(datemillis);
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM");
            shortTimeStr = sdf.format(date);

        } catch (ParseException e) {
            // To change body of catch statement use File | Settings | File Templates.
            e.printStackTrace();
        }
        return shortTimeStr;
    }



    public static String treatmentDateFormat(String startDate) {

        SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = format1.parse(startDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return format2.format(date);
    }

    public static Bitmap BITMAP_RESIZER(Bitmap bitmap, int newWidth, int newHeight) {

        int width = bitmap.getWidth();
        int height = bitmap.getHeight();

        /*Log.v("Pictures", "Width and height are " + width + "--" + height);*/

        if (width > height) {
            // landscape
            float ratio = (float) width / newWidth;
            width = newWidth;
            height = (int) (height / ratio);
        } else if (height > width) {
            // portrait
            float ratio = (float) height / newHeight;
            height = newHeight;
            width = (int) (width / ratio);
        } else {
            // square
            height = newHeight;
            width = newWidth;
        }

        Bitmap scaledBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);


        float ratioX = width / (float) bitmap.getWidth();
        float ratioY = height / (float) bitmap.getHeight();
      /* float ratioX = newWidth / (float) bitmap.getWidth();
       float ratioY = newHeight / (float) bitmap.getHeight();*/
        float middleX = width / 2.0f;
        float middleY = height / 2.0f;

        Matrix scaleMatrix = new Matrix();
        scaleMatrix.setScale(ratioX, ratioY, middleX, middleY);

        Canvas canvas = new Canvas(scaledBitmap);
        canvas.setMatrix(scaleMatrix);
        canvas.drawBitmap(bitmap, middleX - bitmap.getWidth() / 2, middleY - bitmap.getHeight() / 2, new Paint(Paint.FILTER_BITMAP_FLAG));
        //canvas.drawBitmap(bitmap, 0, 0, new Paint(Paint.FILTER_BITMAP_FLAG));

        return scaledBitmap;

    }

    public static void setHeightToDropDown(Spinner lSpinner) {
        try {
            Field popup = Spinner.class.getDeclaredField("mPopup");
            popup.setAccessible(true);

            // Get private mPopup member variable and try cast to ListPopupWindow
            android.widget.ListPopupWindow popupWindow = (android.widget.ListPopupWindow) popup.get(lSpinner);

            // Set popupWindow height to 500px
            popupWindow.setHeight(380);
        } catch (NoClassDefFoundError | ClassCastException | NoSuchFieldException | IllegalAccessException e) {
            // silently fail...
        }
    }

    public static void deleteFolder(File file) {

        Log.d("DeleteRecursive", "DELETEPREVIOUS TOP" + file.getPath());
        if (file.isDirectory()) {
            String[] children = file.list();
            for (String aChildren : children) {
                File temp = new File(file, aChildren);
                if (temp.isDirectory()) {
                    Log.d("DeleteRecursive", "Recursive Call" + temp.getPath());
                    deleteFolder(temp);
                } else {
                    Log.d("DeleteRecursive", "Delete File" + temp.getPath());
                    boolean b = temp.delete();
                    if (!b) {
                        Log.d("DeleteRecursive", "DELETE FAIL");
                    }
                }
            }


        }
        file.delete();

    }

    public static String ConvetUTCToLocalTime(String ls) {
        // ls="2017-07-18T17:34:00.129Z";

        Date date = null;
        String localSteing = "";

        SimpleDateFormat utcFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SS'Z'");

        utcFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        try {
            date = utcFormat.parse(ls);
            SimpleDateFormat pstFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            pstFormat.setTimeZone(TimeZone.getDefault());
            localSteing = pstFormat.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return localSteing;

    }


/*    public static String ConvetUTCToLocalTime(String ls) {
        //  SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

        Date date = null;
        String localSteing = "";
       ls="2017-07-18'T'17:00:26.SSS'Z'";
       // SimpleDateFormat utcFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        SimpleDateFormat utcFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

        utcFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        try {
            date = utcFormat.parse(ls);
            SimpleDateFormat pstFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            pstFormat.setTimeZone(TimeZone.getDefault());
            Util.Log("Time zone",""+TimeZone.getDefault());
            localSteing = pstFormat.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }

        ///return date.getTime();
        return localSteing;

    }*/



    public static String getCurrentDateForLoginUser() {
        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        return date;
    }


    public static String getCurrentUTC() {
        Date time = Calendar.getInstance().getTime();
        SimpleDateFormat outputFmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        outputFmt.setTimeZone(TimeZone.getTimeZone("UTC"));
        return outputFmt.format(time);
    }


    public static String convertDtmToOnlyDate1(String datemillis) {

        String shortTimeStr = null;
        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date;
            date = df.parse(datemillis);
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM");
            shortTimeStr = sdf.format(date);

        } catch (ParseException e) {
            // To change body of catch statement use File | Settings | File Templates.
            e.printStackTrace();
        }
        return shortTimeStr;
    }
}

