package com.sudariyashoda.foodbiteapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Hide the action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        // Delay the start of a new activity using a Handler
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Start the Login activity
                startActivity(new Intent(MainActivity.this, Login.class));
                finish();
            }
        }, 2000); // Delay for 2000 milliseconds (2 seconds)
    }
}
