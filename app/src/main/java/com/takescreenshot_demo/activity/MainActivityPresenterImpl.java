package com.takescreenshot_demo.activity;

import android.os.CountDownTimer;

public class MainActivityPresenterImpl implements MainActivityPresenter {

    private MainActivityView mView;

    public MainActivityPresenterImpl(MainActivityView view) {
        mView = view;
    }

    @Override
    public void startCounter() {

        new CountDownTimer(120000, 500) {

            public void onTick(long millisecondsUntilFinished) {
                mView.updateCounterText(String.valueOf(millisecondsUntilFinished / 1000));
            }

            public void onFinish() {
            }
        }.start();
    }
}
