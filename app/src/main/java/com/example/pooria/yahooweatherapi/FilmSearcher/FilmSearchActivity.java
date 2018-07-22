package com.example.pooria.yahooweatherapi.FilmSearcher;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pooria.yahooweatherapi.FilmSearcher.pojo.Datum;
import com.example.pooria.yahooweatherapi.FilmSearcher.pojo.Movie;
import com.example.pooria.yahooweatherapi.R;
import com.example.pooria.yahooweatherapi.utils.BaseActivity;
import com.example.pooria.yahooweatherapi.utils.PublicMethods;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FilmSearchActivity extends BaseActivity {
    private EditText search;
    private Button find;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film_search);
        bind();
        find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                get_date();
            }
        });
    }

    void get_date(){
        //String text = search.getText().toString();
        //final int n = Integer.parseInt(search.getText().toString());
        ApiClient.getClient().create(ApiInterface.class).getMovieById(3).enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                Movie body = response.body();

                Toast.makeText(mContext, body.getData().get(3).getTitle(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {

            }
        });
    }

    void bind(){
        search = findViewById(R.id.search);
        find = findViewById(R.id.find);
    }
}
