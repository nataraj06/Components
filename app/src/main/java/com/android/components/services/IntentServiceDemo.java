package com.android.components.services;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.ResultReceiver;
import android.support.annotation.Nullable;
import android.widget.Toast;


public class IntentServiceDemo extends IntentService {

    private static final String TAG = IntentServiceDemo.class.getSimpleName();

    public IntentServiceDemo() {
        super(IntentServiceDemo.class.getName());
    }

    @Override
    public void onCreate() {
        super.onCreate();
        showToast("IntentServiceDemo::  onCreate()" + "\n " + Thread.currentThread().getName().toString() + " thread");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        showToast("IntentServiceDemo:: onHandleIntent(...)" + "\n " + Thread.currentThread().getName().toString() + " thread");
        int sleepTime = intent.getIntExtra("sleepTime", 1);
        ResultReceiver resultReceiver = intent.getParcelableExtra("receiver");
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
        Bundle bundle = new Bundle();
        bundle.putString("resultIntentService", "Counter stopped at " + count + " seconds");
        resultReceiver.send(222, bundle);
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
        showToast("IntentServiceDemo:: Task completed.IntentService will be destroyed automatically");
        showToast("IntentServiceDemo::  onDestroy()" + "\n " + Thread.currentThread().getName().toString() + " thread");
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
