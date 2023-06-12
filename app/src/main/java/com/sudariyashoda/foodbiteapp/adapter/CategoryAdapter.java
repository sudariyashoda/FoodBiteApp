package com.sudariyashoda.foodbiteapp.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.sudariyashoda.foodbiteapp.R;
import com.sudariyashoda.foodbiteapp.domain.CategoryDomain;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    ArrayList<CategoryDomain> categoryDomains;

    // Constructor
    public CategoryAdapter(ArrayList<CategoryDomain> categoryDomains) {
        this.categoryDomains = categoryDomains;
    }

    // Create the ViewHolder by inflating the layout for each item
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_category, parent, false);
        return new ViewHolder(inflate);
    }

    // Bind the data to the ViewHolder
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        // Set the category name
        holder.categoryName.setText(categoryDomains.get(position).getTitle());

        String picUrl = "";

        // Determine the picture URL based on the position
        switch (position) {
            case 0:
                picUrl = "cat_1";
                break;
            case 1:
                picUrl = "cat_2";
                break;
            case 2:
                picUrl = "cat_3";
                break;
            case 3:
                picUrl = "cat_4";
                break;
            case 4:
                picUrl = "cat_5";
                break;
            case 5:
                picUrl = "cat_6";
                break;
        }

        // Get the resource ID of the drawable
        int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(picUrl, "drawable", holder.itemView.getContext().getPackageName());

        // Load the image into the ImageView using Glide library
        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .into(holder.categoryPic);
    }

    // Return the number of items in the data set
    @Override
    public int getItemCount() {
        return categoryDomains.size();
    }

    // ViewHolder class
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView categoryName;
        ImageView categoryPic;

        // Constructor
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryPic = itemView.findViewById(R.id.categoryPic);
            categoryName = itemView.findViewById(R.id.categoryName);
        }
    }
}
