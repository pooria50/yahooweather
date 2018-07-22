package com.example.pooria.yahooweatherapi.FilmSearcher;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by pooria on 5/10/18.
 */

public class ApiClient {
    public static String base_Url = "http://moviesapi.ir/api/v1/";
    public static Retrofit retrofit;

    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(base_Url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
