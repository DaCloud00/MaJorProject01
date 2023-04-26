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

import com.majorproject.iamrecipes.Listener.RecipeClickListener;
import com.majorproject.iamrecipes.Models.SimilarRecipesResponse;
import com.majorproject.iamrecipes.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SimilarRecipesAdapter extends RecyclerView.Adapter<SimilarRecipesViewHolder>{
    Context context;
    List<SimilarRecipesResponse> list;
    RecipeClickListener listener;

    public SimilarRecipesAdapter(Context context, List<SimilarRecipesResponse> list, RecipeClickListener listener) {
        this.context = context;
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public SimilarRecipesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SimilarRecipesViewHolder(LayoutInflater.from(context).inflate(R.layout.list_similar_recipe, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SimilarRecipesViewHolder holder, int position) {
        holder.textView_similar_title.setText(list.get(position).title);
        holder.textView_similar_title.setSelected(true);
        holder.textView_similar_serving.setText(list.get(position).servings+"persons");
        Picasso.get().load("https://spoonacular.com/recipeImages/"+list.get(position).id+"-556x370."+list.get(position).imageType).into(holder.imageView_similar);

        holder.similar_recipe_holder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onRecipesClicked(String.valueOf(list.get(holder.getBindingAdapterPosition()).id));
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}

class SimilarRecipesViewHolder extends RecyclerView.ViewHolder {
    CardView similar_recipe_holder;
    TextView textView_similar_title, textView_similar_serving;
    ImageView imageView_similar;

    public SimilarRecipesViewHolder(@NonNull View itemView) {
        super(itemView);
        similar_recipe_holder = itemView.findViewById(R.id.similar_recipe_holder);
        textView_similar_title = itemView.findViewById(R.id.textView_similar_title);
        textView_similar_serving = itemView.findViewById(R.id.textView_similar_serving);
        imageView_similar = itemView.findViewById(R.id.imageView_similar);
    }
}
