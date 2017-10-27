package com.android.components.activity;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.android.components.R;
import com.android.components.utils.UIUtil;

public class DemoActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        UIUtil.showToast(this, "onCreate Called");
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        UIUtil.showToast(this, "onStart Called");
    }

    //This called after onStart
    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        UIUtil.showToast(this, "onPostCreate Called");
    }

    @Override
    protected void onResume() {
        super.onResume();
        UIUtil.showToast(this, "onResume Called");
    }

    @Override
    protected void onPause() {
        super.onPause();
        UIUtil.showToast(this, "onPause Called");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        UIUtil.showToast(this, "onRestart Called");
    }

    @Override
    protected void onStop() {
        super.onStop();
        UIUtil.showToast(this, "onStop Called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        UIUtil.showToast(this, "onDestory Called");
    }

    //This called when the user press the home button
    @Override
    protected void onUserLeaveHint() {
        super.onUserLeaveHint();
        UIUtil.showToast(this, "onUserLeaveHint Called");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        UIUtil.showToast(this, "onBackpressed Called");
    }
}
