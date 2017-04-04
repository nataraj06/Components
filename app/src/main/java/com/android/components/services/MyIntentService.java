package com.android.components.services;

import android.app.IntentService;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;


public class MyIntentService extends IntentService {

    private static final String TAG = MyIntentService.class.getSimpleName();

    public MyIntentService() {
        super(MyIntentService.class.getName());
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        showToast("MyIntentService:: onHandleIntent(...)");

        //TODO Implement the logic to Download file
        showToast("MyIntentService:: Task completed.IntentService will be destroyed automatically");

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // Not necessary to stop implicitly it will stop automatically once the work is done.
        showToast("MyIntentService::  onDestroy()");
    }

    private void showToast(final String msg) {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
            }
        });
    }
}
