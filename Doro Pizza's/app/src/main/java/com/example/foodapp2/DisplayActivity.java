package com.example.foodapp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.media.Image;
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

public class DisplayActivity extends AppCompatActivity {

    FoodData foodData;
    Bundle bundle;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        foodData = FoodData.getFoodData();

        View itemsInflater = findViewById(R.id.itemsContainer);


        // add food items
        foodData.addFoodItem(new FoodItem(0, "Margherita", "", new String[] {"tomato sauce", "mozzarella" }, false, true, 17, "https://i.imgur.com/8B8YLOo.jpg"));
        foodData.addFoodItem(new FoodItem(1, "Pepperoni", "", new String[] {"tomato sauce", "mozzarella", "double pepperoni"}, false, true, 20, "https://i.imgur.com/OHHctnf.jpg"));
        foodData.addFoodItem(new FoodItem(2, "Rome", "", new String[] {"tomato sauce", "mozzarella", "ham", "mushrooms", "beef cubes" }, false, false, 25.75, "https://i.imgur.com/3ZTwCfz.png"));
        foodData.addFoodItem(new FoodItem(3, "American Spicy", "", new String[] {"tomato sauce", "mozzarella", "pepperoni", "tomatoes", "green pepper", "red onion", "jalapenos", "Samourai sauce" }, true, false, 30.25, "https://i.imgur.com/dyoOLCO.png"));
        foodData.addFoodItem(new FoodItem(4, "Quattro Stagioni", "", new String[] {"tomato sauce", "mozzarella", "ham", "pepperoni", "mushrooms", "green pepper" }, false, false, 27.25, "https://i.imgur.com/wOEuXuV.jpg"));


        renderFoodCardsXMl(foodData.arr, itemsInflater);

    }

    public void renderFoodCardsXMl(ArrayList<FoodItem> arr, View v) {

        for (FoodItem f: arr) {
            addFoodCard(v,f);
        }
    }

    public void addFoodCard(View v, FoodItem foodItem) {
        int id = foodItem.getId();
        String name = foodItem.getName();
        String description = foodItem.getDescription();
        String[] ingredients = foodItem.getIngredients();
        boolean spicy = foodItem.isSpicy();
        boolean vegetarian = foodItem.isVegetarian();
        double price = foodItem.getPrice();
        String img = foodItem.getImg();

        GradientDrawable border = new GradientDrawable();
        border.setCornerRadius(15);
        border.setColor(0xFFFFFFFF); //white background
        border.setStroke(1, 0xFF000000); //black border with full opacity

        LinearLayout louter = new LinearLayout(this);
        louter.setBackground(border);
        louter.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        louter.setOrientation(LinearLayout.VERTICAL);
        louter.setPadding(14, 14, 14, 14);

        myfunc(louter, id, name, vegetarian, price);

        ((LinearLayout) v).addView(louter);
    }

    public void passData(int id) {
        intent = new Intent(this, activity_food_details.class);
        bundle = new Bundle();
        bundle.putString("id", Integer.toString(id));
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void addCustomHorizontalView(View v, String label, String value, int id) {
        LinearLayout l1 = new LinearLayout(this);
        l1.setOrientation(LinearLayout.HORIZONTAL);

        TextView name_label = new TextView(this);
        name_label.setText(label + ": ");
        name_label.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT));
        ((LinearLayout) l1).addView(name_label);

        TextView nameValue = new TextView(this);
        nameValue.setId(id);
        nameValue.setText(value);
        nameValue.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT));
        ((LinearLayout) l1).addView(nameValue);

        ((LinearLayout) v).addView(l1);
    }


    public void myfunc(LinearLayout louter, int id, String name, boolean vegetarian, double price) {


        GradientDrawable borderBtn = new GradientDrawable();
        borderBtn.setCornerRadius(15);
        borderBtn.setColor(Color.parseColor("#189AB4")); //white background

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

        TextView textPrice = new TextView(this);
        textPrice.setText("$" + Double.toString(price));
        textPrice.setTextSize(20);
        textPrice.setTextColor(Color.parseColor("#000000"));

        linner3.addView(textPrice);


        Button btn = new Button(this);
        btn.setBackgroundColor(Color.parseColor("#189AB4"));
//        btn.setText("View Details");
        btn.setBackground(borderBtn);
        btn.setText("View food details");
        btn.setId(id);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                passData(id);
            }
        });


        louter.addView(linner1);
        louter.addView(linner3);
        louter.addView(btn);

    }





}