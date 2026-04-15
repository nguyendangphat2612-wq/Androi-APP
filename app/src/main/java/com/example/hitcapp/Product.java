package com.example.hitcapp;

import java.io.Serializable;

public class Product implements Serializable {
    private String name;
    private String category;
    private String description;
    private int price;

    public Product(String name, String category, String description, int price) {
        this.name = name;
        this.category = category;
        this.description = description;
        this.price = price;
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
}