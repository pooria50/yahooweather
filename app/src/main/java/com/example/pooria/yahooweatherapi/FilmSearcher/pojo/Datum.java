
package com.example.pooria.yahooweatherapi.FilmSearcher.pojo;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {
    public Datum(Integer id, String title, String poster, String year, String country, String imdbRating, List<String> genres, List<String> images) {
        this.id = id;
        this.title = title;
        this.poster = poster;
        this.year = year;
        this.country = country;
        this.imdbRating = imdbRating;
        this.genres = genres;
        this.images = images;
    }

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("poster")
    @Expose
    private String poster;
    @SerializedName("year")
    @Expose
    private String year;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("imdb_rating")
    @Expose
    private String imdbRating;
    @SerializedName("genres")
    @Expose
    private List<String> genres = null;
    @SerializedName("images")
    @Expose
    private List<String> images = null;

    private Datum(Builder builder) {
        setId(builder.id);
        setTitle(builder.title);
        setPoster(builder.poster);
        setYear(builder.year);
        setCountry(builder.country);
        setImdbRating(builder.imdbRating);
        setGenres(builder.genres);
        setImages(builder.images);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getImdbRating() {
        return imdbRating;
    }

    public void setImdbRating(String imdbRating) {
        this.imdbRating = imdbRating;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public static final class Builder {
        private Integer id;
        private String title;
        private String poster;
        private String year;
        private String country;
        private String imdbRating;
        private List<String> genres;
        private List<String> images;

        private Builder() {
        }

        public Builder id(Integer val) {
            id = val;
            return this;
        }

        public Builder title(String val) {
            title = val;
            return this;
        }

        public Builder poster(String val) {
            poster = val;
            return this;
        }

        public Builder year(String val) {
            year = val;
            return this;
        }

        public Builder country(String val) {
            country = val;
            return this;
        }

        public Builder imdbRating(String val) {
            imdbRating = val;
            return this;
        }

        public Builder genres(List<String> val) {
            genres = val;
            return this;
        }

        public Builder images(List<String> val) {
            images = val;
            return this;
        }

        public Datum build() {
            return new Datum(this);
        }
    }
}
