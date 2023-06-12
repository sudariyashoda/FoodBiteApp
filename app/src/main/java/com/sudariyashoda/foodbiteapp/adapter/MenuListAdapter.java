package com.sudariyashoda.foodbiteapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sudariyashoda.foodbiteapp.R;
import com.bumptech.glide.Glide;
import com.sudariyashoda.foodbiteapp.model.Menu;

import java.util.List;

public class MenuListAdapter extends RecyclerView.Adapter<MenuListAdapter.MyViewHolder> {

    private List<Menu> menuList;
    private MenuListClickListener clickListener;

    // Constructor to initialize the adapter with a menu list and click listener
    public MenuListAdapter(List<Menu> menuList, MenuListClickListener clickListener) {
        this.menuList = menuList;
        this.clickListener = clickListener;
    }

    // Update the menu list data
    public void updateData(List<Menu> menuList) {
        this.menuList = menuList;
        notifyDataSetChanged();
    }

    // Inflate the item layout and create a new ViewHolder
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_recycler_row, parent, false);
        return  new MyViewHolder(view);
    }

    // Bind the data to the views in the ViewHolder
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        // Set the name and price of the menu item
        holder.menuName.setText(menuList.get(position).getName());
        holder.menuPrice.setText("Price: Rs."+menuList.get(position).getPrice());

        // Set the click listener for the "Add to Cart" button
        holder.addToCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the menu item at the clicked position
                Menu menu  = menuList.get(position);
                menu.setTotalInCart(1);
                // Notify the click listener that the item is added to the cart
                clickListener.onAddToCartClick(menu);
                // Show the "Add More" layout and hide the "Add to Cart" button
                holder.addMoreLayout.setVisibility(View.VISIBLE);
                holder.addToCartButton.setVisibility(View.GONE);
                holder.tvCount.setText(menu.getTotalInCart()+"");
            }
        });

        // Set the click listener for the minus button
        holder.imageMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the menu item at the clicked position
                Menu menu  = menuList.get(position);
                int total = menu.getTotalInCart();
                total--;
                if(total > 0 ) {
                    menu.setTotalInCart(total);
                    // Notify the click listener that the cart is updated
                    clickListener.onUpdateCartClick(menu);
                    holder.tvCount.setText(total +"");
                } else {
                    holder.addMoreLayout.setVisibility(View.GONE);
                    holder.addToCartButton.setVisibility(View.VISIBLE);
                    menu.setTotalInCart(total);
                    // Notify the click listener that the item is removed from the cart
                    clickListener.onRemoveFromCartClick(menu);
                }
            }
        });

        // Set the click listener for the plus button
        holder.imageAddOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the menu item at the clicked position
                Menu menu  = menuList.get(position);
                int total = menu.getTotalInCart();
                total++;
                if(total <= 10 ) {
                    menu.setTotalInCart(total);
                    // Notify the click listener that the cart is updated
                    clickListener.onUpdateCartClick(menu);
                    holder.tvCount.setText(total +"");
                }
            }
        });

        // Load the thumbnail image using Glide library
        Glide.with(holder.thumbImage)
                .load(menuList.get(position).getUrl())
                .into(holder.thumbImage);
    }

    // Return the number of items in the menu list
    @Override
    public int getItemCount() {
        return menuList.size();
    }

    // ViewHolder class to hold the views of an item in the RecyclerView
    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView  menuName;
        TextView  menuPrice;
        TextView  addToCartButton;
        ImageView thumbImage;
        ImageView imageMinus;
        ImageView imageAddOne;
        TextView  tvCount;
        LinearLayout addMoreLayout;

        public MyViewHolder(View view) {
            super(view);
            menuName = view.findViewById(R.id.menuName);
            menuPrice = view.findViewById(R.id.menuPrice);
            addToCartButton = view.findViewById(R.id.addToCartButton);
            thumbImage = view.findViewById(R.id.thumbImage);
            imageMinus = view.findViewById(R.id.imageMinus);
            imageAddOne = view.findViewById(R.id.imageAddOne);
            tvCount = view.findViewById(R.id.tvCount);
            addMoreLayout  = view.findViewById(R.id.addMoreLayout);
        }
    }

    // Interface for click events on the menu items
    public interface MenuListClickListener {
        void onAddToCartClick(Menu menu);
        void onUpdateCartClick(Menu menu);
        void onRemoveFromCartClick(Menu menu);
    }
}
