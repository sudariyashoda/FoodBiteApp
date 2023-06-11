package com.sudariyashoda.foodbiteapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {
    // Declare variables
    EditText inputName, inputPassword, inputConfirmpassword;
    Button signUp, signtologin;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Initialize variables with corresponding views from layout
        inputName = (EditText) findViewById(R.id.inputName);
        inputPassword = (EditText) findViewById(R.id.inputPassword);
        inputConfirmpassword = (EditText) findViewById(R.id.inputConfirmpassword);
        signtologin = (Button) findViewById(R.id.signtologin);
        signUp = (Button) findViewById(R.id.signUp);
        DB = new DBHelper(this);

        // Set click listener for the signUp button
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Retrieve input values
                String iname = inputName.getText().toString();
                String ipass = inputPassword.getText().toString();
                String iconpass = inputConfirmpassword.getText().toString();

                if (iname.equals("") || ipass.equals("") || iconpass.equals("")) {
                    // Display a toast message if any field is empty
                    Toast.makeText(Register.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                } else {
                    if (ipass.equals(iconpass)) {
                        // Check if the user already exists
                        Boolean chekuser = DB.checkinputName(iname);
                        if (chekuser == false) {
                            // Insert the user's data into the database
                            Boolean insert = DB.insertdata(iname, ipass);
                            if (insert == true) {
                                // Display a success message and navigate to the dashboard activity
                                Toast.makeText(Register.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), Dashboard.class);
                                startActivity(intent);
                            } else {
                                // Display a failure message if registration fails
                                Toast.makeText(Register.this, "Registration failed", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            // Display a message if the user already exists
                            Toast.makeText(Register.this, "User already exists. Please sign in", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        // Display a message if the passwords do not match
                        Toast.makeText(Register.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        // Set click listener for the signtologin button
        signtologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Navigate to the login activity
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
            }
        });
    }

    // Method to navigate to the login activity
    public void gotosigntologin(View view) {
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }
}
