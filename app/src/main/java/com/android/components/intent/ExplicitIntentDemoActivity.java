package com.android.components.intent;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.android.components.R;

public class ExplicitIntentDemoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explicit_demo);

        Intent receivedIntent = getIntent();
        ((TextView) findViewById(R.id.explicit_subject_txt)).setText(receivedIntent.getExtras().getString("data"));

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
