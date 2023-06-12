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
import com.sudariyashoda.foodbiteapp.domain.FastDeliveryDomain;

import java.util.ArrayList;

public class FastDeliveryAdapter extends RecyclerView.Adapter<FastDeliveryAdapter.Viewholder> {
    ArrayList<FastDeliveryDomain> fastDeliveryDomain;

    public FastDeliveryAdapter(ArrayList<FastDeliveryDomain> fastDeliveryDomain) {
        this.fastDeliveryDomain = fastDeliveryDomain;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the layout for the view holder
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_holder_fast_delivery, parent, false);
        return new Viewholder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        // Set the time text for the current fast delivery domain
        holder.time.setText(fastDeliveryDomain.get(position).getTime() + " min");

        // Set the title text for the current fast delivery domain
        holder.Title.setText(fastDeliveryDomain.get(position).getTitle());

        // Get the resource ID for the image in the current fast delivery domain
        int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(
                fastDeliveryDomain.get(position).getPic(), "drawable",
                holder.itemView.getContext().getPackageName());

        // Load the image into the ImageView using Glide library
        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .into(holder.pic);
    }

    @Override
    public int getItemCount() {
        // Return the number of items in the fastDeliveryDomain list
        return fastDeliveryDomain.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        TextView Title, description, time;
        ImageView pic;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            // Initialize the views in the view holder
            Title = itemView.findViewById(R.id.Title);
            pic = itemView.findViewById(R.id.pic);
            time = itemView.findViewById(R.id.time);
            description = itemView.findViewById(R.id.description);
        }
    }
}
