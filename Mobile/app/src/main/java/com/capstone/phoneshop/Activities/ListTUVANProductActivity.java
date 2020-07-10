package com.capstone.phoneshop.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.capstone.phoneshop.Adapter.ProductAdapter;
import com.capstone.phoneshop.Data.DownloadListProductTUVAN;
import com.capstone.phoneshop.Model.Product;
import com.capstone.phoneshop.R;

import java.util.ArrayList;
import java.util.List;

public class ListTUVANProductActivity extends AppCompatActivity {

    public static List<Product> list;
    private RecyclerView recyclerView;
    private ProductAdapter productAdapter;
    private Button backtoHome;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_tuvanproduct);
        setTitle("Danh sánh sản phẩm để cử");
        backtoHome =findViewById(R.id.backToHome);

        list = new ArrayList<>();
        /*list.add(new Product(11,"Iphone 11 Pro Max",Float.parseFloat("30000000"),
                "https://cdn.fptshop.com.vn/Uploads/Originals/2019/9/11/637037687763926758_11-pro-max-xanh.png"));
        list.add(new Product(11,"Iphone 11 Pro Max",Float.parseFloat("30000000"),
                "https://cdn.fptshop.com.vn/Uploads/Originals/2019/9/11/637037687763926758_11-pro-max-xanh.png"));
        list.add(new Product(11,"Iphone 11 Pro Max",Float.parseFloat("30000000"),
                "https://cdn.fptshop.com.vn/Uploads/Originals/2019/9/11/637037687763926758_11-pro-max-xanh.png"));*/
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        productAdapter = new ProductAdapter(getApplicationContext(), list);
        recyclerView.setAdapter(productAdapter);
        productAdapter.notifyDataSetChanged();

        backtoHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });

        new DownloadListProductTUVAN().execute(MainActivity.user.getUserID());

        Toast.makeText(getApplicationContext(),"Setting...",Toast.LENGTH_LONG).show();
        new CountDownTimer(2000,1000){
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                productAdapter.notifyDataSetChanged();
            }
        }.start();


    }
}
