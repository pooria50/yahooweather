package com.example.pooria.yahooweatherapi.FilmSearcher;

import android.database.Observable;

import com.example.pooria.yahooweatherapi.FilmSearcher.pojo.Datum;
import com.example.pooria.yahooweatherapi.FilmSearcher.pojo.Movie;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by pooria on 5/10/18.
 */

public interface ApiInterface {
    //http://moviesapi.ir/api/v1/movies?q=[QUERY]
    //@GET("movies")
   // Observable<TmpMovies> getMoviesByTitle(@Query("q") String query, @Query("page") Integer page);
    //Call<Datum> getMoviesByTitle(@Query("q") String query);
    //Call<Movie> getMoviesByTitle();

    //http://moviesapi.ir/api/v1/movies/{ID}
    @GET("movies/{id}")
    Call<Movie> getMovieById(@Path("id") Integer id);
}
