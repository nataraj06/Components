package com.android.components.services;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.android.components.R;
import com.android.components.utils.UIUtil;

public class BoundServiceActivityDemo extends AppCompatActivity {

    private boolean isBound = false;
    private BoundServiceDemo boundServiceDemo;

    ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            BoundServiceDemo.MyBinder myBinder = (BoundServiceDemo.MyBinder) iBinder;
            boundServiceDemo = myBinder.getService();
            isBound = true;
            UIUtil.showToast(BoundServiceActivityDemo.this, "Activity:: onServiceConnected(...)");
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            UIUtil.showToast(BoundServiceActivityDemo.this, "Activity:: onServiceDisconnected(...)");
            isBound = false;
        }
    };

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bound_service);
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = new Intent(BoundServiceActivityDemo.this, BoundServiceDemo.class);
        bindService(intent, mConnection, BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (isBound) {
            unbindService(mConnection);
            isBound = false;
        }
    }

    public void onClickEvent(View view) {
        EditText firstInputEtxt = (EditText) findViewById(R.id.activity_bound_service_num_input_one_etxt);
        EditText secondInputEtxt = (EditText) findViewById(R.id.activity_bound_service_num_input_two_etxt);

        int numOne = Integer.valueOf(firstInputEtxt.getText().toString());
        int numTwo = Integer.valueOf(secondInputEtxt.getText().toString());

        String resultString = "";
        if (isBound) {
            switch (view.getId()) {
                case R.id.activity_bound_service_add_btn:
                    resultString = String.valueOf(boundServiceDemo.add(numOne, numTwo));
                    break;

                case R.id.activity_bound_service_sub_btn:
                    resultString = String.valueOf(boundServiceDemo.sub(numOne, numTwo));
                    break;

                default:
                    break;
            }
        }
        UIUtil.showToast(this, "Result is " + resultString);
    }
}
