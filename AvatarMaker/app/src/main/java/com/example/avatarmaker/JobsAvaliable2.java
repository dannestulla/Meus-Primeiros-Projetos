package com.example.avatarmaker;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;



public class JobsAvaliable2 extends AppCompatActivity {

    private FirebaseFirestore firebaseFirestore;
    private FirestoreRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jobs_avaliable2);
        RecyclerView mFirestoreList = findViewById(R.id.recyclerView);

        firebaseFirestore = FirebaseFirestore.getInstance();
        //Query
        Query query = firebaseFirestore.collection("orders");

        //RecyclerOptions
        FirestoreRecyclerOptions<ProductsModel> options = new FirestoreRecyclerOptions.Builder<ProductsModel>()
                .setQuery(query, ProductsModel.class)
                .build();

        adapter = new FirestoreRecyclerAdapter<ProductsModel, ProductsViewHolder>(options) {
            @NonNull
            @Override
            public ProductsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_jobs_avaliable, parent, false);
                return new ProductsViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull ProductsViewHolder holder, int position, @NonNull ProductsModel model) {
                holder.card_style.setText(model.getStyle());
                holder.card_format.setText(model.getFormat());
                holder.card_number.setText(model.getQnt_people());
                holder.card_name.setText(model.getUser_name());
            }
        };

        mFirestoreList.setHasFixedSize(true);
        mFirestoreList.setLayoutManager(new LinearLayoutManager(this));
        mFirestoreList.setAdapter(adapter);



    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();

    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    private class ProductsViewHolder extends RecyclerView.ViewHolder {

        private final TextView card_style;
        private final TextView card_format;
        private final TextView card_number;
        private final TextView card_name;



        public ProductsViewHolder(@NonNull View itemView) {
            super(itemView);

            card_style = itemView.findViewById(R.id.card_style);
            card_format = itemView.findViewById(R.id.card_format);
            card_number = itemView.findViewById(R.id.card_number);
            card_name = itemView.findViewById(R.id.card_name);

        }


    }
}



