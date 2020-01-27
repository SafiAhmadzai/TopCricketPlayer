package com.example.topcricketplayer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.topcricketplayer.adapters.PlayerAdapter;
import com.example.topcricketplayer.models.Players;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.lang.ref.Reference;

public class MainActivity extends AppCompatActivity {

    private  FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference player = db.collection("players");


    private PlayerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpRecyclerView();
    }

    private void setUpRecyclerView() {

        Query query = player.orderBy("rating", Query.Direction.DESCENDING);

        FirestoreRecyclerOptions<Players> options = new FirestoreRecyclerOptions.Builder<Players>()
                .setQuery(query, Players.class)
                .build();

        adapter = new PlayerAdapter(options);

        RecyclerView recyclerView = findViewById(R.id.rvPlayers);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}
