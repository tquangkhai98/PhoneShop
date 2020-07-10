package com.capstone.phoneshop.ui.category;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.capstone.phoneshop.Adapter.CategoryAdapter;
import com.capstone.phoneshop.Data.DownloaderListCategory;
import com.capstone.phoneshop.Model.Category;
import com.capstone.phoneshop.R;

import java.util.ArrayList;
import java.util.List;

public class CategoryFragment extends Fragment {

    private CategoryViewModel dashboardViewModel;
    public static List<Category> categoryList;
    private CategoryAdapter categoryAdapter;
    private RecyclerView recyclerView;
    //private DownloaderListCategory downloader;


    public void initCategory(){
        categoryList = new ArrayList<>();
        categoryList.add(new Category(1,"Smartphone","https://didongviet.vn//pub/media/catalog/product/i/p/iphone-11-pro-max-64gb-didongviet_2.jpg"));
        //categoryList.add(new Category(3,"Máy tính","https://www.lg.com/vn/images/laptops/md06166737/gallery/Desktop-01.jpg"));
        categoryList.add(new Category(9,"Tivi","https://dienmaygiare.net/wp-content/uploads/2019/06/tivi-lg-43um7600pta.jpg"));
        categoryList.add(new Category(13,"Tủ Lạnh","https://www.lg.com/vn/images/tu-lanh/md05849141/gallery/medium01.jpg"));
        categoryList.add(new Category(16,"Máy giặt","https://images.samsung.com/is/image/samsung/vn-washer-ww90j54e0bx-ww90j54e0bx-sv-fronttitaniumgray-96430772?$PD_GALLERY_L_JPG$"));

        /*downloader = new DownloaderListCategory();
        try {
            downloader.execute("");
        }catch (Exception e){
            e.printStackTrace();
        }*/

    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                ViewModelProviders.of(this).get(CategoryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_category, container, false);
        /*final TextView textView = root.findViewById(R.id.text_dashboard);
        dashboardViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/

        initCategory();




        recyclerView = root.findViewById(R.id.categoryView);

        int orientation = getResources().getConfiguration().orientation;

        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            // In landscape
            recyclerView.setLayoutManager(new StaggeredGridLayoutManager(3, LinearLayout.VERTICAL));
        } else {
            // In portrait
            recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, LinearLayout.VERTICAL));
        }


        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, LinearLayout.VERTICAL));

        recyclerView.setHasFixedSize(true);
        categoryAdapter = new CategoryAdapter(getActivity(),categoryList);
        recyclerView.setAdapter(categoryAdapter);
        categoryAdapter.notifyDataSetChanged();
        /*new CountDownTimer(1000,1000){
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                categoryAdapter.notifyDataSetChanged();
            }
        }.start();*/







        return root;
    }
}