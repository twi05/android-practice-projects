package com.example.foodapp2;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class activity_food_details extends AppCompatActivity {

    FoodData foodData;
    Cart cart;
    FoodItem f;
    Bundle bundle;
    TextView textView;
    Button btn;
    int id;
    ImageView imgView;
    ImageView backButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_details);
        foodData = FoodData.getFoodData();
        cart = Cart.getCartData();
        bundle = getIntent().getExtras();
        btn = findViewById(R.id.button);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        if (bundle != null) {
            id = Integer.parseInt(bundle.getString("id"));
        }

        setFoodData();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (CartItem c: cart.arr) {
                    if (c.getId() == id && c.getQuantity() == 0) {
                        c.incrementQuantity();

                    }
                }
                sendDataToCart(f);
                Toast.makeText(getApplicationContext(),"Added to cart",Toast.LENGTH_SHORT).show();
            }
        });

        imgView = findViewById(R.id.imageView4);

        imgView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getApplicationContext(), cart_items.class));
            }
        });
         backButton = this.findViewById(R.id.backbutton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void sendDataToCart(FoodItem foodItem) {
        int id = foodItem.getId();
        String name = foodItem.getName();
        String description = foodItem.getDescription();
        String[] ingredients = foodItem.getIngredients();
        boolean spicy = foodItem.isSpicy();
        boolean vegetarian = foodItem.isVegetarian();
        double price = foodItem.getPrice();
        String img = foodItem.getImg();

        CartItem c = new CartItem(id, name, ingredients, spicy, vegetarian, price, img);
        bundle = new Bundle();
        cart.addCartItem(c);


    }

    private void setFoodData() {
        f = this.getFoodItem(id);
        textView = findViewById(R.id.name);
        textView.setText(f.getName());
        textView = findViewById(R.id.price);
        textView.setText("$" + Double.toString(f.getPrice()));
        textView = findViewById(R.id.textView8);
        textView.setText(String.join(", ", f.getIngredients()));
    }

    private FoodItem getFoodItem(int id) {
        for (FoodItem f : foodData.arr) {
            if (id == f.getId()) {
                return f;
            }
        }

        return null;
    }
}