package com.android.components.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.android.components.R;
import com.android.components.intent.IntentDemoActivity;
import com.android.components.notification.NotificationActivity;
import com.android.components.services.ServicesDemoActivity;
import com.android.components.threads.ThreadDemoActivity;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    final private String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        (findViewById(R.id.home_activity_btn)).setOnClickListener(this);
        (findViewById(R.id.home_fragment_btn)).setOnClickListener(this);
        (findViewById(R.id.home_intent_btn)).setOnClickListener(this);
        (findViewById(R.id.home_service_btn)).setOnClickListener(this);
        (findViewById(R.id.home_notification_btn)).setOnClickListener(this);
        (findViewById(R.id.home_thread_btn)).setOnClickListener(this);
        (findViewById(R.id.home_data_storage_btn)).setOnClickListener(this);
        (findViewById(R.id.home_networking_btn)).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.home_activity_btn:
                startActivity(new Intent(MainActivity.this, DemoActivity.class));
                break;
            case R.id.home_fragment_btn:
                startActivity(new Intent(MainActivity.this, DemoFragmentActivity.class));
                break;
            case R.id.home_intent_btn:
                startActivity(new Intent(MainActivity.this, IntentDemoActivity.class));
                break;
            case R.id.home_service_btn:
                startActivity(new Intent(MainActivity.this, ServicesDemoActivity.class));
                break;
            case R.id.home_thread_btn:
                startActivity(new Intent(MainActivity.this, ThreadDemoActivity.class));
                break;
            case R.id.home_notification_btn:
                startActivity(new Intent(MainActivity.this, NotificationActivity.class));
                break;
            default:
                break;
        }
    }
}
