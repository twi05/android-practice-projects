package com.example.foodapp2;

import java.util.ArrayList;

public class Cart {

    public ArrayList<CartItem> arr;
    private static Cart cartData;

    private Cart() {
        arr = new ArrayList<>();
    }


    public static Cart getCartData() {
        if (cartData == null) {
            cartData = new Cart();
        }

        return cartData;
    }

    public void addCartItem(CartItem cartItem) {
        for (CartItem c : arr) {
            if (c.getId() == cartItem.getId()) {
//                cartItem.incrementQuantity();
                return;
            }
        }
        arr.add(cartItem);
    }
}
