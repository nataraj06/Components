package com.android.components.intent;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.android.components.R;

import java.util.ArrayList;

public class IntentDemoActivity extends AppCompatActivity implements View.OnClickListener {
    public static final int REQUEST_FOR_RESULT_VALUE = 111;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_demo);

        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        (findViewById(R.id.intent_implicit_intent_btn)).setOnClickListener(this);
        (findViewById(R.id.intent_explicit_intent_btn)).setOnClickListener(this);
        (findViewById(R.id.intent_start_activity_for_result_btn)).setOnClickListener(this);
        (findViewById(R.id.intent_start_share_intent_btn)).setOnClickListener(this);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.intent_implicit_intent_btn:
                Intent implicitIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/nataraj06"));
                startActivity(implicitIntent);
                break;
            case R.id.intent_explicit_intent_btn:
                Intent intent = new Intent(IntentDemoActivity.this, ExplicitIntentDemoActivity.class);
                intent.putExtra("data", "This is Explicit Intent");
                startActivity(intent);
                break;
            case R.id.intent_start_activity_for_result_btn:
                Intent resultIntent = new Intent(IntentDemoActivity.this, IntentResultActivity.class);
                resultIntent.putExtra("data", "Activity for Result");
                startActivityForResult(resultIntent, REQUEST_FOR_RESULT_VALUE);
                break;
            case R.id.intent_start_share_intent_btn:
                sendTextContent();
                //sendBinaryContent();
                //sendMutipleContent();
                break;
            default:
                break;

        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data != null) {

            if (data.hasExtra("result")) {
                Toast.makeText(this, data.getStringExtra("result"), Toast.LENGTH_LONG).show();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void sendTextContent() {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "This is my text to send.");
        sendIntent.setType("text/plain");
        /*resolveHowever, it is possible that there are no applications that can handle your intent.
        In this case, your application will crash when you invoke startActivity().
         To avoid this, before calling startActivity() you should first verify that there is at
        least one application registered in the system that can handle the intent.
         To do this use resolveActivity() on your intent object:*/
        if (sendIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(sendIntent);
        }
    }

    private void sendBinaryContent() {
        //TODO Set image uri
        Uri uriToImage = null;
        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_STREAM, uriToImage);
        shareIntent.setType("image/jpeg");
        /*resolveHowever, it is possible that there are no applications that can handle your intent.
        In this case, your application will crash when you invoke startActivity().
         To avoid this, before calling startActivity() you should first verify that there is at
        least one application registered in the system that can handle the intent.
         To do this use resolveActivity() on your intent object:*/
        if (shareIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(Intent.createChooser(shareIntent, "send to"));
        }
    }

    private void sendMutipleContent() {
        //TODO set multiple image uri
        Uri imageUri1 = null;
        Uri imageUri2 = null;
        ArrayList<Uri> imageUris = new ArrayList<Uri>();
        imageUris.add(imageUri1); // Add your image URIs here
        imageUris.add(imageUri2);

        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND_MULTIPLE);
        shareIntent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, imageUris);
        shareIntent.setType("image/*");
        /*resolveHowever, it is possible that there are no applications that can handle your intent.
        In this case, your application will crash when you invoke startActivity().
         To avoid this, before calling startActivity() you should first verify that there is at
        least one application registered in the system that can handle the intent.
         To do this use resolveActivity() on your intent object:*/
        if (shareIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(Intent.createChooser(shareIntent, "Share images to.."));
        }
    }
}
