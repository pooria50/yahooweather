package com.example.pooria.yahooweatherapi.MyWeatherApp;

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

import cz.msebera.android.httpclient.Header;
import io.nlopez.smartlocation.OnLocationUpdatedListener;
import io.nlopez.smartlocation.SmartLocation;

public class MainMapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Context mConetext = this;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        progressDialog = new ProgressDialog(mConetext);
        progressDialog.setTitle("Please Wait");
        progressDialog.setMessage("Loading...");
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        SmartLocation.with(mConetext).location()
                .oneFix()
                .start(new OnLocationUpdatedListener() {
                    @Override
                    public void onLocationUpdated(Location location) {
                        double latitude_ = location.getLatitude();
                        double longitude_ = location.getLongitude();
                        //PublicMethods.ShowToast(mConetext,latitude+"  ,,,,  "+longitude);
                        //Log.d("t", String.valueOf(latitude));
                        //Log.d("t", String.valueOf(longitude));
                    }
                });
        mMap.setTrafficEnabled(true);
        mMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(mConetext, R.raw.mapstyle));
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.getUiSettings().setCompassEnabled(true);
        mMap.setBuildingsEnabled(true);
        mMap.setOnCameraIdleListener(new GoogleMap.OnCameraIdleListener() {
            @Override
            public void onCameraIdle() {
                double latitude = mMap.getCameraPosition().target.latitude;
                double longitude = mMap.getCameraPosition().target.longitude;
                getWeahter(latitude, longitude);
                //PublicMethods.ShowToast(mConetext, latitude + " " + longitude);
            }
        });
        // Add a marker in Sydney and move the camera
     /*   LatLng sydney = new LatLng(35.730554, 51.437900);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));*/
    }

    void getWeahter(double lat, double lng) {
        String url = "https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%" +
                "20weather.forecast%20where%20woeid%20in%20(SELECT%20woeid%20FROM%20geo.places%" +
                "20WHERE%20text%3D%22("+lat+","+lng+")%22)" +
                "&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys";
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(url, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                PublicMethods.ShowToast(mConetext, throwable.toString());
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
            yahooWeather.getQuery().getResults().getChannel().getItem().getTitle();
            Log.d("weather", String.valueOf(yahooWeather.getQuery().getCount()));
            Log.d("weather", String.valueOf(yahooWeather.getQuery().getResults().getChannel().getItem()));
            Log.d("weather", yahooWeather.getQuery().getResults().getChannel().getLanguage());
            Log.d("weather", yahooWeather.getQuery().getResults().getChannel().getTtl());
            PublicMethods.ShowToast(mConetext, yahooWeather.getQuery().getResults().getChannel().getItem().getTitle() + " " + String.valueOf(yahooWeather.getQuery().getResults().getChannel().getItem()) + " " + yahooWeather.getQuery().getResults().getChannel().getTtl());



        }else {
            PublicMethods.ShowToast(mConetext,"Data Not Found... ");
        }
    }
}
