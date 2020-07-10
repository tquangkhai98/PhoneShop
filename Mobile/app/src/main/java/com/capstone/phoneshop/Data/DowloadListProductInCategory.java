package com.capstone.phoneshop.Data;

import android.os.AsyncTask;

import com.capstone.phoneshop.Activities.ListProductInCategory;
import com.capstone.phoneshop.Model.Product;
import com.capstone.phoneshop.Utils.API;
import com.capstone.phoneshop.ui.home.HomeFragment;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class DowloadListProductInCategory extends AsyncTask<Integer,Void,Boolean> {
    private List<Product> list;
    @Override
    protected Boolean doInBackground(Integer... integers) {

        int categoryID = integers[0];

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .client(new OkHttpClient())
                .baseUrl(API.URL)
                .build();
        GetListProductInCategory service  = retrofit.create(GetListProductInCategory.class);
        Call<List<Product>> call = service.getList(categoryID);
        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if(response.isSuccessful()){
                    list = response.body();
                    ListProductInCategory.listProduct.clear();
                    for(Product product : list){
                        ListProductInCategory.listProduct.add(product);
                        if(ListProductInCategory.listProduct.size()==20){
                            return;
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {

            }
        });



        return true;
    }

    public interface GetListProductInCategory{
        @GET("getProductInCategory")
        Call<List<Product>> getList(@Query("categoryID") int categoryID);
    }
}
