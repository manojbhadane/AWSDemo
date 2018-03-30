package com.takescreenshot_demo.utils;

/**
 * Created by mahesh.jadkar on 03-09-2016.
 */
public interface FileUploadCallback {
    void onUploadProgressChanged(long l, long totalSize);

    void onUploadSuccess();

    void onUploadCanceled();

    void onUploadError(Exception exception);
}
