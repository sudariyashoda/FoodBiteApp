package com.sudariyashoda.foodbiteapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.google.gson.Gson;
import com.sudariyashoda.foodbiteapp.adapter.RestaurantListAdapter;
import com.sudariyashoda.foodbiteapp.model.RestaurantModel;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Arrays;
import java.util.List;
import java.lang.reflect.Array;

public class FoodListActivity extends AppCompatActivity implements RestaurantListAdapter.RestaurantListClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_list);

        // Set title for the action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Restaurant List");

        // Get the restaurant data
        List<RestaurantModel> restaurantModelList = getRestaurantData();

        // Initialize and set up the RecyclerView
        initRecyclerView(restaurantModelList);
    }

    // Initialize and set up the RecyclerView
    private void initRecyclerView(List<RestaurantModel> restaurantModelList) {
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Create and set the adapter for the RecyclerView
        RestaurantListAdapter adapter = new RestaurantListAdapter(restaurantModelList, this);
        recyclerView.setAdapter(adapter);
    }

    // Get the restaurant data from a JSON file
    private List<RestaurantModel> getRestaurantData() {
        // Read the JSON file
        InputStream is = getResources().openRawResource(R.raw.restaurent);
        Writer writer = new StringWriter();
        char[] buffer = new char[1024];
        try {
            Reader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            int n;
            while ((n = reader.read(buffer)) != -1) {
                writer.write(buffer, 0, n);
            }
        } catch (Exception e) {
            // Handle any exceptions that occur during reading
        }

        // Convert JSON string to RestaurantModel array
        String jsonStr = writer.toString();
        Gson gson = new Gson();
        RestaurantModel[] restaurantModels = gson.fromJson(jsonStr, RestaurantModel[].class);

        // Convert RestaurantModel array to List
        List<RestaurantModel> restList = Arrays.asList(restaurantModels);

        return restList;
    }

    // Handle click events on the restaurant items in the RecyclerView
    @Override
    public void onItemClick(RestaurantModel restaurantModel) {
        // Start the RestaurantMenuActivity and pass the selected restaurant model
        Intent intent = new Intent(FoodListActivity.this, ResturantMenuActivity.class);
        intent.putExtra("RestaurantModel", restaurantModel);
        startActivity(intent);
    }

}
