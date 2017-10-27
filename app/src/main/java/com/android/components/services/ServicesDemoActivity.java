package com.android.components.services;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.android.components.R;
import com.android.components.utils.UIUtil;

public class ServicesDemoActivity extends AppCompatActivity implements View.OnClickListener {

    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_demo);

        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        (findViewById(R.id.service_start_btn)).setOnClickListener(this);
        (findViewById(R.id.service_stop_btn)).setOnClickListener(this);
        (findViewById(R.id.service_bound_btn)).setOnClickListener(this);
        (findViewById(R.id.service_intent_service_btn)).setOnClickListener(this);
        (findViewById(R.id.service_ipc_btn)).setOnClickListener(this);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.service_start_btn:
                startService(new Intent(ServicesDemoActivity.this, StartedServiceDemo.class));
                break;
            case R.id.service_stop_btn:
                stopService(new Intent(ServicesDemoActivity.this, StartedServiceDemo.class));
                break;
            case R.id.service_bound_btn:
                startActivity(new Intent(ServicesDemoActivity.this, BoundServiceActivityDemo.class));
                break;
            case R.id.service_intent_service_btn:
                //result receiver is used for sending data back from the calling component
                ResultReceiver myResultReceiver = new MyResultReciever(null);
                Intent intent = new Intent(ServicesDemoActivity.this, IntentServiceDemo.class);
                intent.putExtra("sleepTime", 3);
                intent.putExtra("receiver", myResultReceiver);
                startService(intent);
                break;
            case R.id.service_ipc_btn:
                startActivity(new Intent(ServicesDemoActivity.this, MessengerServiceActivityDemo.class));
                break;
            default:
                break;
        }
    }

    //For getting value back from service
    private class MyResultReciever extends ResultReceiver {

        public MyResultReciever(Handler handler) {
            super(handler);
        }

        @Override
        protected void onReceiveResult(int resultCode, Bundle resultData) {
            super.onReceiveResult(resultCode, resultData);
            if (resultCode == 222 && resultData != null) {
                final String resultString = resultData.getString("resultIntentService");
                //we cant update the UI from here, because it is worker thread. So incase of updating a UI we need a handle this
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        UIUtil.showToast(ServicesDemoActivity.this, resultString + "Toast shown from the calling component");
                    }
                });
            }
        }
    }
}
