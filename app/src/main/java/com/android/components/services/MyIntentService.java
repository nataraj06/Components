package com.android.components.services;

import android.app.IntentService;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.widget.Toast;


public class MyIntentService extends IntentService {

    private static final String TAG = MyIntentService.class.getSimpleName();

    public MyIntentService() {
        super(MyIntentService.class.getName());
    }

    @Override
    public void onCreate() {
        super.onCreate();
        showToast("MyIntentService::  onCreate()" + "\n " + Thread.currentThread().getName().toString() + " thread");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        showToast("MyIntentService:: onHandleIntent(...)" + "\n " + Thread.currentThread().getName().toString() + " thread");
        int sleepTime = intent.getIntExtra("sleepTime", 1);
        int count = 1;
        //Dummy long running operation
        while (count <= sleepTime) {
            showToast("Counter value is " + count);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            count++;
        }
    }

    //IBinder can be null for intent service android
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // Not necessary to stop implicitly it will stop automatically once the work is done.
        showToast("MyIntentService:: Task completed.IntentService will be destroyed automatically");
        showToast("MyIntentService::  onDestroy()" + "\n " + Thread.currentThread().getName().toString() + " thread");
    }

    private void showToast(final String msg) {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
