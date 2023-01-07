package com.example.foodapp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class cart_items extends AppCompatActivity {

    Cart cartData;
    TextView textView;
    int id;
    LinearLayout.LayoutParams l;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_items);
        cartData = Cart.getCartData();

        View itemsInflater = findViewById(R.id.cartItemsContainer);

        renderCartItemCardsXMl(cartData.arr, itemsInflater);

        textView = findViewById(R.id.amounttopay);
        textView.setText("$" + Double.toString(calcTotalCost()));

    }

    public void renderCartItemCardsXMl(ArrayList<CartItem> arr, View v) {
        boolean check = false;
        for (CartItem c: arr) {
            if (c.getQuantity() > 0) {
                check = true;
                addCartItemCard(v, c);
            }
        }

        TextView t = findViewById(R.id.content);

        if (check) {

            t.setText("Your Cart");
        }
        else {
            t.setText("Your Cart is Empty");
        }
    }

    public double calcTotalCost() {
        double totalCost = 0;
        for (CartItem c: cartData.arr) {
            totalCost += c.getQuantity() * c.getPrice();
        }

        return totalCost;
    }

    public void addCartItemCard(View v, CartItem cartItem) {
        int id = cartItem.getId();
        String name = cartItem.getName();
        String[] ingredients = cartItem.getIngredients();
        boolean spicy = cartItem.isSpicy();
        boolean vegetarian = cartItem.isVegetarian();
        double price = cartItem.getPrice();
        String img = cartItem.getImg();
        int quantity = cartItem.getQuantity();

        GradientDrawable border = new GradientDrawable();
        border.setCornerRadius(15);
        border.setColor(0xFFFFFFFF);
        border.setStroke(1, 0xFF000000);

        LinearLayout louter = new LinearLayout(this);
        louter.setBackground(border);
        louter.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        louter.setOrientation(LinearLayout.VERTICAL);
        louter.setPadding(14, 14, 14, 14);

        myfunc(louter, id, name, vegetarian, price, quantity);

        ((LinearLayout) v).addView(louter);
    }

    public void passData(int id) {
//        intent = new Intent(this, activity_food_details.class);
//        bundle = new Bundle();
//        bundle.putString("id", Integer.toString(id));
//        intent.putExtras(bundle);
//        startActivity(intent);
    }

    public void myfunc(LinearLayout louter, int id, String name, boolean vegetarian, double price, int quantity) {

        GradientDrawable borderBtn = new GradientDrawable();
        borderBtn.setCornerRadius(15);
        borderBtn.setColor(Color.parseColor("#189AB4"));

        GradientDrawable qBtn = new GradientDrawable();
        qBtn.setCornerRadius(15);
        qBtn.setColor(Color.parseColor("#05445E"));


        LinearLayout linner1 = new LinearLayout(this);
        linner1.setWeightSum(1);
        linner1.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        linner1.setOrientation(LinearLayout.HORIZONTAL);

        LinearLayout linnerinner1 = new LinearLayout(this);
        linnerinner1.setPadding(20, 20, 20, 20);
        linnerinner1.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT));
        linnerinner1.setOrientation(LinearLayout.VERTICAL);

        TextView t = new TextView(this);
        t.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
