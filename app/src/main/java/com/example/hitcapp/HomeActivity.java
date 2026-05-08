package com.example.hitcapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {

    RecyclerView recyclerViewProducts;
    ImageView imgCart, imgProfile;
    TextView tvHeaderCartCount;
    List<Product> productList;
    ProductAdapter productAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        recyclerViewProducts = findViewById(R.id.recyclerViewProducts);
        imgCart = findViewById(R.id.imgCart);
        imgProfile = findViewById(R.id.imgProfile);
        tvHeaderCartCount = findViewById(R.id.tvHeaderCartCount);

        // Khởi tạo danh sách trống và adapter
        productList = new ArrayList<>();
        productAdapter = new ProductAdapter(this, productList);
        
        recyclerViewProducts.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerViewProducts.setAdapter(productAdapter);

        // Gọi API lấy dữ liệu
        fetchProducts();

        imgCart.setOnClickListener(v ->
                startActivity(new Intent(HomeActivity.this, CartActivity.class)));

        imgProfile.setOnClickListener(v ->
                startActivity(new Intent(HomeActivity.this, ProfileActivity.class)));
    }

    private void fetchProducts() {
        RetrofitClient.getApiService().getProducts().enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    productList.clear();
                    productList.addAll(response.body());
                    productAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                // Xử lý khi lỗi kết nối ở đây (ví dụ: Toast thông báo)
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        tvHeaderCartCount.setText(String.valueOf(CartManager.getTotalItems()));
    }
}