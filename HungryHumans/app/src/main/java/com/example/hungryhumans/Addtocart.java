package com.example.hungryhumans;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.camera2.TotalCaptureResult;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;

public class Addtocart extends AppCompatActivity {
TextView itemDetail;
    ArrayList<PizzaClass> addedTocart;
    TextView totalamount;
    final String CHANNEL_ID="channel1";
    Button placeorder;
    double totalAmount = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addtocartpage);
        itemDetail = findViewById(R.id.itemsdetail);
        totalamount = findViewById(R.id.totalamount);
        totalamount.setVisibility(View.GONE);
        // calling method to load data
        // from shared prefs.
        loadData();
        TinyDB tinydb = new TinyDB(this);
        ArrayList<Object> playerObjects = tinydb.getListObject("addedtocart", PizzaClass.class);
//        ArrayList<PizzaClass> players = new ArrayList<PizzaClass>();

        for(Object objs : playerObjects){
            addedTocart.add((PizzaClass)objs);
        }
//        String datastring = tinydb.getString("nameKey");
//        itemDetail.setText(datastring); ;;
//        addedTocart = (ArrayList<PizzaClass>) getIntent().getSerializableExtra("data");
        if(addedTocart!=null) {
//            itemDetail.setText(String.valueOf( p.name));

            LinearLayout llMain = findViewById(R.id.rlMain);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT
            );

for(PizzaClass p :addedTocart){

    itemDetail.setVisibility(View.GONE) ;;
    TextView name = new TextView(this);
    TextView amountdetail = new TextView(this);
    TextView customisation = new TextView(this);

    name.setText("Name : " + String.valueOf(p.name));
    amountdetail.setText("Amount : " + String.valueOf(p.amount));
    customisation.setText("Customisations :" + String.valueOf(p.customisation));
    name.setLayoutParams(params);
    amountdetail.setLayoutParams(params);
    customisation.setLayoutParams(params);
    llMain.addView(name);
    llMain.addView(amountdetail);
    llMain.addView(customisation);
    totalAmount+=p.amount;
}

            totalamount.setVisibility(View.VISIBLE);
    totalamount.setText("Total Amount : " + totalAmount);
        }



        placeorder=findViewById(R.id.placeorder);
        placeorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    createNotification();
                }
            }
        });

    }


    // Fetch the stored data in onResume()
    // Because this is what will be called
    // when the app opens again
//    @Override
//    protected void onResume() {
//        super.onResume();
//
//        // Fetching the stored data
//        // from the SharedPreference
//        SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_PRIVATE);
//
//        String s1 = sh.getString("name", "");
//        int a = sh.getInt("age", 0);
//
//
//
//    }
    private void loadData() {
        // method to load arraylist from shared prefs
        // initializing our shared prefs with name as
        // shared preferences.
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);

        // creating a variable for gson.
        Gson gson = new Gson();

        // below line is to get to string present from our
        // shared prefs if not present setting it as null.
        String json = sharedPreferences.getString("shared_preferences", null);

        // below line is to get the type of our array list.
        Type type = new TypeToken<ArrayList<PizzaClass>>() {}.getType();

        // in below line we are getting data from gson
        // and saving it to our array list
        addedTocart = gson.fromJson(json, type);

        // checking below if the array list is empty or not
        if (addedTocart == null) {
            // if the array list is empty
            // creating a new array list.
            addedTocart = new ArrayList<>();
        }
    }

    private void saveData() {
        // method for saving the data in array list.
        // creating a variable for storing data in
        // shared preferences.
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);

        // creating a variable for editor to
        // store data in shared preferences.
        SharedPreferences.Editor editor = sharedPreferences.edit();

        // creating a new variable for gson.
        Gson gson = new Gson();

        // getting data from gson and storing it in a string.
        String json = gson.toJson(addedTocart);

        // below line is to save data in
        // prefs in the form of string.
        editor.putString("itemsincart", json);

        // below line is to apply changes
        // and save data in shared prefs.
        editor.apply();

        // after saving data we are displaying a toast message.
//        Toast.makeText(this, "Saved Array List to Shared preferences. ", Toast.LENGTH_SHORT).show();

    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    void createNotification()
    {
        NotificationManager
                nm=(NotificationManager)this.getSystemService(NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "FoodApp";
            String description = "Foodapp notifcation.";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new
                    NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            nm.createNotificationChannel(channel);
        }
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this,CHANNEL_ID)
                        .setSmallIcon(R.drawable.ic_baseline_notifications_24)
                        .setContentTitle("Your order has been placed successfully.")
                        .setContentText("You have to pay Rs " + totalAmount + ". Your order will be delivered in 30 mins")
                ;
        builder.setAutoCancel(true);
        Intent notificationIntent = new Intent(this,
                NotificationView.class);
        int time = LocalTime.now().getMinute();



        notificationIntent.putExtra("notificationID", time);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
                notificationIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(contentIntent);
        nm.notify(0,builder.build());
    }
}