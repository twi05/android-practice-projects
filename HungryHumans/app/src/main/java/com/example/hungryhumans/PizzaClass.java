package com.example.hungryhumans;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;

public class PizzaClass implements Serializable, Parcelable {
    String name;
    double amount;
    ArrayList<String> customisation;
    PizzaClass(String name, double amount, ArrayList<String> customisation){
       this.name =name;
                this.amount = amount;
        this.customisation = customisation;
    }


    protected PizzaClass(Parcel in) {
        name = in.readString();
        amount = in.readDouble();
        customisation = in.createStringArrayList();
    }

    public static final Creator<PizzaClass> CREATOR = new Creator<PizzaClass>() {
        @Override
        public PizzaClass createFromParcel(Parcel in) {
            return new PizzaClass(in);
        }

        @Override
        public PizzaClass[] newArray(int size) {
            return new PizzaClass[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeDouble(amount);
        parcel.writeStringList(customisation);
    }
}
