package com.sudariyashoda.foodbiteapp;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.IOException;


public class Profile extends AppCompatActivity {


        private static final int REQUEST_IMAGE_PICK = 1;
        private static final int PERMISSION_REQUEST_READ_EXTERNAL_STORAGE = 2;

        private ImageView profilePhotoImageView;
        private EditText nameEditText;
        private EditText emailEditText;
        private EditText addressEditText;
        private EditText telephoneEditText;
        private EditText mobilePhoneEditText;
        private Button saveButton;

        private Uri selectedImageUri;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_profile);

            profilePhotoImageView = findViewById(R.id.addprofilephoto);
            nameEditText = findViewById(R.id.addname);
            emailEditText = findViewById(R.id.addemail);
            addressEditText = findViewById(R.id.addaddress);
            telephoneEditText = findViewById(R.id.addphone);
            mobilePhoneEditText = findViewById(R.id.addmobilephone);
            saveButton = findViewById(R.id.save);

            profilePhotoImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pickImageFromGallery();
                }
            });

            saveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String name = nameEditText.getText().toString();
                    String email = emailEditText.getText().toString();
                    String address = addressEditText.getText().toString();
                    String telephone = telephoneEditText.getText().toString();
                    String mobilePhone = mobilePhoneEditText.getText().toString();

                    // Perform validation or other necessary checks on the input data

                    // Display the input data without the save button
                    nameEditText.setEnabled(false);
                    emailEditText.setEnabled(false);
                    addressEditText.setEnabled(false);
                    telephoneEditText.setEnabled(false);
                    mobilePhoneEditText.setEnabled(false);

                    saveButton.setVisibility(View.GONE);

                    Toast.makeText(Profile.this, "Profile Saved!", Toast.LENGTH_SHORT).show();
                }
            });
        }

        private void pickImageFromGallery() {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        PERMISSION_REQUEST_READ_EXTERNAL_STORAGE);
            } else {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, REQUEST_IMAGE_PICK);
            }
        }

        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);

            if (requestCode == REQUEST_IMAGE_PICK && resultCode == RESULT_OK && data != null) {
                selectedImageUri = data.getData();

                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImageUri);
                    profilePhotoImageView.setImageBitmap(bitmap);
                } catch (IOException e) {
                    Log.e("ProfileActivity", "Error loading image", e);
                }
            }
        }

        @Override
        public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);

            if (requestCode == PERMISSION_REQUEST_READ_EXTERNAL_STORAGE) {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    pickImageFromGallery();
                } else {
                    Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }





