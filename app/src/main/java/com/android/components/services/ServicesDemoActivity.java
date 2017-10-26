package com.android.components.services;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.android.components.R;

public class ServicesDemoActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_demo);

        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        (findViewById(R.id.service_start_btn)).setOnClickListener(this);
        (findViewById(R.id.service_stop_btn)).setOnClickListener(this);
        (findViewById(R.id.service_start_bind_btn)).setOnClickListener(this);
        (findViewById(R.id.service_un_bind_btn)).setOnClickListener(this);
        (findViewById(R.id.service_bind_get_value_btn)).setOnClickListener(this);
        (findViewById(R.id.service_intent_service_btn)).setOnClickListener(this);
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
                startService(new Intent(ServicesDemoActivity.this, MyService.class));
                break;
            case R.id.service_stop_btn:
                stopService(new Intent(ServicesDemoActivity.this, MyService.class));
                break;
            case R.id.service_start_bind_btn:
                break;
            case R.id.service_un_bind_btn:
                break;
            case R.id.service_bind_get_value_btn:
                break;
            case R.id.service_intent_service_btn:
                Intent intent = new Intent(ServicesDemoActivity.this, MyIntentService.class);
                intent.putExtra("sleepTime", 5);
                startService(intent);
                break;
            default:
                break;
        }
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
