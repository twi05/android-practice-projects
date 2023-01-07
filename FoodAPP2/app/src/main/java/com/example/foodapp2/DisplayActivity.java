package com.example.foodapp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class DisplayActivity extends AppCompatActivity {

    TextView v1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        v1 =(TextView) findViewById(R.id.textview);

        Intent intent = getIntent();

        String v = intent.getStringExtra("message_key");
        v1.setText(v);

    }


}