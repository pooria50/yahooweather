
package com.example.pooria.yahooweatherapi.weather.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class YahooWeather {

    @SerializedName("query")
    @Expose
    private Query query;

    public Query getQuery() {
        return query;
    }

    public void setQuery(Query query) {
        this.query = query;
    }

}
