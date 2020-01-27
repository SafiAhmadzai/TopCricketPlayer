package com.example.topcricketplayer.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.topcricketplayer.R;
import com.example.topcricketplayer.models.Players;

import java.util.List;

public class TopPlayerAdapter extends RecyclerView.Adapter<TopPlayerAdapter.TopPlayerViewHolder> {

    Context context;
    List<Players> playersData;

    public TopPlayerAdapter(Context context, List<Players> playersData) {
        this.context = context;
        this.playersData = playersData;
    }


    @NonNull
    @Override
    public TopPlayerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.player_row,parent,false);
        TopPlayerViewHolder topPlayerViewHolder = new TopPlayerViewHolder(view);
        return topPlayerViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TopPlayerViewHolder holder, int position) {
        final  Players players = playersData.get(position);

        holder.tvPlayerName.setText(players.getName());

        Glide
                .with(context)
                .load(players.getImage())
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher)
                .into(holder.ivPlayer);
    }


    @Override
    public int getItemCount() {
        return playersData.size();
    }

    public class TopPlayerViewHolder extends RecyclerView.ViewHolder{

        ImageView ivPlayer;
        TextView tvPlayerName;

        public TopPlayerViewHolder(@NonNull View itemView) {
            super(itemView);
            ivPlayer = itemView.findViewById(R.id.ivPlayer);
            tvPlayerName = itemView.findViewById(R.id.tvPlayerName);
        }
    }
}
