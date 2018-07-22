package com.example.pooria.yahooweatherapi.MyWeatherApp;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by pooria on 5/8/18.
 */

public class API {
    public static String base_Url = "https://query.yahooapis.com/v1/public/";
    public static Retrofit retrofit;
    public static Retrofit getClient(){
        if (retrofit == null) {
            retrofit = new Retrofit.Builder().baseUrl(base_Url).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }

}
