package com.android.components.services;


import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

public class BoundServiceDemo extends Service {

    //Field variable for MyBinder
    MyBinder myBinder = new MyBinder();

    //To convert this Service class to bound service we need to instance of Binder class
    public class MyBinder extends Binder {
        //MyLocal binder should return the instance of BoundServiceDemo class
        BoundServiceDemo getService() {
            //should return current instance of BoundServiceDemo class
            return BoundServiceDemo.this;
        }
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

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        showToast("Service:: onBind(...)");
        return myBinder;
    }

    public int add(int a, int b) {
        return a + b;
    }

    public int sub(int a, int b) {
        return a - b;
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
