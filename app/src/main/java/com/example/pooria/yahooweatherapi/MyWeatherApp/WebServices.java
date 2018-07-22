package com.example.pooria.yahooweatherapi.MyWeatherApp;

import com.example.pooria.yahooweatherapi.MyWeatherApp.pojo.RetrofitWeather;
import com.example.pooria.yahooweatherapi.weather.pojo.Astronomy;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by pooria on 5/8/18.
 */

public interface WebServices {
    @GET("yql?format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys")
    Call<RetrofitWeather> getWeather(@Query("q") String q) ;
}
