package com.capstone.phoneshop.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Toast;

import com.capstone.phoneshop.Adapter.CartItemAdapter2;
import com.capstone.phoneshop.Data.DownloaderListProductOrdered;
import com.capstone.phoneshop.Model.CartItem;
import com.capstone.phoneshop.R;

import java.util.ArrayList;
import java.util.List;

public class OrderedProductActivity extends AppCompatActivity {

    public static List<CartItem> listProductOrdered;
    private RecyclerView recyclerView;
    //private DownloaderListProductOrdered downloader;
    private CartItemAdapter2 adapter;

    public  void  init(){
        new DownloaderListProductOrdered().execute(MainActivity.user.getUserID());
        Toast.makeText(getApplicationContext(),"Loading...",Toast.LENGTH_LONG).show();
        new CountDownTimer(2000,1000){
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                //adapter = new CartItemAdapter(getApplicationContext(), listProductOrdered);
                adapter.notifyDataSetChanged();
            }
        }.start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ordered_product);
        setTitle("Các sản phẩm đã đặt hàng");
        recyclerView = findViewById(R.id.productsOrderedView);
        //downloader = new DownloaderListProductOrdered();
        listProductOrdered = new ArrayList<>();

        recyclerView.setHasFixedSize(true);



        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        adapter = new CartItemAdapter2(getApplicationContext(), listProductOrdered);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        init();


    }
}
