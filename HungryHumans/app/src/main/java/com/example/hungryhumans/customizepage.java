package com.example.hungryhumans;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import java.util.ArrayList;

public class customizepage extends AppCompatActivity {
Button cartBtn;
CheckBox capcicum,extracheese;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customizepage);

        cartBtn = findViewById(R.id.backtopizzapage);
        cartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<String> customizeItems = new ArrayList<>();
                if(capcicum.isChecked()){
                    customizeItems.add("capcicum");
                }if(extracheese.isChecked()){
                    customizeItems.add("extracheese");
                }
                Intent I = new Intent(customizepage.this, PizzaPage.class);
                I.putExtra("customizeItems",customizeItems);
                startActivity(I);
            }
        });

        capcicum = findViewById(R.id.capcicum);
        extracheese = findViewById(R.id.extracheese);
    }
}