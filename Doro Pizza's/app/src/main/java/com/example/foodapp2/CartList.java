package com.example.foodapp2;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class CartList extends AppCompatActivity {

    Cart cartData;
    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_items);
        cartData = Cart.getCartData();
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        bundle = getIntent().getExtras();

        if (bundle != null) {

        }

    }
}