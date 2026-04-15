package com.example.hitcapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    EditText edtRegUsername, edtRegEmail, edtRegPassword;
    Button btnRegister;
    TextView tvGoLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edtRegUsername = findViewById(R.id.edtRegUsername);
        edtRegEmail = findViewById(R.id.edtRegEmail);
        edtRegPassword = findViewById(R.id.edtRegPassword);
        btnRegister = findViewById(R.id.btnRegister);
        tvGoLogin = findViewById(R.id.tvGoLogin);

        btnRegister.setOnClickListener(v -> {
            String username = edtRegUsername.getText().toString().trim();
            String email = edtRegEmail.getText().toString().trim();
            String password = edtRegPassword.getText().toString().trim();

            if (username.isEmpty() || email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập đầy đủ", Toast.LENGTH_SHORT).show();
                return;
            }

            SharedPreferences preferences = getSharedPreferences("USER_DATA", MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("username", username);
            editor.putString("email", email);
            editor.putString("password", password);
            editor.apply();

            Toast.makeText(this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            finish();
        });

        tvGoLogin.setOnClickListener(v -> finish());
    }
}