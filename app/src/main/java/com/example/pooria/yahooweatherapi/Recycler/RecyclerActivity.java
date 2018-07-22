package com.example.pooria.yahooweatherapi.Recycler;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.pooria.yahooweatherapi.R;
import com.example.pooria.yahooweatherapi.utils.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class RecyclerActivity extends BaseActivity {
    private RecyclerView myrecycler;
    private String image_url = "data:image/jpeg;base64,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";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);
        bind();
        FoodModel foodModel = FoodModel.newBuilder().foodName("ghormesabzi").type("sonaty").pride(12000).restaurant("orkide").image_url(image_url).build();
        FoodModel foodModel1 = FoodModel.newBuilder().foodName("gheyme").type("sonaty").pride(12000).restaurant("orkide").image_url(image_url).build();
        FoodModel foodModel2 = FoodModel.newBuilder().foodName("mahi").type("sonaty").pride(12000).restaurant("orkide").image_url(image_url).build();
        FoodModel foodModel3 = FoodModel.newBuilder().foodName("adas polo").type("sonaty").pride(12000).restaurant("orkide").image_url(image_url).build();
        FoodModel foodModel4 = FoodModel.newBuilder().foodName("pizzaa").type("sonaty").pride(12000).restaurant("orkide").image_url(image_url).build();
        FoodModel foodModel5 = FoodModel.newBuilder().foodName("sandwich").type("sonaty").pride(12000).restaurant("orkide").image_url(image_url).build();
        FoodModel foodModel6 = FoodModel.newBuilder().foodName("pasta").type("sonaty").pride(12000).restaurant("orkide").image_url(image_url).build();
        FoodModel foodModel7 = FoodModel.newBuilder().foodName("burger").type("sonaty").pride(12000).restaurant("orkide").image_url(image_url).build();
        FoodModel foodModel8 = FoodModel.newBuilder().foodName("salad").type("sonaty").pride(12000).restaurant("orkide").image_url(image_url).build();
        FoodModel foodModel9 = FoodModel.newBuilder().foodName("morq").type("sonaty").pride(12000).restaurant("orkide").image_url(image_url).build();
        FoodModel foodModel10 = FoodModel.newBuilder().foodName("abgoosht").type("sonaty").pride(12000).restaurant("orkide").image_url(image_url).build();
        List<FoodModel> foods = new ArrayList<>();
        foods.add(foodModel);
        foods.add(foodModel1);
        foods.add(foodModel2);
        foods.add(foodModel3);
        foods.add(foodModel4);
        foods.add(foodModel5);
        foods.add(foodModel6);
        foods.add(foodModel7);
        foods.add(foodModel9);
        foods.add(foodModel10);
        FoodsAdapter foodsAdapter = new FoodsAdapter(mContext, foods);
            LinearLayoutManager manager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        myrecycler.setLayoutManager(manager);
        myrecycler.setAdapter(foodsAdapter);

    }
    void bind (){
        myrecycler = findViewById(R.id.myRecycler);
    }
}
