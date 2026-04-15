package com.example.hitcapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.NumberFormat;
import java.util.Locale;

public class DetailActivity extends AppCompatActivity {

    TextView tvDetailBadge, tvDetailName, tvDetailPrice, tvDetailDesc;
    Button btnAddToCart, btnBuyNow;

    private String formatPrice(int price) {
        NumberFormat format = NumberFormat.getInstance(new Locale("vi", "VN"));
        return format.format(price) + " đ";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        tvDetailBadge = findViewById(R.id.tvDetailBadge);
        tvDetailName = findViewById(R.id.tvDetailName);
        tvDetailPrice = findViewById(R.id.tvDetailPrice);
        tvDetailDesc = findViewById(R.id.tvDetailDesc);
        btnAddToCart = findViewById(R.id.btnAddToCart);
        btnBuyNow = findViewById(R.id.btnBuyNow);

        Product product = (Product) getIntent().getSerializableExtra("product");

        if (product != null) {
            tvDetailBadge.setText(product.getCategory());
            tvDetailName.setText(product.getName());
            tvDetailPrice.setText(formatPrice(product.getPrice()));
            tvDetailDesc.setText(product.getDescription());
        }

        btnAddToCart.setOnClickListener(v -> {
            if (product != null) {
                CartManager.addToCart(product);
                Toast.makeText(this, "Đã thêm vào giỏ hàng", Toast.LENGTH_SHORT).show();
            }
        });

        btnBuyNow.setOnClickListener(v -> {
            if (product != null) {
                CartManager.addToCart(product);
                startActivity(new Intent(DetailActivity.this, CartActivity.class));
            }
        });
    }
}