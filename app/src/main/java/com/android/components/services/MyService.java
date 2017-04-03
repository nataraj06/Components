package com.android.components.services;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.widget.Toast;

import com.android.components.utils.PlaySound;


public class MyService extends Service {

    private final IBinder myBinder = new MyBinder();
     String status = "I am My Service";
    private PlaySound playSound;

    public MyService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        showToast("Service:: OnCreate()");
        playSound = new PlaySound(this);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        showToast("Service:: onStartCommand(...)");
        playSound.start();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        playSound.stop();
        showToast("Service:: onDestroy()");
    }

    @Override
    public IBinder onBind(Intent intent) {
        showToast("Service:: onBind(...)");
        return myBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        showToast("Service:: onUnbind(...)");
        return super.onUnbind(intent);
    }

    @Override
    public void onRebind(Intent intent) {
        showToast("Service:: onRebind(...)");
        super.onRebind(intent);
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    public class MyBinder extends Binder {
        MyService getService() {
            return MyService.this;
        }

    }
}
