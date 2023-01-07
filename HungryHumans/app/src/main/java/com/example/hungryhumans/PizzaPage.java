package com.example.hungryhumans;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;

public class PizzaPage extends AppCompatActivity {
    private int contextViewId;
    ViewGroup pizza1 ;
    ViewGroup pizza2 ;
    TextView customisationData;
   static ArrayList<String> data;
    static ArrayList<PizzaClass> addedTocart=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pizzapage);
//addedTocart = new ArrayList<PizzaClass>();
//        initializearraylist();
//        saveData();
        customisationData = findViewById(R.id.customisationData);
       data = (ArrayList<String>) getIntent().getSerializableExtra("customizeItems");
        if(data!=null) {
            customisationData.setText(String.valueOf(data));
        }

// calling this activity's function to
        // use ActionBar utility methods
        ActionBar actionBar = getSupportActionBar();

        // providing title for the ActionBar
        actionBar.setTitle("Hungry Humans");

        // methods to display the icon in the ActionBar
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        pizza1 = findViewById(R.id.pizza1context);
        registerForContextMenu(pizza1);
        pizza2 = findViewById(R.id.pizza2context);
        registerForContextMenu(pizza2);
//        createPopupMenu(pizza2);
        pizza1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(PizzaPage.this, v);
                popupMenu.getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        PizzaClass p =  new PizzaClass("Green Chilli", 500, data);
                        addedTocart.add(p);
saveData();

                        return true;
                    }
                });
                // Showing the popup menu
                popupMenu.show();
            }
        });
        pizza2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(PizzaPage.this, v);
                popupMenu.getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        PizzaClass p =  new PizzaClass("Chicago  Pizza", 1500, data);
                        addedTocart.add(p);
                        saveData();

                        return true;
                    }
                });
                // Showing the popup menu
                popupMenu.show();
            }
        });

    }
public void initializearraylist(){
    TinyDB tinydb = new TinyDB(this);
    addedTocart= new ArrayList<PizzaClass>();
    ArrayList<Object> playerObjects = new ArrayList<Object>();

    for(PizzaClass a : addedTocart){
        playerObjects.add((Object)a);
    }

    tinydb.putListObject("addedtocart", playerObjects);
    }
//
//}
//    protected void onPause() {
//        super.onPause();
//
//        // Creating a shared pref object
//        // with a file name "MySharedPref"
//        // in private mode
//        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
//        SharedPreferences.Editor myEdit = sharedPreferences.edit();
//
//        // write all the data entered by the user in SharedPreference and apply
//        myEdit.putString("name", name.getText().toString());
//        myEdit.putInt("age", Integer.parseInt(age.getText().toString()));
//        myEdit.apply();
//    }

public void createPopupMenu(View v){
    PopupMenu popupMenu = new PopupMenu(PizzaPage.this, v);
    popupMenu.getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());
    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            // Toast message on menu item clicked
            Toast.makeText(PizzaPage.this, "You Clicked " + menuItem.getTitle(), Toast.LENGTH_SHORT).show();
            return true;
        }
    });
    // Showing the popup menu
    popupMenu.show();
}

    public boolean onCreateOptionsMenu( Menu menu ) {

        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(0, v.getId(), 0, "Customize");
        menu.add(0, v.getId(), 0, "Add to cart");
        contextViewId = v.getId();
    }
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (item.getTitle() == "Customize") {
            Intent I = new Intent(PizzaPage.this, customizepage.class);

            startActivity(I);
        }
        if (item.getTitle() == "Add to cart") {
            Intent I = new Intent(PizzaPage.this, Addtocart.class);
            PizzaClass p =  new PizzaClass("Green Chilli", 500, data);
           addedTocart.add(p);
            I.putExtra("data", addedTocart);
            saveData();
            startActivity(I);
        }
        else {
            return  false;
        }
        return true;
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("myArrayList", addedTocart);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        addedTocart = savedInstanceState.getParcelableArrayList("myArrayList");
    }



    private void saveData() {
//        TinyDB tinydb = new TinyDB(this);
//        tinydb.putList("MyUsers", addedTocart);
//        tinydb.putString("nameKey", "John");
        // method for saving the data in array list.
        // creating a variable for storing data in
        // shared preferences.
        ArrayList<Object> playerObjects = new ArrayList<Object>();

        for(PizzaClass a : addedTocart){
            playerObjects.add((Object)a);
        }

        TinyDB tinydb = new TinyDB(this);
        tinydb.putListObject("addedtocart", playerObjects);
        SharedPreferences sharedPreferences = getSharedPreferences("shared_preferences", MODE_PRIVATE);

        // creating a variable for editor to
        // store data in shared preferences.
        SharedPreferences.Editor editor = sharedPreferences.edit();

        // creating a new variable for gson.
        Gson gson = new Gson();

        // getting data from gson and storing it in a string.
        String json = gson.toJson(addedTocart);

        // below line is to save data in shared
        // prefs in the form of string.
        editor.putString("itemsincart", json);

        // below line is to apply changes
        // and save data in shared prefs.
        editor.apply();

        // after saving data we are displaying a toast message.
        Toast.makeText(this, "Saved Array List to Shared preferences. ", Toast.LENGTH_SHORT).show();
    }
}