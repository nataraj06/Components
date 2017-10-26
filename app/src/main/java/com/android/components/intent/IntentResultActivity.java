package com.android.components.intent;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.android.components.R;


public class IntentResultActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_result);
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final EditText resultEtxt = (EditText) findViewById(R.id.intent_result_name_et);

        findViewById(R.id.intent_result_submit_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(resultEtxt.getText().toString())) {
                    Intent intent = new Intent();
                    intent.putExtra("result", resultEtxt.getText().toString());
                    setResult(IntentDemoActivity.REQUEST_FOR_RESULT_VALUE, intent);
                    finish();//finishing activity
                }
            }
        });
    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
