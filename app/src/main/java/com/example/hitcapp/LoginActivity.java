package com.example.hitcapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    EditText edtUsername, edtPassword;
    Button btnLogin;
    TextView tvGoRegister, tvForgot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences preferences = getSharedPreferences("USER_DATA", MODE_PRIVATE);
        boolean isLogin = preferences.getBoolean("isLogin", false);

        if (isLogin) {
            startActivity(new Intent(LoginActivity.this, HomeActivity.class));
            finish();
            return;
        }

        setContentView(R.layout.activity_login);

        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        btnLogin = findViewById(R.id.btnLogin);
        tvGoRegister = findViewById(R.id.tvGoRegister);
        tvForgot = findViewById(R.id.tvForgot);

        btnLogin.setOnClickListener(v -> {
            String username = edtUsername.getText().toString().trim();
            String password = edtPassword.getText().toString().trim();

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                return;
            }

            String savedUser = preferences.getString("username", "admin");
            String savedPass = preferences.getString("password", "123456");

            if (username.equals(savedUser) && password.equals(savedPass)) {
                SharedPreferences.Editor editor = preferences.edit();
                editor.putBoolean("isLogin", true);
                editor.apply();

                Toast.makeText(this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                finish();
            } else {
                Toast.makeText(this, "Sai tài khoản hoặc mật khẩu", Toast.LENGTH_SHORT).show();
            }
        });

        tvGoRegister.setOnClickListener(v -> {
            startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
        });

        tvForgot.setOnClickListener(v -> {
            Toast.makeText(this, "Chức năng quên mật khẩu sẽ làm sau", Toast.LENGTH_SHORT).show();
        });
    }
}