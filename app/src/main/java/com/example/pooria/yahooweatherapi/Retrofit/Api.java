package com.example.pooria.yahooweatherapi.Retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by pooria on 4/24/18.
 */

public class Api {
    public static String base_url = "http://ip-api.com/";

    private static Retrofit retrofit;
    public static Retrofit getclient(){
        if (retrofit == null) {
            retrofit = new Retrofit.Builder().baseUrl(base_url).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }
}
