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
import com.example.topcricketplayer.MainActivity;
import com.example.topcricketplayer.R;
import com.example.topcricketplayer.models.Players;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

import java.util.List;

public class PlayerAdapter extends FirestoreRecyclerAdapter<Players, PlayerAdapter.PlayerHolder> {

    Context context;


    public PlayerAdapter(@NonNull FirestoreRecyclerOptions<Players> options) {
        super(options);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull PlayerHolder holder, int position, @NonNull Players model) {

        holder.tvPlayerName.setText(model.getName());
        holder.tvPlayerRating.setText(String.valueOf(model.getRating()));

//        Glide.with(context)
//                .load(model.getImage())
//                .centerCrop()
//                .placeholder(R.mipmap.ic_launcher)
//                .into(holder.ivPlayer);

    }

    @NonNull
    @Override
    public PlayerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.player_row, parent, false);
        return new PlayerHolder(v);
    }

    class  PlayerHolder extends RecyclerView.ViewHolder {
        ImageView ivPlayer;
        TextView tvPlayerName;
        TextView tvPlayerRating;

        public PlayerHolder(@NonNull View itemView) {
            super(itemView);

            ivPlayer = itemView.findViewById(R.id.ivPlayer);
            tvPlayerName = itemView.findViewById(R.id.tvPlayerName);
            tvPlayerRating = itemView.findViewById(R.id.tvPlayerRating);

        }
    }
}
