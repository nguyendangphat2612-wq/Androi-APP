package com.example.hitcapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.NumberFormat;
import java.util.Locale;

public class CartActivity extends AppCompatActivity implements CartAdapter.OnCartChangedListener {

    RecyclerView recyclerViewCart;
    TextView tvCartEmpty, tvTotalPrice;
    Button btnCheckout, btnBackHome;
    CartAdapter adapter;

    private String formatPrice(int price) {
        NumberFormat format = NumberFormat.getInstance(new Locale("vi", "VN"));
        return format.format(price) + " đ";
    }

    private void updateCartUI() {
        if (CartManager.getCartItems().isEmpty()) {
            tvCartEmpty.setVisibility(View.VISIBLE);
            recyclerViewCart.setVisibility(View.GONE);
        } else {
            tvCartEmpty.setVisibility(View.GONE);
            recyclerViewCart.setVisibility(View.VISIBLE);
        }
        tvTotalPrice.setText("Tổng tiền: " + formatPrice(CartManager.getTotalPrice()));
    }

    @Override
    public void onCartChanged() {
        updateCartUI();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        recyclerViewCart = findViewById(R.id.recyclerViewCart);
        tvCartEmpty = findViewById(R.id.tvCartEmpty);
        tvTotalPrice = findViewById(R.id.tvTotalPrice);
        btnCheckout = findViewById(R.id.btnCheckout);
        btnBackHome = findViewById(R.id.btnBackHome);

        recyclerViewCart.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CartAdapter(this, CartManager.getCartItems(), this);
        recyclerViewCart.setAdapter(adapter);

        updateCartUI();

        btnCheckout.setOnClickListener(v -> {
            if (!CartManager.getCartItems().isEmpty()) {
                startActivity(new Intent(CartActivity.this, PaymentActivity.class));
            }
        });

        btnBackHome.setOnClickListener(v -> finish());
    }
}