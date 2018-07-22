package com.example.pooria.yahooweatherapi.utils;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by pooria on 4/18/18.
 */

public class BaseActivity extends AppCompatActivity {
    public Context mContext;
    public Activity mActivity;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        mActivity = this;
    }
}
