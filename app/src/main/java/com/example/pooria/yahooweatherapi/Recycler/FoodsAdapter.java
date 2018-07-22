package com.example.pooria.yahooweatherapi.Recycler;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pooria.yahooweatherapi.R;
import com.example.pooria.yahooweatherapi.utils.PublicMethods;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by pooria on 4/24/18.
 */

public class FoodsAdapter extends RecyclerView.Adapter<FoodsAdapter.MyHolder> {
    Context mContext;
    List<FoodModel> foods;
    @Override

    public FoodsAdapter.MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.activity_foods_list, parent, false);
        return new MyHolder(v);
    }

    public FoodsAdapter(Context mContext, List<FoodModel> foods) {
        this.mContext = mContext;
        this.foods = foods;
    }

    @Override
    public void onBindViewHolder(FoodsAdapter.MyHolder holder, int position) {
        holder.name.setText(foods.get(position).getFoodName());
        holder.type.setText(foods.get(position).getType());
        holder.price.setText(foods.get(position).getPride()+"");
        Picasso.get().load(foods.get(position).getImage_url()).into(holder.img);

    }

    @Override
    public int getItemCount() {
        return foods.size();
    }

    @Override
    public int getItemViewType(int position) {
        return 1;
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView name,price,type;
        public MyHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            price = itemView.findViewById(R.id.price);
            type = itemView.findViewById(R.id.type);
            img = itemView.findViewById(R.id.img);
            name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    PublicMethods.ShowToast(mContext, "name");
                }
            });
            img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    PublicMethods.ShowToast(mContext,"image_id");
                }
            });

        }
    }
}
