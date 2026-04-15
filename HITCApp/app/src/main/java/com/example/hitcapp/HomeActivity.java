package com.example.hitcapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HomeActivity extends AppCompatActivity {

    RecyclerView recyclerViewProducts;
    ImageView imgCart, imgProfile;
    TextView tvHeaderCartCount;
    List<Product> productList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        recyclerViewProducts = findViewById(R.id.recyclerViewProducts);
        imgCart = findViewById(R.id.imgCart);
        imgProfile = findViewById(R.id.imgProfile);
        tvHeaderCartCount = findViewById(R.id.tvHeaderCartCount);

        productList = SampleData.getGoldProducts();

        recyclerViewProducts.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerViewProducts.setAdapter(new ProductAdapter(this, productList));

        imgCart.setOnClickListener(v ->
                startActivity(new Intent(HomeActivity.this, CartActivity.class)));

        imgProfile.setOnClickListener(v ->
                startActivity(new Intent(HomeActivity.this, ProfileActivity.class)));
    }

    @Override
    protected void onResume() {
        super.onResume();
        tvHeaderCartCount.setText(String.valueOf(CartManager.getTotalItems()));
    }
}