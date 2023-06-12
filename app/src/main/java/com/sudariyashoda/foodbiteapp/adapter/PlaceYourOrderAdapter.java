package com.sudariyashoda.foodbiteapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.sudariyashoda.foodbiteapp.R;
import com.sudariyashoda.foodbiteapp.model.Menu;

import java.util.List;

public class PlaceYourOrderAdapter extends RecyclerView.Adapter<PlaceYourOrderAdapter.MyViewHolder> {

    private List<Menu> menuList;

    // Constructor to initialize the adapter with a list of Menu items
    public PlaceYourOrderAdapter(List<Menu> menuList) {
        this.menuList = menuList;
    }

    // Method to update the data in the adapter with a new list of Menu items
    public void updateData(List<Menu> menuList) {
        this.menuList = menuList;
        notifyDataSetChanged();
    }

    // Inflates the layout for each item in the RecyclerView
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.place_order_recycler_row, parent, false);
        return new MyViewHolder(view);
    }

    // Binds the data to the views in each item of the RecyclerView
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        // Set the menu name
        holder.menuName.setText(menuList.get(position).getName());
        // Set the menu price
        holder.menuPrice.setText("Price: Rs." + String.format("%.2f", menuList.get(position).getPrice() * menuList.get(position).getTotalInCart()));
        // Set the menu quantity
        holder.menuQty.setText("Qty: " + menuList.get(position).getTotalInCart());
        // Load the menu image using Glide library
        Glide.with(holder.thumbImage)
                .load(menuList.get(position).getUrl())
                .into(holder.thumbImage);
    }

    // Returns the total number of items in the RecyclerView
    @Override
    public int getItemCount() {
        return menuList.size();
    }

    // ViewHolder class to hold the views for each item in the RecyclerView
    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView menuName;
        TextView menuPrice;
        TextView menuQty;
        ImageView thumbImage;

        public MyViewHolder(View view) {
            super(view);
            // Initialize the views
            menuName = view.findViewById(R.id.menuName);
            menuPrice = view.findViewById(R.id.menuPrice);
            menuQty = view.findViewById(R.id.menuQty);
            thumbImage = view.findViewById(R.id.thumbImage);
        }
    }
}
