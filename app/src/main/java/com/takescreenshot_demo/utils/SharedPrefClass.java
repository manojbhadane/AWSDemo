package com.takescreenshot_demo.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by nilesh.patil on 20-12-2016.
 */

public class SharedPrefClass {

    public static SharedPrefClass mInstance = null;
    private SharedPreferences mPref;
    private static final String SETTINGS_NAME = "default_settings";
    private SharedPreferences.Editor mEdit;


    private SharedPrefClass(Context context) {
        mPref = context.getSharedPreferences(SETTINGS_NAME, Context.MODE_PRIVATE);
    }

    public static SharedPrefClass getmInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SharedPrefClass(context);
        }
        return mInstance;
    }

    private void doEdit() {
        mEdit = mPref.edit();
    }

    private void doCommit() {
        mEdit.commit();
    }


    public boolean getBoolean(String key) {
        boolean status;
        status = mPref.getBoolean(key, false);
        return status;
    }


    public void putBoolean(String key, boolean value) {
        doEdit();
        mEdit.putBoolean(key, value);
        doCommit();
    }


    public String getString(String key) {
        String status;
        status = mPref.getString(key, null);
        return status;
    }


    public void putString(String key, String value) {
        doEdit();
        mEdit.putString(key, value);
        doCommit();
    }


    public int getInt(String key) {
        int status=0;
        //doEdit();
        status = mPref.getInt(key, 0);
        //doCommit();
        return status;
    }


    public void putInt(String key, int value) {
        doEdit();
        mEdit.putInt(key, value);
        doCommit();
    }

    public void doClear(String key) {
        doEdit();
        mEdit.remove(key);
        doCommit();
    }


    public void doClearAll() {
        doEdit();
        mEdit.clear();
        doCommit();
    }
}
