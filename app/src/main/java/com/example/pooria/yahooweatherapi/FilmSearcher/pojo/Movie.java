
package com.example.pooria.yahooweatherapi.FilmSearcher.pojo;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Movie {

    @SerializedName("data")
    @Expose
    private List<Datum> data = null;
    @SerializedName("metadata")
    @Expose
    private Metadata metadata;

    private Movie(Builder builder) {
        setData(builder.data);
        setMetadata(builder.metadata);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }

    public Metadata getMetadata() {
        return metadata;
    }

    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }


    public static final class Builder {
        private List<Datum> data;
        private Metadata metadata;

        private Builder() {
        }

        public Builder data(List<Datum> val) {
            data = val;
            return this;
        }

        public Builder metadata(Metadata val) {
            metadata = val;
            return this;
        }


        public Movie build() {
            return new Movie(this);
        }
    }
}
