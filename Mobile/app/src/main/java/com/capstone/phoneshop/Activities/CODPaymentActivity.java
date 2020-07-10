package com.capstone.phoneshop.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Toast;

import com.capstone.phoneshop.R;

public class CODPaymentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_codpayment);
        setTitle("Thanh toán COD");
        Toast.makeText(getApplicationContext(),"Chọn địa chỉ giao hàng",Toast.LENGTH_LONG).show();
        new CountDownTimer(1000,1000){
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                startActivity(new Intent(getApplicationContext(),AddressInformationActivity.class).putExtra("buy",true).putExtra("payID",1));
            }
        }.start();
    }
}
