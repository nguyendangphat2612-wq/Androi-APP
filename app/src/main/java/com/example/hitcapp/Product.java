package com.example.hitcapp;

import java.io.Serializable;

public class Product implements Serializable {
    private String name;
    private String category;
    private String description;
    private int price;
    private String image; // Thay đổi từ int sang String để nhận URL từ API

    public Product(String name, String category, String description, int price, String image) {
        this.name = name;
        this.category = category;
        this.description = description;
        this.price = price;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public int getPrice() {
        return price;
    }

    public String getImage() {
        return image;
    }
}