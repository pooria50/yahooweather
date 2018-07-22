package com.example.pooria.yahooweatherapi.Retrofit;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pooria.yahooweatherapi.R;
import com.example.pooria.yahooweatherapi.utils.BaseActivity;
import com.example.pooria.yahooweatherapi.utils.PublicMethods;
import com.example.pooria.yahooweatherapi.weather.pojo.Astronomy;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RetrofitActivity extends BaseActivity {
    private TextView country, city, isp, latitude, longtitude, timezone;
    private Button find;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);
        BindComponents();
        find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Api.getclient().create(WebServices.class).getIP().enqueue(new Callback<IpPojoModel>() {
                    @Override
                    public void onResponse(Call<IpPojoModel> call, Response<IpPojoModel> response) {
                        IpPojoModel body = response.body();
                        PublicMethods.ShowToast(mContext, body.getCountry() + " , " + body.getCity()
                                + " , " + body.getIsp() + " , " + body.getLat() + " , " + body.getQuery() + " , " + body.getLon() + " , " + body.getTimezone());

                        country.setText(body.getCountry().toString());
                        city.setText(body.getCity().toString());
                        isp.setText(body.getIsp().toString());
                        latitude.setText(body.getLat().toString());
                        longtitude.setText(body.getLon().toString());
                        timezone.setText(body.getTimezone().toString());

                    }

                    @Override
                    public void onFailure(Call<IpPojoModel> call, Throwable t) {
                        PublicMethods.ShowToast(mContext, "Connection Failed...");
                    }
                });
            }
        });
    }

    void BindComponents() {
        city = findViewById(R.id.city);
        isp = findViewById(R.id.isp);
        country = findViewById(R.id.country);
        latitude = findViewById(R.id.latitude);
        longtitude = findViewById(R.id.longtitude);
        timezone = findViewById(R.id.timezone);
        find = findViewById(R.id.find);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.Aboutus:
                openDialog();
        }
        return super.onOptionsItemSelected(item);
    }

    void openDialog() {
        final Dialog dialog = new Dialog(RetrofitActivity.this); // Context, this, etc.
        dialog.setContentView(R.layout.activity_aler_dialog_retrofit);
        dialog.setCancelable(true);
        dialog.setTitle("اطلاعات کاربردی ");
        dialog.show();
    }
}
