package com.example.hitcapp;

import java.util.ArrayList;
import java.util.List;

public class CartManager {
    private static final List<CartItem> cartItems = new ArrayList<>();

    public static List<CartItem> getCartItems() {
        return cartItems;
    }

    public static void addToCart(Product product) {
        for (CartItem item : cartItems) {
            if (item.getProduct().getName().equals(product.getName())) {
                item.increase();
                return;
            }
        }
        cartItems.add(new CartItem(product, 1));
    }

    public static void removeItem(int position) {
        if (position >= 0 && position < cartItems.size()) {
            cartItems.remove(position);
        }
    }

    public static void increaseQty(int position) {
        if (position >= 0 && position < cartItems.size()) {
            cartItems.get(position).increase();
        }
    }

    public static void decreaseQty(int position) {
        if (position >= 0 && position < cartItems.size()) {
            CartItem item = cartItems.get(position);
            if (item.getQuantity() > 1) {
                item.decrease();
            }
        }
    }

    public static int getTotalPrice() {
        int total = 0;
        for (CartItem item : cartItems) {
            total += item.getSubTotal();
        }
        return total;
    }

    public static int getTotalItems() {
        int total = 0;
        for (CartItem item : cartItems) {
            total += item.getQuantity();
        }
        return total;
    }

    public static void clearCart() {
        cartItems.clear();
    }
}