package com.example.assignedproject1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class Dashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

        Button signOutButton = findViewById(R.id.signout_button);
        signOutButton.setOnClickListener(view -> {
            firebaseAuth.signOut();
            Toast.makeText(this, "Signing Out", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, MainActivity.class));
        });
    }
}