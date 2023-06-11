package com.sudariyashoda.foodbiteapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.sudariyashoda.foodbiteapp.adapter.MenuListAdapter;
import com.sudariyashoda.foodbiteapp.model.Menu;
import com.sudariyashoda.foodbiteapp.model.RestaurantModel;

import java.util.ArrayList;
import java.util.List;

public class ResturantMenuActivity extends AppCompatActivity implements MenuListAdapter.MenuListClickListener {

    // List to store menu items
    private List<Menu> menuList = null;
    private MenuListAdapter menuListAdapter;

    // List to store items in the cart
    private List<Menu> itemsInCartList;

    // Total number of items in the cart
    private int totalItemInCart = 0;

    // Button for checkout
    private TextView buttonCheckout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resturant_menu);

        // Get the RestaurantModel object from the intent
        RestaurantModel restaurantModel = getIntent().getParcelableExtra("RestaurantModel");

        // Set title, subtitle, and enable back button in ActionBar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(restaurantModel.getName());
        actionBar.setSubtitle(restaurantModel.getAddress());
        actionBar.setDisplayHomeAsUpEnabled(true);

        // Get the menu list from the restaurant model
        menuList = restaurantModel.getMenus();

        // Initialize RecyclerView
        initRecyclerView();

        // Set click listener for the checkout button
        buttonCheckout = findViewById(R.id.buttonCheckout);
        buttonCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Check if there are items in the cart
                if (itemsInCartList != null && itemsInCartList.size() <= 0) {
                    Toast.makeText(ResturantMenuActivity.this, "Please add some items in cart.", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Set the selected menu items in the restaurant model
                restaurantModel.setMenus(itemsInCartList);

                // Start the PlaceYourOrderActivity and pass the restaurant model
                Intent i = new Intent(ResturantMenuActivity.this, PlaceYourOrderActivity.class);
                i.putExtra("RestaurantModel", restaurantModel);
                startActivityForResult(i, 1000);
            }
        });
    }

    // Initialize the RecyclerView and set the adapter
    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        menuListAdapter = new MenuListAdapter(menuList, this);
        recyclerView.setAdapter(menuListAdapter);
    }

    // Listener method for adding an item to the cart
    @Override
    public void onAddToCartClick(Menu menu) {
        if (itemsInCartList == null) {
            itemsInCartList = new ArrayList<>();
        }
        itemsInCartList.add(menu);
        totalItemInCart = 0;

        // Calculate the total number of items in the cart
        for (Menu m : itemsInCartList) {
            totalItemInCart = totalItemInCart + m.getTotalInCart();
        }
        buttonCheckout.setText("Checkout (" + totalItemInCart + ") items");
    }

    // Listener method for updating the cart
    @Override
    public void onUpdateCartClick(Menu menu) {
        if (itemsInCartList.contains(menu)) {
            int index = itemsInCartList.indexOf(menu);
            itemsInCartList.remove(index);
            itemsInCartList.add(index, menu);

            totalItemInCart = 0;

            // Calculate the total number of items in the cart
            for (Menu m : itemsInCartList) {
                totalItemInCart = totalItemInCart + m.getTotalInCart();
            }
            buttonCheckout.setText("Checkout (" + totalItemInCart + ") items");
        }
    }

    // Listener method for removing an item from the cart
    @Override
    public void onRemoveFromCartClick(Menu menu) {
        if (itemsInCartList.contains(menu)) {
            itemsInCartList.remove(menu);
            totalItemInCart = 0;

            // Calculate the total number of items in the cart
            for (Menu m : itemsInCartList) {
                totalItemInCart = totalItemInCart + m.getTotalInCart();
            }
            buttonCheckout.setText("Checkout (" + totalItemInCart + ") items");
        }
    }

    // Handle click events on ActionBar items
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
            default:
                // Do nothing
        }
        return super.onOptionsItemSelected(item);
    }

    // Handle the result from the PlaceYourOrderActivity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // If the request code and result code match, finish the activity
        if (requestCode == 1000 && resultCode == Activity.RESULT_OK) {
            finish();
        }
    }
}
