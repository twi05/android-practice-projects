package com.example.bloodregistration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
Button signup;
EditText name, password, bloodgrp;
String iName, iPassword, ibloodgrp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = findViewById(R.id.nameinput);
        password = findViewById(R.id.passwordinput);
        bloodgrp = findViewById(R.id.bloodgrpinput);
        signup = findViewById(R.id.signupbtn);

        SharedPreferences sh = getSharedPreferences("registrationData", MODE_PRIVATE);
        if(sh.getString("username", null) !=null)
        {
            Intent i = new Intent(this, Welcome_page.class);
            startActivity(i);
        }
    }
public  void signupclicked(View view){
    iName = name.getText().toString();
    iPassword = password.getText().toString();
    ibloodgrp = bloodgrp.getText().toString();

    SharedPreferences sp = getSharedPreferences("registrationData" , MODE_PRIVATE);
    SharedPreferences.Editor myEdit = sp.edit();

    myEdit.putString("username",iName);
    myEdit.putString("password",iPassword);
    myEdit.putString("bloodgrp",ibloodgrp);
    myEdit.commit();
    Intent i = new Intent(this, Welcome_page.class);
    startActivity(i);
}


}