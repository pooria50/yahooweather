package com.example.pooria.yahooweatherapi.Recycler;

/**
 * Created by pooria on 4/24/18.
 */

public class FoodModel {
    private String foodName;
    private int pride;
    private String restaurant;
    private String type;
    private String image_url;

    private FoodModel(Builder builder) {
        foodName = builder.foodName;
        pride = builder.pride;
        restaurant = builder.restaurant;
        type = builder.type;
        image_url = builder.image_url;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public int getPride() {
        return pride;
    }

    public void setPride(int pride) {
        this.pride = pride;
    }

    public String getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(String restaurant) {
        this.restaurant = restaurant;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public static final class Builder {
        private String foodName;
        private int pride;
        private String restaurant;
        private String type;
        private String image_url;

        private Builder() {
        }

        public Builder foodName(String val) {
            foodName = val;
            return this;
        }

        public Builder pride(int val) {
            pride = val;
            return this;
        }

        public Builder restaurant(String val) {
            restaurant = val;
            return this;
        }

        public Builder type(String val) {
            type = val;
            return this;
        }

        public Builder image_url(String val) {
            image_url = val;
            return this;
        }

        public FoodModel build() {
            return new FoodModel(this);
        }
    }
}
