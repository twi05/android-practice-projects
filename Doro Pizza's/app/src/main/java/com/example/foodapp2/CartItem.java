package com.example.foodapp2;

import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.util.Date;

public class CartItem {

    private int id;
    private String name;
    private String ingredients[];
    private boolean spicy;
    private boolean vegetarian;
    private double price;
    private String img;
    private int quantity;

    public CartItem(int id, String name,
                    String ingredeients[], boolean spicy,
                    boolean vegetarian, double price, String img) {
        this.id = id;
        this.name = name;
        this.ingredients = ingredeients;
        this.price = price;
        this.spicy = spicy;
        this.vegetarian = vegetarian;
        this.img = img;
        this.quantity = 1;
    }

    public int getId() { return this.id; }

    public String getName() { return  this.name; }

    public String[] getIngredients() { return this.ingredients; }

    public double getPrice() { return this.price; }

    public boolean isSpicy() { return this.spicy; }

    public boolean isVegetarian() { return this.vegetarian; }

    public String getImg() { return this.img; }

    public void incrementQuantity() { this.quantity++; }

    public void decrementQuantity() {
        if (this.quantity > 0) {
            this.quantity--;
        }
    }
    public int getQuantity() { return this.quantity; }
}
