package com.example.pooria.yahooweatherapi.Retrofit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.pooria.yahooweatherapi.R;

public class SplashPageIpPojoModelActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_page_ip_pojo_model);
        Thread thread = new Thread() {
            public void run() {

                try {
                    // Thread will sleep for 1 seconds
                    sleep(1*1000);
                    // After 1 seconds redirect to another intent
                    Intent i=new Intent(getApplicationContext(),RetrofitActivity.class);
                    startActivity(i);
                    finish();

                } catch (Exception e) {

                }
            }
        };

        // start thread
        thread.start();
    }
}
