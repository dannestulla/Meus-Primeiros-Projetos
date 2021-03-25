package com.example.avatarmaker;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.protobuf.StringValue;

public class OrderRequest extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    private final HashMap<String, String> prefChosen = new HashMap<String, String>();
    private boolean alreadyExecuted;
    HashMap<String, String> order_map = new HashMap<>();

    int order_number;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_looking_for_job);

        SeekBar seekBar = findViewById(R.id.seekBar);
        TextView qntPessoas = findViewById(R.id.peopleNumber);


        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                qntPessoas.setText(Integer.toString(seekBar.getProgress()));
                Vibrator vibrate = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                vibrate.vibrate(500);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Vibrator vibrate = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                vibrate.vibrate(500);

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


    }
    public void backButton(View view) {
        startActivity(new Intent(getApplicationContext(), ChoosePath.class));
    }

    public void logOff(View view) {
        mAuth = FirebaseAuth.getInstance();
        mAuth.signOut();
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }

    //Interface to read
    private interface MyCallback{
        void onCallback(int resultado);
    }

    private void readData(MyCallback myCallback) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("order_id").document("id")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isComplete()) {
                        DocumentSnapshot document = task.getResult();
                        int order_number = Integer.valueOf(document.getString("order_id"));
                        System.out.println(order_number);
                        myCallback.onCallback(order_number);




                    } else {
                        System.out.println("Error " + task.getException());
                    }
                });}

    public void buttonRequest(View view) {
        EditText avatarStyle = findViewById(R.id.avatarStyle);
        TextView nPeople = findViewById(R.id.peopleNumber);
        EditText picFormat = findViewById(R.id.mediaFormat);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        //1
        readData(new MyCallback() {
            @Override
            public void onCallback(int resultado) {
                //resultado ok até aqui!
                Log.e("1", String.valueOf(resultado));
                order_number = resultado;
                alreadyExecuted = true;
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                String email = user.getEmail();

                //
                    Log.e("2", String.valueOf(order_number));

                    order_number += 1;
                    Log.e("3", String.valueOf(order_number));
                    order_map.put("order_id", String.valueOf(order_number));
                    Log.e("4", String.valueOf(order_number));
                    alreadyExecuted = true;

                    //adicionar +1 no numero das orders
                    db.collection("order_id").document("id")
                            .set(order_map)
                            .addOnFailureListener(e -> System.out.println("Error"));

                    prefChosen.put("user_name", email);
                    prefChosen.put("style", avatarStyle.getText().toString());
                    prefChosen.put("qnt_people", nPeople.getText().toString());
                    prefChosen.put("format", picFormat.getText().toString());

                    //adicionar novo dado na db
                    db.collection("orders").document(Integer.toString(order_number))
                            .set(prefChosen)
                            .addOnSuccessListener(aVoid -> {
                            })
                            .addOnFailureListener(e -> System.out.println("Error adding document"));

                    Toast.makeText(OrderRequest.this,"Your Request Was Successfull", Toast.LENGTH_LONG).show();
            }});}}
















