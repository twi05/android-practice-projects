package com.example.foodapp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


    //    ListView superListView;
    Button b1;

    RadioGroup rg;
    RadioButton t1, t2, t3, t4, t5, t6, t7, t8;
    String S;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b1 = findViewById(R.id.button);
        rg = findViewById(R.id.radio);
        t1 = findViewById(R.id.table1);
        t2 = findViewById(R.id.table2);
        t3 = findViewById(R.id.table3);
        t4 = findViewById(R.id.table4);
        t5 = findViewById(R.id.table5);
        t6 = findViewById(R.id.table6);
        t7 = findViewById(R.id.table7);
        t8 = findViewById(R.id.table8);



        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.table1)
                    S = "1";
                else if (i == R.id.table2)
                    S = "2";
                else if (i == R.id.table3)
                    S = "3";
                else if (i == R.id.table4)
                    S = "4";
                else if (i == R.id.table5)
                    S = "5";
                else if (i == R.id.table6)
                    S = "6";
                else if (i == R.id.table7)
                    S = "7";
                else if (i == R.id.table8)
                    S = "8";


            }
        });


        b1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {


                Intent intent = new Intent(getApplicationContext(), DisplayActivity.class);
                intent.putExtra("message_key", "No of table Booked " + S);
                startActivity(intent);
            }
        });
    }




//        superListView = findViewById(R.id.superListView);

//        getSuperHeroes();


//    private void getSuperHeroes() {
//        Call<List<Results>> call = RetrofitClient.getInstance().getMyApi().getsuperHeroes();
//        call.enqueue(new Callback<List<Results>>() {
//            @Override
//            public void onResponse(Call<List<Results>> call, Response<List<Results>> response) {
//                List<Results> myheroList = response.body();
//                String[] oneHeroes = new String[myheroList.size()];
//
//                for (int i = 0; i < myheroList.size(); i++) {
//                    oneHeroes[i] = myheroList.get(i).getName();
//                }
//
//                superListView.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, oneHeroes));
//            }
//
//            @Override
//            public void onFailure(Call<List<Results>> call, Throwable t) {
//                Toast.makeText(getApplicationContext(), "An error has occured", Toast.LENGTH_LONG).show();
//            }
//
//        });
//    }



}


