package com.example.pooria.yahooweatherapi.MyWeatherApp;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.telecom.Call;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.pooria.yahooweatherapi.MyWeatherApp.pojo.Forecast;
import com.example.pooria.yahooweatherapi.MyWeatherApp.pojo.RetrofitWeather;
import com.example.pooria.yahooweatherapi.R;
import com.example.pooria.yahooweatherapi.utils.PublicMethods;
import com.example.pooria.yahooweatherapi.weather.pojo.Location;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import io.nlopez.smartlocation.OnLocationUpdatedListener;
import io.nlopez.smartlocation.SmartLocation;
import retrofit2.Callback;
import retrofit2.Response;

public class RetrofitMapsActivity extends FragmentActivity implements OnMapReadyCallback {
    private ImageView inf;
    private GoogleMap mMap;
    //ProgressDialog dialog;
    Context mContext = this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        inf = findViewById(R.id.inf);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        
        inf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        if (!isFinishing()){
                            new AlertDialog.Builder(RetrofitMapsActivity.this)
                                    .setTitle(" Programmer ")
                                    .setMessage(" Mahtab Shohraty ")
                                    .setCancelable(true)
                                    .show();
                        }
                    }
                });
            }
        });

    }

    @SuppressLint("MissingPermission")
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.getUiSettings().setCompassEnabled(true);

        mMap.setMyLocationEnabled(true);

        SmartLocation.with(mContext).location()
                .oneFix()
                .start(new OnLocationUpdatedListener() {
                    @Override
                    public void onLocationUpdated(android.location.Location location) {
                        double lat = location.getLatitude();
                        double lng = location.getLongitude();

                        LatLng myLocation = new LatLng(lat, lng);
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(myLocation, 7));
                    }
                });

        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        if (hour <= 20 && hour >= 7) {
            mMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(this, R.raw.morning));

        } else {

            mMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(this, R.raw.mapstyle));

        }

        mMap.setTrafficEnabled(true);
        LatLng iran = new LatLng(32.4325274, 53.4267969);
        //mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(iran, 5));
        mMap.setOnCameraIdleListener(new GoogleMap.OnCameraIdleListener() {
            @Override
            public void onCameraIdle() {
                double lat = mMap.getCameraPosition().target.latitude;
                double lng = mMap.getCameraPosition().target.longitude;

                String q = "select * from weather.forecast where woeid in (SELECT woeid FROM geo.places WHERE text=\"(" + lat + "," + lng + ")\")and u='c'\n";
                WebServices services = API.getClient().create(WebServices.class);
                retrofit2.Call<RetrofitWeather> weather = services.getWeather(q);
                weather.enqueue(new Callback<RetrofitWeather>() {
                    @Override
                    public void onResponse(retrofit2.Call<RetrofitWeather> call, Response<RetrofitWeather> response) {
                        if (response.isSuccessful()) {
                            if (response.body().getQuery().getCount() == 1) {
                                RetrofitWeather body = response.body();
                                PublicMethods.ShowToast(mContext,
                                        body.getQuery().getResults().getChannel().getLocation().getCountry() + " , " +
                                                body.getQuery().getResults().getChannel()
                                                        .getLocation().getCity() + " , " +
                                                body.getQuery().getResults().getChannel().getItem().getCondition().getTemp() + " ºC" +" , "+
                                        body.getQuery().getResults().getChannel().getItem().getCondition().getText());
                                Log.d("ss", body.getQuery().getResults().getChannel().getItem().getLat() + " " + body.getQuery().getResults().getChannel().getItem().getLong());

                            }
                        }
                    }

                    @Override
                    public void onFailure(retrofit2.Call<RetrofitWeather> call, Throwable t) {
                        PublicMethods.ShowToast(mContext, "Connection Error ...");
                    }
                });
            }
        });

    }
}
