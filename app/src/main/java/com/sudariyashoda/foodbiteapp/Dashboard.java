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

        private RecyclerView.Adapter adapter, adapter2;
        private RecyclerView recyclerViewCategory, recycleViewFastList;
        private Accelerometer accelerometer;
        private Gyroscope gyroscope;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_dashboard);

            recyclerViewCategory();
            recycleViewFastList();
            initVideoPlayer();
            accelerometer = new Accelerometer(this);
            gyroscope =new Gyroscope(this);

            accelerometer.setListner(new Accelerometer.Listner() {
                @Override
                public void onTranslation(float tx, float ty, float tz) {
                    if (tx>1.0f){
                        getWindow().getDecorView().setBackgroundColor(Color.parseColor("#e6ffff"));
                    }
                    else if(tx<-1.0f){
                        getWindow().getDecorView().setBackgroundColor(Color.parseColor("#ffe6e6"));
                    }
                }
            });
            gyroscope.setListner(new Gyroscope.Listner() {
                @Override
                public void onRotation(float rx, float ry, float rz) {
                    if (rz>1.0f){
                        getWindow().getDecorView().setBackgroundColor(Color.parseColor("#e6ffcc"));
                    }
                    else if(rz<-1.0f){
                        getWindow().getDecorView().setBackgroundColor(Color.parseColor("#fff2cc"));
                    }
                }
            });
        }

        private void recyclerViewCategory() {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
            recyclerViewCategory = findViewById(R.id.recyclerView);
            recyclerViewCategory.setLayoutManager(linearLayoutManager);

            ArrayList<CategoryDomain> categoryList = new ArrayList<>();
            categoryList.add(new CategoryDomain("Burger", "cat_1"));
            categoryList.add(new CategoryDomain("Pizza", "cat_2"));
            categoryList.add(new CategoryDomain("Juice", "cat_3"));
            categoryList.add(new CategoryDomain("ShortEats", "cat_4"));
            categoryList.add(new CategoryDomain("Curries", "cat_5"));
            categoryList.add(new CategoryDomain("Sweets", "cat_6"));

            adapter = new CategoryAdapter(categoryList);
            recyclerViewCategory.setAdapter(adapter);
        }
        private void recycleViewFastList() {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
            recycleViewFastList = findViewById(R.id.RecyclerView);
            recycleViewFastList.setLayoutManager(linearLayoutManager);

            ArrayList<FastDeliveryDomain>fastDeliveryDomains = new ArrayList<>();

            fastDeliveryDomains.add(new FastDeliveryDomain("Mama's brownies","fast_1"));
            fastDeliveryDomains.add(new FastDeliveryDomain("Cup cakes","fast_2"));
            fastDeliveryDomains.add(new FastDeliveryDomain("Fresh juice","fast_3"));
            fastDeliveryDomains.add(new FastDeliveryDomain("Burger King","fast_4"));
            fastDeliveryDomains.add(new FastDeliveryDomain("SeaFood","fast_5"));

            adapter2 = new FastDeliveryAdapter(fastDeliveryDomains);
            recycleViewFastList.setAdapter(adapter2);

        }

        public void gotodetailsview(View view) {
            Intent intent = new Intent(this, FoodListActivity.class);
            startActivity(intent);

        }
        public void gotocart(View view) {
            Intent intent = new Intent(this, PlaceYourOrderActivity.class);
            startActivity(intent);
        }
        public void gotoprofile(View view) {
            Intent intent = new Intent(this, Profile.class);
            startActivity(intent);
        }
        public void gotomap(View view) {
        Intent intent = new Intent(this, MapActivity.class);
        startActivity(intent);
        }
        public void gotosupport(View view) {
            Intent intent = new Intent(this, Support.class);
            startActivity(intent);
        }

        @Override
        protected void onResume() {
            super.onResume();
            accelerometer.register();
            gyroscope.register();
        }

        @Override
        protected void onPause() {
            super.onPause();
            accelerometer.unegister();
            gyroscope.unegister();
        }
        private void initVideoPlayer (){
            VideoView video = findViewById(R.id.firstvideoView);
            video.setVideoPath("android.resource://"+ getPackageName()+"/"+ R.raw.videoclipdash);
            video.start();
        }
}