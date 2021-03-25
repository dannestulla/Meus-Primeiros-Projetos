package com.example.avatarmaker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class ChoosePath extends AppCompatActivity {
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen2);


    }

    public void logOff(View view) {
        mAuth = FirebaseAuth.getInstance();
        mAuth.signOut();
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }
    public void lookingFor(View view) {
        startActivity(new Intent(getApplicationContext(), OrderRequest.class));

    }
    public void jobsAvaliable(View view) {
        startActivity(new Intent(getApplicationContext(), JobsAvaliable2.class));

    }


}