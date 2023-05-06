package com.majorproject.iamrecipes.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.majorproject.iamrecipes.Models.Ingredient;
import com.majorproject.iamrecipes.Models.Step;
import com.majorproject.iamrecipes.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class InstructionsIngredientsAdapter extends RecyclerView.Adapter<InstructionIngredientsAdapter> {
    Context context;
    List<Ingredient> list;

    public InstructionsIngredientsAdapter(Context context, List<Ingredient> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public InstructionIngredientsAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new InstructionIngredientsAdapter(LayoutInflater.from(context).inflate(R.layout.list_instructions_step_items, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull InstructionIngredientsAdapter holder, int position) {

        holder.textView_instructions_step_items.setText(list.get(position).name);
        holder.textView_instructions_step_items.setSelected(true);
        Picasso.get().load("https://spoonacular.com/cdn/ingredients_100x100/"+list.get(position).image).into(holder.imageView_instruction_step_items);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}

class InstructionIngredientsAdapter extends RecyclerView.ViewHolder{
    ImageView imageView_instruction_step_items;
    TextView textView_instructions_step_items;


    public InstructionIngredientsAdapter(@NonNull View itemView) {
        super(itemView);
        imageView_instruction_step_items = itemView.findViewById(R.id.imageView_instruction_step_items);
        textView_instructions_step_items = itemView.findViewById(R.id.textView_instructions_step_items);
    }

}