//        t.setText("Food Menu");
        t.setText(name);
        t.setPadding(0, 20, 0, 20);
        t.setTextColor(Color.parseColor("#fdb501"));
        t.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 24);

        LinearLayout linnerinnerinner1 = new LinearLayout(this);
        linnerinnerinner1.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        linnerinnerinner1.setOrientation(LinearLayout.HORIZONTAL);

        LinearLayout Linnerinnerinnerinner1 = new LinearLayout(this);
        Linnerinnerinnerinner1.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        Linnerinnerinnerinner1.setOrientation(LinearLayout.VERTICAL);

        TextView t1 = new TextView(this);
        t1.setText("30");
        t1.setTextColor(Color.parseColor("#6A90B5"));
        t1.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20);

        TextView t2 = new TextView(this);
        t2.setText("minutes");
        t2.setTextColor(Color.parseColor("#DF6A90B5"));
        t2.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 14);

        Linnerinnerinnerinner1.addView(t1);
        Linnerinnerinnerinner1.addView(t2);

        ImageView iinnner = new ImageView(this);
        iinnner.setImageResource(R.drawable.split);
        iinnner.setPadding(30, 0, 30, 0);
        iinnner.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        LinearLayout Linnerinnerinnerinner2 = new LinearLayout(this);
        Linnerinnerinnerinner2.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));

        Linnerinnerinnerinner2.setOrientation(LinearLayout.VERTICAL);

        TextView t3 = new TextView(this);
        t3.setText(vegetarian ? "Veg" : "Non-Veg");
        t3.setTextColor(Color.parseColor("#6A90B5"));
        t3.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20);

        TextView t4 = new TextView(this);
        t4.setText("Type");
        t4.setTextColor(Color.parseColor("#DF6A90B5"));
        t4.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 14);

        Linnerinnerinnerinner2.addView(t3);
        Linnerinnerinnerinner2.addView(t4);

        linnerinnerinner1.addView(Linnerinnerinnerinner1);
        linnerinnerinner1.addView(iinnner);
        linnerinnerinner1.addView(Linnerinnerinnerinner2);


        linnerinner1.addView(t);
        linnerinner1.addView(linnerinnerinner1);

        LinearLayout linnerinner2 = new LinearLayout(this);
        linnerinner2.setWeightSum(1);
        linnerinner2.setGravity(Gravity.CENTER);
        linnerinner2.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        linnerinner2.setOrientation(LinearLayout.HORIZONTAL);

        ImageView i = new ImageView(this);
        i.setImageResource(R.drawable.food1);
        i.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        linnerinner2.addView(i);

        linner1.addView(linnerinner1);
        linner1.addView(linnerinner2);

        LinearLayout linner3 = new LinearLayout(this);
        linner3.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        linner3.setOrientation(LinearLayout.HORIZONTAL);
        linner3.setPadding(20, 0, 20, 40);
        linner3.setGravity(Gravity.CENTER);

        TextView textPrice = new TextView(this);
        textPrice.setText("$" + Double.toString(price));
        textPrice.setTextSize(20);
        textPrice.setTextColor(Color.parseColor("#000000"));


        LinearLayout linner3inner = new LinearLayout(this);
        l = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        linner3inner.setLayoutParams(l);
        l.setMargins(80, 0, 0, 0);
        linner3inner.setOrientation(LinearLayout.HORIZONTAL);

        TextView q = new TextView(this);
        q.setText("Quantity: ");
        q.setTextSize(20);
        q.setTextColor(Color.parseColor("#000000"));

        TextView qValue = new TextView(this);
        qValue.setText(Integer.toString(quantity));
        qValue.setTextSize(20);
        qValue.setTextColor(Color.parseColor("#000000"));

        Button incBtn = new Button(this);
        incBtn.setText("+");
        l = new LinearLayout.LayoutParams(80, 80);
        l.setMargins(20, 0, 0, 0);
        incBtn.setLayoutParams(l);
        incBtn.setBackground(qBtn);

        incBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (CartItem c: cartData.arr) {
                    if (c.getId() == id) {
                        c.incrementQuantity();
//                        myfunc(louter, id, name, vegetarian, price, c.getQuantity());
                        qValue.setText(Integer.toString(c.getQuantity()));
                        textView = findViewById(R.id.amounttopay);
                        textView.setText("$" + Double.toString(calcTotalCost()));
                    }
                }
            }
        });

        Button decBtn = new Button(this);
        decBtn.setText("-");
        l = new LinearLayout.LayoutParams(80, 80);
        l.setMargins(20, 0, 30, 0);
        decBtn.setLayoutParams(l);
        decBtn.setBackground(qBtn);

        decBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (CartItem c: cartData.arr) {
                    if (c.getId() == id) {
                        c.decrementQuantity();
//                        myfunc(louter, id, name, vegetarian, price, c.getQuantity());
                        qValue.setText(Integer.toString(c.getQuantity()));
                        textView.setText("$" + Double.toString(calcTotalCost()));
                    }
                }
            }
        });

        linner3inner.addView(q);
        linner3inner.addView(decBtn);
        linner3inner.addView(qValue);
        linner3inner.addView(incBtn);

        linner3.addView(textPrice);
        linner3.addView(linner3inner);


        Button btn = new Button(this);
        btn.setBackgroundColor(Color.parseColor("#189AB4"));
//        btn.setText("View Details");
        btn.setBackground(borderBtn);
        btn.setText("View food details");
        btn.setId(id);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                passData(id);
            }
        });


        louter.addView(linner1);
        louter.addView(linner3);
        louter.addView(btn);

    }

}