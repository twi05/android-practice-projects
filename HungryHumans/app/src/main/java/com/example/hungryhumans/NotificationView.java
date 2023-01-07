package com.example.hungryhumans;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class NotificationView extends AppCompatActivity {
TextView timerem;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_view);

        Intent pi=getIntent();
//        Date d = new Date();
//        d.setTime(pi.getLongExtra("notificationID", -1));
//        LocalTime lt1 = LocalTime.ofSecondOfDay((pi.getIntExtra("notificationID", 0)));
        int lt1 = pi.getIntExtra("notificationID", 0);
//        LocalTime lt2= LocalTime.now();
            int lt2 = LocalTime.now().getMinute();


//        LocalTime diff= lt2.minusSeconds(lt1.toSecondOfDay());
//        Log.d("difff" , String.valueOf(diff.getMinute()));
        Log.d("LT1" , String.valueOf(lt1));
        timerem = findViewById(R.id.timerem);
        timerem.setText( String.valueOf(30 - (lt2 - lt1)));
//String.valueOf(lt1.minus(Long.valueOf(String.valueOf(time2)), ChronoUnit.HOURS))
  //      String.valueOf(time2.until(lt1, ChronoUnit.HOURS)))
    }
}