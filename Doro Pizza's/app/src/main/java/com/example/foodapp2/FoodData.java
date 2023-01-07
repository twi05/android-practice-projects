package com.example.foodapp2;

import java.util.ArrayList;

public class FoodData {

    public ArrayList<FoodItem> arr;
    private static FoodData foodData;

    private FoodData() {
        arr = new ArrayList<>();
    }

    public static FoodData getFoodData() {
        if (foodData == null) {
            foodData = new FoodData();
        }
        return foodData;
    }

    public void addFoodItem(FoodItem foodItem) {
        for (FoodItem f : arr) {
            if (f.getId() == foodItem.getId()) {
                return;
            }
        }
        arr.add(foodItem);
    }
//
//
//    public FoodItem getFoodItem(int id) {
//        for (FoodItem f: arr) {
//            if (f.getId() == id) {
//                return f;
//            }
//        }
//
//        return null;
//    }
}
