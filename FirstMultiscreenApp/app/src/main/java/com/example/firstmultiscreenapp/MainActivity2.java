package com.example.firstmultiscreenapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.text.BreakIterator;

public class MainActivity2 extends AppCompatActivity{
String name, age, mob, gender, freq, interest;
    TextView data;
    TextView ratingData;
    TextView ratingtext;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout2);
        data = findViewById(R.id.data);

        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            return;
        }

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
             name = bundle.getString("name");
             age = bundle.getString("age");
             mob = bundle.getString("mob");
             gender = bundle.getString("gender");
             freq = bundle.getString("freq");
             interest = bundle.getString("interest");

            Toast.makeText(this, "Input Values are : \n Name: "  +name +"\n" + "Age: " + age +"\n" + "Mobile NO.: " + mob +"\n"+  "Gender: " +gender +"\n" +"Freq: " + freq +"\n" +  "Interest: " + interest , Toast.LENGTH_SHORT).show();

            data.setText("Name: "  +name +"\n" + "Age: " + age +"\n" + "Mobile NO.: " + mob +"\n"+  "Gender: " +gender +"\n" +"Freq: " + freq +"\n" +  "Interest: " + interest);

        }
        ratingtext= findViewById(R.id.ratingtext);
        ratingData = findViewById(R.id.ratingdata);
        ratingData.setText("Give Feedback to show here");
    }
    public void openCall(View v) {
        String phone = "+34666777888";
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null));
        startActivity(intent);

    }
    public void openMap(View v){
        Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                Uri.parse("http://maps.google.com/maps?saddr=20.344,34.34&daddr=20.5666,45.345"));
        startActivity(intent);
    }
    public void openMail(View v){
        String mailto = "mailto:bob@example.org" +
                "?cc=" + "inshorts@newsletter.com" +
                "&subject=" + Uri.encode("I have query regarding...") +
                "&body=" + Uri.encode("Hello I am sending this mail to ans you about ...");

        Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
        emailIntent.setData(Uri.parse(mailto));

        try {
            startActivity(emailIntent);
        } catch (ActivityNotFoundException e) {
            //TODO: Handle case where no email app is available
        }
        startActivity(emailIntent);
    }


    public void openGplay(View view) {
        final String appPackageName = getPackageName();
        try {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://search?q=NewsLetter&c=apps")));
        } catch (android.content.ActivityNotFoundException anfe) {

        }
    }

    public void forfeedback(View view) {
        final AlertDialog.Builder popDialog = new AlertDialog.Builder(this);

        LinearLayout linearLayout = new LinearLayout(this);
        final RatingBar rating = new RatingBar(this);

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        rating.setLayoutParams(lp);
        rating.setNumStars(5);
        rating.setStepSize(1);

        //add ratingBar to linearLayout
        linearLayout.addView(rating);


        popDialog.setIcon(android.R.drawable.btn_star_big_on);
        popDialog.setTitle("Add Rating: ");

        //add linearLayout to dailog
        popDialog.setView(linearLayout);



        rating.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                System.out.println("Rated val:"+v);
//                ratingData.setText("Give Feedback to show here" + v);
            }
        });



        // Button OK
        popDialog.setPositiveButton(android.R.string.ok,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                ratingtext.setText("Thankyou for rating ");
                                ratingData.setText(String.valueOf(rating.getProgress()) + " 🌟");
                                dialog.dismiss();
                            }

                        })

                // Button Cancel
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

        popDialog.create();
        popDialog.show();

    }

}
