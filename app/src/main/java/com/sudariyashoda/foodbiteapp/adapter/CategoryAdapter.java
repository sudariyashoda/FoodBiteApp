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

    public CategoryAdapter(ArrayList<CategoryDomain> categoryDomains) {
        this.categoryDomains = categoryDomains;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate= LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_category,parent,false);
        return new ViewHolder(inflate);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.categoryName.setText(categoryDomains.get(position).getTitle());
        String picUrl= "";
        switch (position){
            case 0:{
                picUrl="cat_1";
                break;
            }
            case 1:{
                picUrl="cat_2";
                break;
            }
            case 2:{
                picUrl="cat_3";
                break;
            }
            case 3:{
                picUrl="cat_4";
                break;
            }
            case 4:{
                picUrl="cat_5";
                break;
            }
            case 5:{
                picUrl="cat_6";
                break;
            }
        }
        int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(picUrl,"drawable",holder.itemView.getContext()
                .getPackageName());
        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .into(holder.categoryPic);
    }

    @Override
    public int getItemCount() {
        return categoryDomains.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView categoryName;
        ImageView categoryPic;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryPic=itemView.findViewById(R.id.categoryPic);
            categoryName = itemView.findViewById(R.id.categoryName);
        }


    }

}
