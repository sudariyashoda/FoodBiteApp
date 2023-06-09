package com.sudariyashoda.foodbiteapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {
        EditText inputName,inputPassword,inputConfirmpassword;
        Button signUp,signtologin;
        DBHelper DB;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_register);

            inputName = (EditText) findViewById(R.id.inputName);
            inputPassword = (EditText) findViewById(R.id.inputPassword);
            inputConfirmpassword=(EditText) findViewById(R.id.inputConfirmpassword);
            signtologin=(Button) findViewById(R.id.signtologin);
            signUp=(Button) findViewById(R.id.signUp);
            DB = new DBHelper(this);

            signUp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String iname = inputName.getText().toString();
                    String ipass = inputPassword.getText().toString();
                    String iconpass = inputConfirmpassword.getText().toString();

                    if (iname.equals("")||ipass.equals("")||iconpass.equals(""))
                        Toast.makeText(Register.this,"please enter all the fields",Toast.LENGTH_SHORT).show();
                    else {
                        if(ipass.equals(iconpass)){
                            Boolean chekuser = DB.checkinputName(iname);
                            if (chekuser == false) {
                                Boolean insert =DB.insertdata(iname,ipass);
                                if(insert==true){
                                    Toast.makeText(Register.this,"Registered succersfully",Toast.LENGTH_SHORT).show();
                                    Intent intent=new Intent(getApplicationContext(),Dashboard.class);
                                    startActivity(intent);
                                }
                                else {
                                    Toast.makeText(Register.this,"Registration Failled",Toast.LENGTH_SHORT).show();
                                }
                            }
                            else {
                                Toast.makeText(Register.this,"User already exits. Please SignIn",Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(Register.this,"Password not matching",Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });
            signtologin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(getApplicationContext(),Login.class);
                    startActivity(intent);

                }
            });
        }
        public void gotosigntologin (View view){
            Intent intent = new Intent (this, Login.class);
            startActivity(intent);
        }
}