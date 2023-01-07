package com.example.firstmultiscreenapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    String[] freqs;
    ListView myList;
    EditText name;

    Spinner s1 ;
    TextView tvDate;
    Button btPickDate;
    int ageInteger = 0;
    Button submitbtn2;

    String[] listContent = {

            "Politics",
            "Cooking"
            ,"arts",
            "Movies"
            ,"Reading Books"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);
        freqs = getResources().getStringArray(R.array.freq_array);
        s1 = (Spinner) findViewById(R.id.spinner1);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, freqs);
        s1.setAdapter(adapter);



        myList = (ListView)findViewById(R.id.list);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>
                (this,android.R.layout.simple_list_item_multiple_choice, listContent);
        myList.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        myList.setAdapter(adapter2);

        tvDate = findViewById(R.id.ageCalculate);
        btPickDate = findViewById(R.id.btPickDate);

        submitbtn2 = findViewById(R.id.submitbtn);

        btPickDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                com.example.firstmultiscreenapp.DatePicker mDatePickerDialogFragment;
                mDatePickerDialogFragment = new com.example.firstmultiscreenapp.DatePicker();
                mDatePickerDialogFragment.show(getSupportFragmentManager(), "DATE PICK");
            }

        });


        CheckBox    checkbox =  findViewById(R.id.chk1);
        submitbtn2.setEnabled(false);

        checkbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkbox.isEnabled()){
                    submitbtn2.setEnabled(true);
                }
                else {
                    submitbtn2.setEnabled(false);
                }
            }
            public void onCheckedChanged(CompoundButton compoundButton, boolean b)
            {
                if (checkbox.isEnabled())
                {
                    submitbtn2.setEnabled(false);
                }
            }
        });
    }

    @Override
    public void onDateSet(android.widget.DatePicker datePicker, int year, int month, int dayOfMonth) {
        Calendar mCalendar = Calendar.getInstance();
        mCalendar.set(Calendar.YEAR, year);
        mCalendar.set(Calendar.MONTH, month);
        mCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        String selectedDate = DateFormat.getDateInstance(DateFormat.FULL).format(mCalendar.getTime());

        Calendar today = Calendar.getInstance();

        ageInteger = today.get(Calendar.YEAR) - mCalendar.get(Calendar.YEAR);

        if (today.get(Calendar.MONTH) == mCalendar.get(Calendar.MONTH)) {

            if (today.get(Calendar.DAY_OF_MONTH) < mCalendar.get(Calendar.DAY_OF_MONTH)) {

                ageInteger = ageInteger - 1;
            }

        } else if (today.get(Calendar.MONTH) < mCalendar.get(Calendar.MONTH)) {

            ageInteger = ageInteger - 1;

        }
        String s = String.valueOf(ageInteger);
        tvDate.setText(s);
    }

    public void openActivity(View v)
    {

        EditText mobinp, emailinp;

        name = findViewById(R.id.nameinput);
        String nameText = name.getText().toString();
        emailinp = findViewById(R.id.emailinput);
        String emailText = emailinp.getText().toString();
        mobinp = findViewById(R.id.mobinput);
        String mobText = mobinp.getText().toString();

        String selected_freq = s1.getSelectedItem().toString();

        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radiogrp);
        int genid=radioGroup.getCheckedRadioButtonId();
        RadioButton radioButton = (RadioButton) findViewById(genid);
        String gender=radioButton.getText().toString();

//        Toast.makeText(this, "Value of name input is :" +nameText, Toast.LENGTH_SHORT).show();
        String selected = "";
        int cntChoice = myList.getCount();
        SparseBooleanArray sparseBooleanArray = myList.getCheckedItemPositions();
        for(int i = 0; i < cntChoice; i++){
            if(sparseBooleanArray.get(i)) {
                selected += myList.getItemAtPosition(i).toString() + "\n";
            }
        }



        Intent intent = new Intent(getApplicationContext(), MainActivity2.class);
        Bundle bundle = new Bundle();
        bundle.putString("name", nameText);

        bundle.putString("email", emailText);
        bundle.putString("age", String.valueOf(ageInteger));
        bundle.putString("mob",mobText );

        bundle.putString("gender",gender);
        bundle.putString("freq",selected_freq);
        bundle.putString("interest",selected);
        intent.putExtras(bundle);

        startActivity(intent);

    }

}


