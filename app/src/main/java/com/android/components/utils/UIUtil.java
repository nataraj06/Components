package com.android.components.utils;


import android.content.Context;
import android.widget.Toast;

/***
 * common class for handling toast messages etc
 */
public class UIUtil {

    public static void showToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}
