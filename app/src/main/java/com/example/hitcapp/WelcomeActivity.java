package com.example.hitcapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class WelcomeActivity extends AppCompatActivity {

    Button btnStart, btnGoRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        btnStart = findViewById(R.id.btnStart);
        btnGoRegister = findViewById(R.id.btnGoRegister);

        btnStart.setOnClickListener(v ->
                startActivity(new Intent(WelcomeActivity.this, LoginActivity.class)));

        btnGoRegister.setOnClickListener(v ->
                startActivity(new Intent(WelcomeActivity.this, RegisterActivity.class)));
    }
}