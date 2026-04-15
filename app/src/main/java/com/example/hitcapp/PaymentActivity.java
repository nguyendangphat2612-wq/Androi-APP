package com.example.hitcapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.NumberFormat;
import java.util.Locale;

public class PaymentActivity extends AppCompatActivity {

    TextView tvPaymentTotal;
    EditText edtFullName, edtPhone, edtAddress;
    Button btnConfirmPayment;

    private String formatPrice(int price) {
        NumberFormat format = NumberFormat.getInstance(new Locale("vi", "VN"));
        return format.format(price) + " đ";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        tvPaymentTotal = findViewById(R.id.tvPaymentTotal);
        edtFullName = findViewById(R.id.edtFullName);
        edtPhone = findViewById(R.id.edtPhone);
        edtAddress = findViewById(R.id.edtAddress);
        btnConfirmPayment = findViewById(R.id.btnConfirmPayment);

        tvPaymentTotal.setText("Tổng thanh toán: " + formatPrice(CartManager.getTotalPrice()));

        btnConfirmPayment.setOnClickListener(v -> {
            String fullName = edtFullName.getText().toString().trim();
            String phone = edtPhone.getText().toString().trim();
            String address = edtAddress.getText().toString().trim();

            if (fullName.isEmpty() || phone.isEmpty() || address.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin nhận hàng", Toast.LENGTH_SHORT).show();
                return;
            }

            Toast.makeText(this, "Thanh toán thành công", Toast.LENGTH_LONG).show();
            CartManager.clearCart();

            Intent intent = new Intent(PaymentActivity.this, HomeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        });
    }
}