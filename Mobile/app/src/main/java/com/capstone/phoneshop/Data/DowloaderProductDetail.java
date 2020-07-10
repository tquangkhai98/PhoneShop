package com.capstone.phoneshop.Data;

import android.os.AsyncTask;
import android.util.Log;

import com.capstone.phoneshop.Activities.ProductDetailActivity;
import com.capstone.phoneshop.Model.Product;
import com.capstone.phoneshop.Utils.API;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class DowloaderProductDetail extends AsyncTask<Integer,Void, Product> {

    private Product product;
    @Override
    protected Product doInBackground(Integer... integers) {
        Retrofit retrofit  = new Retrofit.Builder()
                .baseUrl(API.URL)
                .client(new OkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GetProduct service = retrofit.create(GetProduct.class);
        Call<Product> call = service.getProduct(integers[0]);
        call.enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {
                Log.i("Downloaded","Success");
                Log.i("Content",response.toString());
                if(response.isSuccessful()){
                    product = response.body();
                    ProductDetailActivity.productSelected = product;
                }
            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {
                Log.i("Error",t.getMessage());
            }
        });


        return null;
    }

    public interface GetProduct{
        @GET("getProduct")
        Call<Product> getProduct(@Query("productId") int productID);
    }
}
