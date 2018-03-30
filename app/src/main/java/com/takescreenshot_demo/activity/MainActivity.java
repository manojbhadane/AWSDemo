package com.takescreenshot_demo.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.simplymadeapps.quickperiodicjobscheduler.PeriodicJob;
import com.simplymadeapps.quickperiodicjobscheduler.QuickJobFinishedCallback;
import com.simplymadeapps.quickperiodicjobscheduler.QuickPeriodicJob;
import com.simplymadeapps.quickperiodicjobscheduler.QuickPeriodicJobCollection;
import com.simplymadeapps.quickperiodicjobscheduler.QuickPeriodicJobScheduler;
import com.takescreenshot_demo.R;
import com.takescreenshot_demo.utils.ScreenshotType;
import com.takescreenshot_demo.utils.ScreenshotUtils;

import java.io.File;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements MainActivityView {

    private TextView hiddenText;
    private ImageView imageView;
    private LinearLayout rootContent;
    private Button fullPageScreenshot;
    private TextView customPageScreenshot;

    private MainActivityPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        implementClickEvents();
    }


    /*
    *
    *
    *
    http://yasirameen.com/2016/10/uploading-file-to-amazon-s3/
    *
    *
    */



    private void findViews() {
        imageView = (ImageView) findViewById(R.id.image_view);
        hiddenText = (TextView) findViewById(R.id.hidden_text);
        rootContent = (LinearLayout) findViewById(R.id.root_content);
        fullPageScreenshot = (Button) findViewById(R.id.full_page_screenshot);
        customPageScreenshot = (TextView) findViewById(R.id.custom_page_screenshot);

        mPresenter = new MainActivityPresenterImpl(this);
        mPresenter.startCounter();
    }

    @Override
    public void updateCounterText(String counter) {
        customPageScreenshot.setText(counter);
    }

    private void implementClickEvents() {

        initJobs();

        QuickPeriodicJobScheduler jobScheduler = new QuickPeriodicJobScheduler(this);
        jobScheduler.start(1, 1000); // Run job with jobId=1 every 60 seconds
    }

    private void showScreenShotImage(Bitmap b) {
        imageView.setImageBitmap(b);
    }

    public void initJobs() {
        int jobId = 1;
        QuickPeriodicJob job = new QuickPeriodicJob(jobId, new PeriodicJob() {
            @Override
            public void execute(QuickJobFinishedCallback callback) {

                Log.e("----", "Job Fired");
                Bitmap bitmap = ScreenshotUtils.getScreenShot(rootContent);
                Date now = new Date();
                String str = android.text.format.DateFormat.format("yyyy_MM_dd_hh:mm:ss", now).toString();
                File saveFile1 = ScreenshotUtils.getMainDirectoryName(MainActivity.this);//get the path to save screenshot
//                ScreenshotUtils.store(bitmap, str + ".jpg", saveFile1);
                ScreenshotUtils.SaveImage(bitmap);

                showScreenShotImage(bitmap);

                // When you have done all your work in the job, call jobFinished to release the resources
                callback.jobFinished();
            }
        });

        QuickPeriodicJobCollection.addJob(job);
    }

    private void re
}
