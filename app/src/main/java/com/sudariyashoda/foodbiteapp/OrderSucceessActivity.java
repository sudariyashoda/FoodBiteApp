package com.sudariyashoda.foodbiteapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.sudariyashoda.foodbiteapp.model.RestaurantModel;

public class OrderSucceessActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_succeess);

        // Get the RestaurantModel object from the intent
        RestaurantModel restaurantModel = getIntent().getParcelableExtra("RestaurantModel");

        // Set up the action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(restaurantModel.getName()); // Set the title of the action bar to the restaurant name
        actionBar.setSubtitle(restaurantModel.getAddress()); // Set the subtitle of the action bar to the restaurant address
        actionBar.setDisplayHomeAsUpEnabled(false); // Disable the home button on the action bar

        // Set up the "Done" button
        TextView buttonDone = findViewById(R.id.buttonDone);
        buttonDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Close the activity when the "Done" button is clicked
            }
        });
    }
}
