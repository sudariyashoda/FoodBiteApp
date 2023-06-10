package com.sudariyashoda.foodbiteapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    // Declare variables
    EditText inputName, inputPassword;
    Button Login;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initialize variables with corresponding views
        inputName = (EditText) findViewById(R.id.loginName);
        inputPassword = (EditText) findViewById(R.id.LoginPassword);
        Login = (Button) findViewById(R.id.Login);
        DB = new DBHelper(this);

        // Set click listener for the Login button
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Retrieve entered name and password
                String iname = inputName.getText().toString();
                String ipass = inputPassword.getText().toString();

                // Check if any field is empty
                if (iname.equals("") || ipass.equals(""))
                    Toast.makeText(Login.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else {
                    // Check if the entered name and password are valid
                    Boolean checkuserpass = DB.checkinputNameinputPassword(iname, ipass);
                    if (checkuserpass == true) {
                        Toast.makeText(Login.this, "Sign In Successfully", Toast.LENGTH_SHORT).show();
                        // Redirect to the Dashboard activity
                        Intent intent = new Intent(getApplicationContext(), Dashboard.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(Login.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    // Method to navigate to the SignUp activity
    public void gotosignup(View view) {
        Intent intent = new Intent(this, Register.class);
        startActivity(intent);
    }

    // Method to navigate to the Dashboard activity
    public void gotodashboad(View view) {
        Intent intent = new Intent(this, Dashboard.class);
        startActivity(intent);
    }
}
