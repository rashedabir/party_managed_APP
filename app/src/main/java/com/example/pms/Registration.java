package com.example.pms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Registration extends AppCompatActivity {

    EditText username, password, confirmPassword;
    String uname, pass, confirmPass;
    MyDatabaseHelper mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        username = findViewById(R.id.uname);
        password = findViewById(R.id.upass);
        confirmPassword = findViewById(R.id.uconfirmpass);
        mydb = new MyDatabaseHelper(this);
    }

    public void register(View view) {
        uname = username.getText().toString();
        pass = password.getText().toString();
        confirmPass = confirmPassword.getText().toString();

        if(uname.equals("") || pass.equals("") || confirmPass.equals("")){
            Toast.makeText(Registration.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
        }
        else {
            if(pass.equals(confirmPass)){
                Boolean checkuser = mydb.checkUsername(uname);
                if(checkuser == false){
                    Boolean insert = mydb.insertUser(uname, pass);
                    if(insert == true){
                        Toast.makeText(Registration.this, "Registration Complete", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(Registration.this, MainActivity.class);
                        startActivity(i);
                    }
                    else{
                        Toast.makeText(Registration.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(Registration.this, "User already exists", Toast.LENGTH_SHORT).show();
                }
            }
            else {
                Toast.makeText(Registration.this, "Both password doesn't match", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void loginPage(View view) {
        Intent i = new Intent(Registration.this, MainActivity.class);
        startActivity(i);
    }
}