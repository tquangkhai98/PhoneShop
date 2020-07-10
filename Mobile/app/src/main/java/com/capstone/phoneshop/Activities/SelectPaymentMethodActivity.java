package com.capstone.phoneshop.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.capstone.phoneshop.R;
import com.google.android.material.card.MaterialCardView;

public class SelectPaymentMethodActivity extends AppCompatActivity {
    private MaterialCardView cod;
    private MaterialCardView momo;
    private MaterialCardView nganluong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_payment_method);
        setTitle("Chọn phương thức thanh toán");

        cod = findViewById(R.id.cash);
        momo = findViewById(R.id.momo);
        nganluong = findViewById(R.id.nganluong);

        cod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),CODPaymentActivity.class));
            }
        });

        momo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MoMoPaymentActivity.class));
            }
        });
        nganluong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Chưa hỗ trợ",Toast.LENGTH_SHORT).show();
            }
        });

    }
}
