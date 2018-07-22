package com.example.pooria.yahooweatherapi.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by pooria on 4/18/18.
 */

public class PublicMethods {
    public static void ShowToast(Context mContext, String msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }
}
