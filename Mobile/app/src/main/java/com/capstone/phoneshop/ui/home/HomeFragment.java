package com.capstone.phoneshop.ui.home;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;


import com.capstone.phoneshop.Activities.MyWebViewActivity;
import com.capstone.phoneshop.Adapter.ProductAdapter;
import com.capstone.phoneshop.Data.DownloaderListSumaryProduct;
import com.capstone.phoneshop.Model.Product;
import com.capstone.phoneshop.R;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;


public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    public static List<Product> productList;
    private RecyclerView recyclerView;
    private ProductAdapter productAdapter;
    private ImageView banner;
    private boolean downloaded = false;
    //private DownloaderListSumaryProduct downloader;

    /*public void init(){
        *//*for (int i=0;i<10;i++){
            Product product = new Product(11,"Iphone 11 Pro Max",Float.parseFloat("30000000"),"https://cdn.fptshop.com.vn/Uploads/Originals/2019/9/11/637037687763926758_11-pro-max-xanh.png");
            productList.add(product);
        }*//*
        downloader = new DownloaderListSumaryProduct();
        try {
            downloader.execute("");
        }catch (Exception e){
            e.printStackTrace();
        }
    }*/



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);


        banner =  root.findViewById(R.id.imageBanner);

        banner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getActivity(),"Banner seleted",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), MyWebViewActivity.class);
                intent.putExtra("BannerURL","https://shopee.vn");
                startActivity(intent);
            }
        });


        //productList = new ArrayList<>();


        recyclerView = root.findViewById(R.id.homeView);
        //getLayoutInflater().inflate(R.layout.banner_card_view,recyclerView);

        //Product product = new Product(11,"Iphone 11 Pro Max",Float.parseFloat("30000000"),"https://cdn.fptshop.com.vn/Uploads/Originals/2019/9/11/637037687763926758_11-pro-max-xanh.png");
        //productList.add(product);



        recyclerView.setHasFixedSize(true);
        int orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            // In landscape
            recyclerView.setLayoutManager(new StaggeredGridLayoutManager(3, LinearLayout.VERTICAL));
        } else {
            // In portrait
            recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, LinearLayout.VERTICAL));
        }
        //recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));



        productAdapter = new ProductAdapter(getActivity(), productList);
        recyclerView.setAdapter(productAdapter);

        new CountDownTimer(1500,1000){
            @Override
            public void onTick(long millisUntilFinished) {
                /*if(productList.size()>0&&downloaded==false){
                    productAdapter.notifyDataSetChanged();
                    downloaded=true;
                }else {
                    return;
                }*/
            }

            @Override
            public void onFinish() {
                productAdapter.notifyDataSetChanged();
            }
        }.start();


        //init();


        //final TextView textView = root.findViewById(R.id.text_home);
        /*homeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                //textView.setText(s);
            }
        });*/

        /*new CountDownTimer(1000,1000){

            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                productAdapter.notifyDataSetChanged();
            }
        }.start();*/

        return root;
    }


}