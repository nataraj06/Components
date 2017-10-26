package com.android.components.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

import com.android.components.utils.PlaySound;


public class StartedServiceDemo extends Service {

    private PlaySound playSound;

    public StartedServiceDemo() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        showToast("Service:: OnCreate()" + " " + Thread.currentThread().getName().toString());
        playSound = new PlaySound(this);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        showToast("Service:: onStartCommand(...)" + " " + Thread.currentThread().getName().toString());
        playSound.start();
        //By defult android will shut down the components when low memory cases
        //There are some flags to handle this
        //START_STICKY service will automatically restarted and but the intent value become null
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        playSound.stop();
        showToast("Service:: onDestroy()" + " " + Thread.currentThread().getName().toString());
    }

    @Override
    public IBinder onBind(Intent intent) {
        showToast("Service:: onBind(...)" + " " + Thread.currentThread().getName().toString());
        return null;
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
