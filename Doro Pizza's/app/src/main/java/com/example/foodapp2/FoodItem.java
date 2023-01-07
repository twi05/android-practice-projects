package com.example.foodapp2;

import java.util.Arrays;
import java.util.Objects;

public class FoodItem {

    private int id;
    private String name;
    private String description;
    private String ingredients[];
    private boolean spicy;
    private boolean vegetarian;
    private double price;
    private String img;

    public FoodItem(int id, String name, String description,
                    String ingredeients[], boolean spicy,
                    boolean vegetarian, double price, String img) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.ingredients = ingredeients;
        this.price = price;
        this.spicy = spicy;
        this.vegetarian = vegetarian;
        this.img = img;

    }

    public int getId() { return this.id; }

    public String getName() { return  this.name; }

    public String getDescription() { return this.description; }

    public String[] getIngredients() { return this.ingredients; }

    public double getPrice() { return this.price; }

    public boolean isSpicy() { return this.spicy; }

    public boolean isVegetarian() { return this.vegetarian; }

    public String getImg() { return this.img; }
}
