package com.example.hitcapp;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    // Thay "products" bằng endpoint thực tế của bạn
    @GET("products") 
    Call<List<Product>> getProducts();
}