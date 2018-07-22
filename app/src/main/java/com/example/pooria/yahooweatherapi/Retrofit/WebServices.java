package com.example.pooria.yahooweatherapi.Retrofit;

import com.example.pooria.yahooweatherapi.weather.pojo.Astronomy;

import retrofit2.Call;
import retrofit2.http.GET;

public interface WebServices {
    @GET("json")
    Call<IpPojoModel> getIP();

}
