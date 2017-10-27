package com.android.components.services;


import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.android.components.R;
import com.android.components.utils.UIUtil;

public class MessengerServiceActivityDemo extends AppCompatActivity {

    private boolean mIsBound = false;

    private Messenger mService = null;
    private Messenger mIncomingMessenger = new Messenger(new IncomingHandler());

    private class IncomingHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 444:
                    Bundle bundle = msg.getData();
                    int result = bundle.getInt("result", 0);
                    UIUtil.showToast(MessengerServiceActivityDemo.this, "Result: " + result);
                    break;
                default:
                    break;
            }
        }
    }

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            mIsBound = true;
            mService = new Messenger(iBinder);
            UIUtil.showToast(MessengerServiceActivityDemo.this, "Activity:: onServiceConnected(...)");
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            mIsBound = false;
            UIUtil.showToast(MessengerServiceActivityDemo.this, "Activity:: onServiceDisconnected(...)");
        }
    };

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messenger_service);
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void onClickEvent(View view) throws RemoteException {
        EditText firstInputEtxt = (EditText) findViewById(R.id.activity_messenger_service_num_input_one_etxt);
        EditText secondInputEtxt = (EditText) findViewById(R.id.activity_messenger_service_num_input_two_etxt);

        int numOne = Integer.valueOf(firstInputEtxt.getText().toString());
        int numTwo = Integer.valueOf(secondInputEtxt.getText().toString());

        if (mIsBound) {
            Message messageToService = Message.obtain(null, 333);
            Bundle bundle = new Bundle();
            bundle.putInt("numOne", numOne);
            bundle.putInt("numTwo", numTwo);
            messageToService.setData(bundle);
            messageToService.replyTo = mIncomingMessenger;
            try {
                mService.send(messageToService);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else {
            UIUtil.showToast(this, "Please bind your service to perform action");
        }
    }

    public void performBindService(View view) {
        Intent intent = new Intent(MessengerServiceActivityDemo.this, MessengerServiceDemo.class);
        bindService(intent, mConnection, BIND_AUTO_CREATE);
    }

    public void performUnBindService(View view) {
        if (mIsBound) {
            unbindService(mConnection);
            mIsBound = false;
        }
    }
}
