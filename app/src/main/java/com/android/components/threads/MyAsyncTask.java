
package com.android.components.threads;

import android.os.AsyncTask;

/**
 * AsyncTask's generic types
 * The three types used by an asynchronous task are the following:
 * <p/>
 * Params, the type of the parameters sent to the task upon execution.
 * Progress, the type of the progress units published during the background computation.
 * Result, the type of the result of the background computation.
 * Not all types are always used by an asynchronous task. To mark a type as unused, simply use the type Void:
 */
public class MyAsyncTask extends AsyncTask<String, Integer, String> {

    /**
     * onPreExecute(), invoked on the UI thread before the task is executed.
     * This step is normally used to setup the task, for instance by showing a progress bar in the user interface.
     */
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    /**
     * doInBackground(Params...), invoked on the background thread immediately after onPreExecute() finishes executing.
     * This step is used to perform background computation that can take a long time. The parameters of the asynchronous task are passed to this step.
     * The result of the computation must be returned by this step and will be passed back to the last step.
     * This step can also use publishProgress(Progress...) to publish one or more units of progress.
     * These values are published on the UI thread, in the onProgressUpdate(Progress...) step.
     */
    @Override
    protected String doInBackground(String... params) {
        return null;
    }


    /**
     * onProgressUpdate(Progress...), invoked on the UI thread after a call to publishProgress(Progress...).
     * The timing of the execution is undefined.
     * This method is used to display any form of progress in the user interface while the background computation is still executing.
     * For instance, it can be used to animate a progress bar or show logs in a text field.
     *
     */
    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }

    /**
     * onPostExecute(Result), invoked on the UI thread after the background computation finishes.
     * The result of the background computation is passed to this step as a parameter.
     *
     */
    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }

}
