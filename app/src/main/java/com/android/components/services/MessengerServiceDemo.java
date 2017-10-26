package com.android.components.services;


import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.widget.Toast;

public class MessengerServiceDemo extends Service {

    private class IncomingHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 333:
                    Bundle bundle = msg.getData();
                    int numOne = bundle.getInt("numOne", 0);
                    int numTwo = bundle.getInt("numTwo", 0);
                    int result = add(numOne, numTwo);

                    Messenger messenger = msg.replyTo;
                    Message msgToActivity = Message.obtain(null, 444);
                    Bundle bundleToActivity = new Bundle();
                    bundleToActivity.putInt("result", result);
                    msgToActivity.setData(bundleToActivity);
                    try {
                        messenger.send(msgToActivity);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    break;
                default:
                    super.handleMessage(msg);
            }
        }
    }

    //To IPC we can't use local binder object
    /* why to need to use handle here??? --- because here we are starting the service from different process example "process1" and "process2"
    so each process will have a default thread associated with it example "thread1" and "thread2". So to handle messages from different threads we
    need to use handler*/
    Messenger mMessenger = new Messenger(new IncomingHandler());

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        showToast("Service:: onbind(...)");
        return mMessenger.getBinder();
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


    public static int add(int a, int b) {
        return a + b;
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
