package com.majorproject.iamrecipes.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.majorproject.iamrecipes.Models.ExtendedIngredient;
import com.majorproject.iamrecipes.Models.Recipe;
import com.majorproject.iamrecipes.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RandomRecipeAdapter extends RecyclerView.Adapter<RandomRecipeViewHolder>{
    Context context;
    List<Recipe> list;

    public RandomRecipeAdapter(Context context, List<Recipe> list){
        this.context = context;
        this.list = list;
    }
    @NonNull
    @Override
    public RandomRecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RandomRecipeViewHolder(LayoutInflater.from(context).inflate(R.layout.list_random_recipes, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RandomRecipeViewHolder holder, int position) {
        holder.textView_title.setText(list.get(position).title);
        holder.textView_title.setSelected(true);
        holder.textView_like.setText(list.get(position).aggregateLikes+"likes");
        holder.textView_servings.setText(list.get(position).servings+"Servings");
        holder.textView_time.setText(list.get(position).readyInMinutes+"Minutes");
        Picasso.get().load(list.get(position).image).into(holder.imageView_food);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
class RandomRecipeViewHolder extends RecyclerView.ViewHolder {
    CardView random_list_container;
    TextView textView_title, textView_servings, textView_like, textView_time;
    ImageView imageView_food;


    public RandomRecipeViewHolder(@NonNull View itemVew){
        super(itemVew);
        random_list_container = itemVew.findViewById(R.id.random_list_container);
        textView_title = itemVew.findViewById(R.id.textView_title);
        textView_servings = itemVew.findViewById(R.id.textView_servings);
        textView_like = itemVew.findViewById(R.id.textView_likes);
        textView_time = itemVew.findViewById(R.id.textview_time);
        imageView_food = itemVew.findViewById(R.id.imageView_food);
    }

}