package com.android.components.threads;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.android.components.R;

import java.io.IOException;
import java.io.InputStream;

/**
 * ThreadActivity.java
 */
public class ThreadDemoActivity extends AppCompatActivity implements View.OnClickListener {
    final private String TAG = ThreadDemoActivity.class.getSimpleName();

    private ProgressBar progressBar;
    private String url = "https://content-static.upwork.com/blog/uploads/sites/3/2016/08/24161340/AsyncTask.png";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread);

        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        progressBar = (ProgressBar) findViewById(R.id.thread_progress_bar);
        (findViewById(R.id.thread_run_on_ui_btn)).setOnClickListener(this);
        (findViewById(R.id.thread_view_post_btn)).setOnClickListener(this);
        (findViewById(R.id.thread_view_post_delay_btn)).setOnClickListener(this);
        (findViewById(R.id.thread_asyc_task_btn)).setOnClickListener(this);
    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.thread_run_on_ui_btn:
                //runOnUiThread();
                break;
            case R.id.thread_view_post_btn:
                //post();
                break;
            case R.id.thread_view_post_delay_btn:
                //postDelay();
                break;
            case R.id.thread_asyc_task_btn:
                new DownloadImageTask((ImageView) findViewById(R.id.thread_image_view))
                        .execute(url);
                break;
            default:
                break;
        }
    }

    private Bitmap loadImageFromNetwork(String url) {
        InputStream inputStream = null;
        try {
            inputStream = new java.net.URL(url).openStream();
            return BitmapFactory.decodeStream(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView imageView;

        private DownloadImageTask(ImageView imageView) {
            this.imageView = imageView;
        }

        @Override
        protected void onPreExecute() {
            progressBar.setVisibility(View.VISIBLE);
        }

        protected Bitmap doInBackground(String... urls) {
            return loadImageFromNetwork(urls[0]);
        }

        protected void onPostExecute(Bitmap result) {
            progressBar.setVisibility(View.GONE);
            imageView.setImageBitmap(result);
        }
    }
}
