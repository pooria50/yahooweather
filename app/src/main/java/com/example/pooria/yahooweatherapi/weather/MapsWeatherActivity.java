package com.example.pooria.yahooweatherapi.weather;

import android.app.ProgressDialog;
import android.content.Context;
import android.location.Location;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.pooria.yahooweatherapi.R;
import com.example.pooria.yahooweatherapi.utils.PublicMethods;
import com.example.pooria.yahooweatherapi.weather.pojo.YahooWeather;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.function.Consumer;

import cz.msebera.android.httpclient.Header;
import io.nlopez.smartlocation.OnLocationUpdatedListener;
import io.nlopez.smartlocation.SmartLocation;
import io.nlopez.smartlocation.location.providers.LocationBasedOnActivityProvider;

public class MapsWeatherActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Context mContext = this;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        progressDialog = new ProgressDialog(mContext);
        progressDialog.setTitle("Please Wait");
        progressDialog.setMessage("Loading...");
        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        String strDate = sdf.format(c.getTime());
        Log.d("time", strDate);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;
        SmartLocation.with(mContext).location()
                .oneFix()
                .start(new OnLocationUpdatedListener() {
                    @Override
                    public void onLocationUpdated(Location location) {
                        double latitude = location.getLatitude();
                        double longitude = location.getLongitude();
                        PublicMethods.ShowToast(mContext,latitude+"  ,,  "+longitude);
                        //Log.d("t", String.valueOf(latitude));
                        //Log.d("t", String.valueOf(longitude));
                    }
                });

        mMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(mContext, R.raw.mapstyle));
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.getUiSettings().setCompassEnabled(true);
        mMap.setOnCameraIdleListener(new GoogleMap.OnCameraIdleListener() {
            @Override
            public void onCameraIdle() {
                double lat = mMap.getCameraPosition().target.latitude;
                double lng = mMap.getCameraPosition().target.longitude;
                /*Log.d("s", String.valueOf(lat + lng));
                PublicMethods.ShowToast(mContext, lat + "" + lng);*/
                recieveWeather(lat,lng);
              //  PublicMethods.ShowToast(mContext, "lat"+ lat + ",,,, " + "lan"+ lng);
            }
        });
    }

    void recieveWeather(double lat,double lng) {
         String url = "https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%" +
                "20weather.forecast%20where%20woeid%20in%20(SELECT%20woeid%20FROM%20geo.places%" +
                "20WHERE%20text%3D%22("+lat+","+lng+")%22)" +
                "&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys";
        AsyncHttpClient client = new AsyncHttpClient();
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
        if (yahooWeather.getQuery().getCount() == 1) {
            Log.d("ok", "ok");

            String a = yahooWeather.getQuery().getResults().getChannel().getLanguage();
            Log.d("a", a);
            String location = yahooWeather.getQuery().getResults().getChannel().getLocation().getCountry() +
                    "," +
                    yahooWeather.getQuery().getResults().getChannel().getLocation().getCity();
            PublicMethods.ShowToast(mContext,
                     location+" "+yahooWeather.getQuery().getResults().getChannel().getItem().getCondition().getTemp()+"F" +
            ",,," +a);

        }else {
            PublicMethods.ShowToast(mContext,"Data Not Found... ");
        }
    }
}
