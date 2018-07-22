package com.example.pooria.yahooweatherapi.weather;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.example.pooria.yahooweatherapi.R;
import com.example.pooria.yahooweatherapi.customViews.MyEdittext;
import com.example.pooria.yahooweatherapi.utils.BaseActivity;
import com.example.pooria.yahooweatherapi.utils.PublicMethods;
import com.example.pooria.yahooweatherapi.weather.pojo.YahooWeather;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;

import cz.msebera.android.httpclient.Header;

public class WeatherActivityForm extends BaseActivity implements View.OnClickListener {
    private MyEdittext cityname;
    private TextView result;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_form);
        Bind();
    }

    void getWeather(String city) {
        AsyncHttpClient client = new AsyncHttpClient();
        String url = "https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20weather.forecast%20where%20woeid%20in%20(select%20woeid%20from%20geo.places(1)%20where%20text%3D%22" + city + "%2C%20ir%22)&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys";
        client.get(url, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                PublicMethods.ShowToast(mContext, throwable.toString());
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                parseResponse(responseString);
            }

            @Override
            public void onStart() {
                super.onStart();
                progressDialog.show();
            }

            @Override
            public void onFinish() {
                super.onFinish();
                progressDialog.dismiss();
            }
        });
    }

    void parseResponse(String response) {
        Gson gson = new Gson();
        YahooWeather yahooWeather = gson.fromJson(response, YahooWeather.class);
        result.setText(cityname.text() + " " +
                yahooWeather.getQuery().getResults().getChannel().getItem().getCondition().getTemp() + " F ");

    }
    void Bind(){
        cityname = findViewById(R.id.cityName);
        result = findViewById(R.id.result);
        findViewById(R.id.show).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view.getId() == R.id.show) {
                    getWeather(cityname.text());
                }
            }
        });
        progressDialog = new ProgressDialog(mContext);
        progressDialog.setTitle("Please Wait");
        progressDialog.setMessage("Loading...");

    }
    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.show) {
            getWeather(cityname.text());
        }
    }
}
