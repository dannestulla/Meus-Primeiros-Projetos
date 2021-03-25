package com.example.avatarmaker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private EditText ePassWord, eEMail;
    private Button eRegisterButton;
    private ProgressBar eProgressBar;
    private String passWord, eMail;



    //First Screen, Menu to go to LookingForJob or JobsAvaliable
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();


        if (mAuth.getCurrentUser() != null) {
            Intent secondScreen = new Intent(getApplicationContext(), ChoosePath.class);
            startActivity(secondScreen);
        }
    }

    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        updateUI(user);



    }

    public void updateUI(FirebaseUser account) {

        if (account != null) {
            Toast.makeText(this, "You are signed in!", Toast.LENGTH_LONG).show();
            startActivity(new Intent(this, ChoosePath.class));

        } else {
            Toast.makeText(this, "You are not signed in yet", Toast.LENGTH_LONG).show();
        }
    }

    public void loginClick(View view) {
        ePassWord = findViewById((R.id.password));
        eEMail = findViewById((R.id.email));
        eRegisterButton = findViewById((R.id.register2));
        eProgressBar = findViewById(R.id.progressBar);

        passWord = ePassWord.getText().toString().trim();
        eMail = eEMail.getText().toString().trim();
        if (passWord.isEmpty() || eMail.isEmpty()) {
            Toast.makeText(MainActivity.this, "Enter both your email and password", Toast.LENGTH_LONG).show();
        } else {
            mAuth.signInWithEmailAndPassword(eMail, passWord).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(MainActivity.this, "Login Sucessful", Toast.LENGTH_LONG);
                        startActivity(new Intent(getApplicationContext(), ChoosePath.class));

                    } else {
                        Toast.makeText(MainActivity.this, "Error login in", Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }

    public void registerClick(View view) {
        ePassWord = findViewById((R.id.password));
        eEMail = findViewById((R.id.email));
        eRegisterButton = findViewById((R.id.register2));
        eProgressBar = findViewById(R.id.progressBar);

        passWord = ePassWord.getText().toString().trim();
        eMail = eEMail.getText().toString().trim();
        if (passWord.isEmpty() || eMail.isEmpty()) {
            Toast.makeText(MainActivity.this, "Fill both your Email and Password fields", Toast.LENGTH_LONG).show();
        } else {
            eProgressBar.setVisibility(View.VISIBLE);
            mAuth.createUserWithEmailAndPassword(eMail, passWord).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(MainActivity.this, "User Created", Toast.LENGTH_LONG).show();

                        Intent secondScreen = new Intent(getApplicationContext(), ChoosePath.class);
                        startActivity(secondScreen);
                    } else {
                        Toast.makeText(MainActivity.this, "Error creating account", Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }

    public void forgotPassword(View view) {
        eEMail = findViewById((R.id.email));
        eMail = eEMail.getText().toString().trim();

        if (eMail.isEmpty()) {
            Toast.makeText(MainActivity.this, "Fill your email account", Toast.LENGTH_LONG).show();
        } else {

            mAuth.sendPasswordResetEmail(eMail);
            Toast.makeText(MainActivity.this, "Password reset sent to " + eMail, Toast.LENGTH_LONG).show();
        }
    }

}

;


























