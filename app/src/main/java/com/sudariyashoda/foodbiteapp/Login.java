package com.sudariyashoda.foodbiteapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

        EditText inputName,inputPassword;
        Button Login;
        DBHelper DB;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_login);

            inputName = (EditText) findViewById(R.id.loginName);
            inputPassword = (EditText) findViewById(R.id.LoginPassword);
            Login = (Button) findViewById(R.id.Login);
            DB = new DBHelper(this);

            Login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String iname = inputName.getText().toString();
                    String ipass = inputPassword.getText().toString();

                    if(iname.equals("")|| ipass.equals(""))
                        Toast.makeText(Login.this,"please enter all the fields",Toast.LENGTH_SHORT).show();
                    else {
                        Boolean checkuserpass = DB.checkinputNameinputPassword(iname,ipass);
                        if(checkuserpass == true){
                            Toast.makeText(Login.this,"SignIn Successfuly",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(),Dashboard.class);
                            startActivity(intent);
                        }else {
                            Toast.makeText(Login.this,"Invalid Credentials",Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });
        }
        public void gotosignup (View view){
            Intent intent = new Intent (this, Register.class);
            startActivity(intent);
        }
        public void gotodashboad (View view){
            Intent intent = new Intent (this, Dashboard.class);
            startActivity(intent);
        }

}