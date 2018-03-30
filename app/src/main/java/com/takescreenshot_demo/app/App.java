package com.takescreenshot_demo.app;

import android.app.Application;

import com.simplymadeapps.quickperiodicjobscheduler.PeriodicJob;
import com.simplymadeapps.quickperiodicjobscheduler.QuickJobFinishedCallback;
import com.simplymadeapps.quickperiodicjobscheduler.QuickPeriodicJob;
import com.simplymadeapps.quickperiodicjobscheduler.QuickPeriodicJobCollection;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        initJobs();
    }

    public void initJobs() {
//        int jobId = 1;
//        QuickPeriodicJob job = new QuickPeriodicJob(jobId, new PeriodicJob() {
//            @Override
//            public void execute(QuickJobFinishedCallback callback) {
//                SomeJobClass.performJob();
//
//                // When you have done all your work in the job, call jobFinished to release the resources
//                callback.jobFinished();
//            }
//        });
//
//        QuickPeriodicJobCollection.addJob(job);
    }

    public static class SomeJobClass {
        public static void performJob() {
            System.out.println("Job Fired");
        }
    }
}
