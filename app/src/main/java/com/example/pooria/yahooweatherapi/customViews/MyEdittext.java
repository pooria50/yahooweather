package com.example.pooria.yahooweatherapi.customViews;

import android.content.Context;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;

/**
 * Created by pooria on 4/18/18.
 */

public class MyEdittext extends AppCompatEditText {

    public MyEdittext(Context context) {
        super(context);
    }

    public MyEdittext(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public String text(){
        return getText().toString();
    }
}
