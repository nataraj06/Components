package com.android.components.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.android.components.R;
import com.android.components.intent.IntentDemoActivity;
import com.android.components.services.ServicesDemoActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int REQUEST_CODE_ASK_SMS_PERMISSIONS = 124;
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

        // Read SMS permission
        int smsPermissionCheck = ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.READ_SMS);
        //To check the read sms permission granted
        if (smsPermissionCheck != PackageManager.PERMISSION_GRANTED) {
            // Request dialog and get permission from the user to read sms
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_SMS}, REQUEST_CODE_ASK_SMS_PERMISSIONS);
        }
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
            default:
                break;
        }
    }
}
