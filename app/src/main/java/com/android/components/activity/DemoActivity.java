package com.android.components.activity;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.android.components.R;

public class DemoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        showToast("onCreate Called");
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        showToast("onStart Called");
    }

    //This called after onStart
    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        showToast("onPostCreate Called");
    }

    @Override
    protected void onResume() {
        super.onResume();
        showToast("onResume Called");
    }

    @Override
    protected void onPause() {
        super.onPause();
        showToast("onPause Called");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        showToast("onRestart Called");
    }

    @Override
    protected void onStop() {
        super.onStop();
        showToast("onStop Called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        showToast("onDestory Called");
    }

    //This called when the user press the home button
    @Override
    protected void onUserLeaveHint() {
        super.onUserLeaveHint();
        showToast("onUserLeaveHint Called");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        showToast("onBackpressed Called");
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
