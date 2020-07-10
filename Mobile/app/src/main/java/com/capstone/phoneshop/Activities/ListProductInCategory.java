package com.capstone.phoneshop.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.capstone.phoneshop.Adapter.ProductAdapter;
import com.capstone.phoneshop.Data.DowloadListProductInCategory;
import com.capstone.phoneshop.Model.Product;
import com.capstone.phoneshop.R;

import java.util.ArrayList;
import java.util.List;

public class ListProductInCategory extends BaseActivity{

    private int category_ID;
    public static List<Product> listProduct;
    private RecyclerView listProductView;
    private ProductAdapter productInCategoryAdapter;
    //private DowloadListProductInCategory dowloader;

    public void initProductList(){
        //lay danh sach san pham bang category_ID
        Intent intent = getIntent();
        category_ID = intent.getIntExtra("category_ID",0);
        //Toast.makeText(getApplicationContext(),category_ID,Toast.LENGTH_SHORT).show();
        Log.i("Category_ID",category_ID+"");
        listProduct = new ArrayList<>();
        try{
            new DowloadListProductInCategory().execute(category_ID);
        }catch (Exception e){
            e.printStackTrace();
        }

        /*

        listProduct.add(new Product(11,"Iphone 11 Pro Max",Float.parseFloat("30000000"),
                "https://cdn.fptshop.com.vn/Uploads/Originals/2019/9/11/637037687763926758_11-pro-max-xanh.png"));
        listProduct.add(new Product(10,"Iphone XS Max",Float.parseFloat("20000000"),
                "https://cdn.tgdd.vn/Products/Images/42/190322/iphone-xs-max-256gb-white-400x400.jpg"));
        listProduct.add(new Product(12,"Iphone 8 Plus",Float.parseFloat("15000000"),
                "https://cdn.tgdd.vn/Products/Images/42/114110/iphone-8-plus-hh-400x400.jpg"));
        listProduct.add(new Product(13,"Samsung Galaxy S10",Float.parseFloat("20000000"),
                "https://cdn.tgdd.vn/Products/Images/42/161554/samsung-galaxy-s10-white-400x460.png"));
        listProduct.add(new Product(14,"Samsung Note 10",Float.parseFloat("25000000"),
                "https://cdn.tgdd.vn/Products/Images/42/191276/samsung-galaxy-note-10-silver-600x600.jpg"));*/
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_product_in_category);
        setTitle(R.string.title_list_product);

        Toast.makeText(getApplicationContext(),"Loading",Toast.LENGTH_LONG).show();
        initProductList();

        listProductView  = findViewById(R.id.list_product_in_category);
        listProductView.setHasFixedSize(true);
        productInCategoryAdapter = new ProductAdapter(getApplicationContext(),listProduct);

        int orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            // In landscape
            listProductView.setLayoutManager(new StaggeredGridLayoutManager(3, LinearLayout.VERTICAL));
        } else {
            // In portrait
            listProductView.setLayoutManager(new StaggeredGridLayoutManager(2, LinearLayout.VERTICAL));
        }
        listProductView.setAdapter(productInCategoryAdapter);
        productInCategoryAdapter.notifyDataSetChanged();
        new CountDownTimer(1000,1000){
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                productInCategoryAdapter.notifyDataSetChanged();
            }
        }.start();

    }
}
