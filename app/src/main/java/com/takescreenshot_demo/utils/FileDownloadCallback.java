package com.takescreenshot_demo.utils;

/**
 * Created by mahesh.jadkar on 03-09-2016.
 */
public interface FileDownloadCallback {
    void onDownloadProgressChanged(long l, long totalSize);

    void onDownloadSuccess();

    void onDownloadCanceled();

    void onDownloadError(Exception exception);
}
