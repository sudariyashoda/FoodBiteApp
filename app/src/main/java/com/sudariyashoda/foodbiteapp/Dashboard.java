package com.sudariyashoda.foodbiteapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.VideoView;

import com.sudariyashoda.foodbiteapp.adapter.CategoryAdapter;
import com.sudariyashoda.foodbiteapp.adapter.FastDeliveryAdapter;
import com.sudariyashoda.foodbiteapp.domain.Accelerometer;
import com.sudariyashoda.foodbiteapp.domain.CategoryDomain;
import com.sudariyashoda.foodbiteapp.domain.FastDeliveryDomain;
import com.sudariyashoda.foodbiteapp.domain.Gyroscope;

import java.util.ArrayList;

public class Dashboard extends AppCompatActivity {

    // Adapter and RecyclerView variables for category list
    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerViewCategory;

    // Adapter and RecyclerView variables for fast delivery list
    private RecyclerView.Adapter adapter2;
    private RecyclerView recycleViewFastList;

    // Accelerometer and Gyroscope objects
    private Accelerometer accelerometer;
    private Gyroscope gyroscope;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        // Initialize the RecyclerViews
        recyclerViewCategory();
        recycleViewFastList();

        // Initialize the video player
        initVideoPlayer();

        // Initialize the accelerometer and gyroscope
        accelerometer = new Accelerometer(this);
        gyroscope = new Gyroscope(this);

        // Set listeners for accelerometer and gyroscope events
        accelerometer.setListner(new Accelerometer.Listner() {
            @Override
            public void onTranslation(float tx, float ty, float tz) {
                // Change background color based on accelerometer translation values
                if (tx > 1.0f) {
                    getWindow().getDecorView().setBackgroundColor(Color.parseColor("#e6ffff"));
                } else if (tx < -1.0f) {
                    getWindow().getDecorView().setBackgroundColor(Color.parseColor("#ffe6e6"));
                }
            }
        });

        gyroscope.setListner(new Gyroscope.Listner() {
            @Override
            public void onRotation(float rx, float ry, float rz) {
                // Change background color based on gyroscope rotation values
                if (rz > 1.0f) {
                    getWindow().getDecorView().setBackgroundColor(Color.parseColor("#e6ffcc"));
                } else if (rz < -1.0f) {
                    getWindow().getDecorView().setBackgroundColor(Color.parseColor("#fff2cc"));
                }
            }
        });
    }

    // Set up the RecyclerView for category list
    private void recyclerViewCategory() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewCategory = findViewById(R.id.recyclerView);
        recyclerViewCategory.setLayoutManager(linearLayoutManager);

        // Create a list of category items
        ArrayList<CategoryDomain> categoryList = new ArrayList<>();
        categoryList.add(new CategoryDomain("Burger", "cat_1"));
        categoryList.add(new CategoryDomain("Pizza", "cat_2"));
        categoryList.add(new CategoryDomain("Juice", "cat_3"));
        categoryList.add(new CategoryDomain("ShortEats", "cat_4"));
        categoryList.add(new CategoryDomain("Curries", "cat_5"));
        categoryList.add(new CategoryDomain("Sweets", "cat_6"));

        // Create an adapter and set it to the RecyclerView
        adapter = new CategoryAdapter(categoryList);
        recyclerViewCategory.setAdapter(adapter);
    }

    // Set up the RecyclerView for fast delivery list
    private void recycleViewFastList() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recycleViewFastList = findViewById(R.id.RecyclerView);
        recycleViewFastList.setLayoutManager(linearLayoutManager);

        // Create a list of fast delivery items
        ArrayList<FastDeliveryDomain> fastDeliveryDomains = new ArrayList<>();
        fastDeliveryDomains.add(new FastDeliveryDomain("Mama's brownies", "fast_1"));
        fastDeliveryDomains.add(new FastDeliveryDomain("Cup cakes", "fast_2"));
        fastDeliveryDomains.add(new FastDeliveryDomain("Fresh juice", "fast_3"));
        fastDeliveryDomains.add(new FastDeliveryDomain("Burger King", "fast_4"));
        fastDeliveryDomains.add(new FastDeliveryDomain("SeaFood", "fast_5"));

        // Create an adapter and set it to the RecyclerView
        adapter2 = new FastDeliveryAdapter(fastDeliveryDomains);
        recycleViewFastList.setAdapter(adapter2);
    }

    // Start FoodListActivity
    public void gotodetailsview(View view) {
        Intent intent = new Intent(this, FoodListActivity.class);
        startActivity(intent);
    }

    // Start PlaceYourOrderActivity
    public void gotocart(View view) {
        Intent intent = new Intent(this, PlaceYourOrderActivity.class);
        startActivity(intent);
    }

    // Start Profile activity
    public void gotoprofile(View view) {
        Intent intent = new Intent(this, Profile.class);
        startActivity(intent);
    }

    // Start MapActivity
    public void gotomap(View view) {
        Intent intent = new Intent(this, MapActivity.class);
        startActivity(intent);
    }

    // Start Support activity
    public void gotosupport(View view) {
        Intent intent = new Intent(this, Support.class);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Register accelerometer and gyroscope listeners
        accelerometer.register();
        gyroscope.register();
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Unregister accelerometer and gyroscope listeners
        accelerometer.unegister();
        gyroscope.unegister();
    }

    // Initialize the video player
    private void initVideoPlayer() {
        VideoView video = findViewById(R.id.firstvideoView);
        video.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.videoclipdash);
        video.start();
    }
}
