package com.example.bloodregistration;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

public class secondpage extends AppCompatActivity {
Button b1;
TimePicker timePicker ;
DatePicker datePicker ;
EditText mobileno;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.secondpage);

        timePicker = findViewById(R.id.picktime);
        datePicker = findViewById((R.id.date));
mobileno = findViewById(R.id.inputmobileno);
if(ContextCompat.checkSelfPermission(this,
                Manifest.permission.SEND_SMS)!= PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this, new
                    String[]{Manifest.permission.SEND_SMS,Manifest.permission.SEND_SMS},100);
        }
        b1=findViewById(R.id.confirmslot);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int hour = timePicker.getCurrentHour();
                int minute = timePicker.getCurrentMinute();
                int year = datePicker.getYear();
                int month = datePicker.getMonth();
                int date = datePicker.getDayOfMonth();

                SmsManager sms = SmsManager.getDefault();
                String messagesend = "Your Blood Donation slot has been successfully booked on date: "+ date +"/" + month + "/" + year +". And time is " + hour +": "+ minute;
                sms.sendTextMessage("+91" + String.valueOf(mobileno.getText()), null, messagesend, null, null);

            }
        });
        if(ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_SMS)!= PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this, new
                    String[]{Manifest.permission.READ_SMS,Manifest.permission.READ_SMS},100);
        }
        if(ContextCompat.checkSelfPermission(this,
                Manifest.permission.RECEIVE_SMS)!= PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this, new
                            String[]{Manifest.permission.RECEIVE_SMS,Manifest.permission.RECEIVE_SMS},100);
        }


}

}