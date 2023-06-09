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
        View inflate= LayoutInflater.from(parent.getContext()).inflate(R.layout.view_holder_fast_delivery,parent,false);
        return new Viewholder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        holder.time.setText(fastDeliveryDomain.get(position).getTime()+" min");
        holder.Title.setText(fastDeliveryDomain.get(position).getTitle());
        int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(fastDeliveryDomain.get(position).getPic(),"drawable",
                holder.itemView.getContext().getPackageName());
        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .into(holder.pic);
    }

    @Override
    public int getItemCount() {
        return fastDeliveryDomain.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder{
        TextView Title,description,time;
        ImageView pic;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            Title=itemView.findViewById(R.id.Title);
            pic=itemView.findViewById(R.id.pic);
            time=itemView.findViewById(R.id.time);
            description=itemView.findViewById(R.id.description);
        }
    }
}
