package com.capstone.phoneshop.Data;

import android.os.AsyncTask;
import android.util.Log;

import com.capstone.phoneshop.Activities.ListProductInCategory;
import com.capstone.phoneshop.Activities.ListTUVANProductActivity;
import com.capstone.phoneshop.Model.Product;
import com.capstone.phoneshop.Utils.API;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class DownloadListProductTUVAN extends AsyncTask<Integer,Void,List<Product>> {

    private List<Product> list;

    @Override
    protected List<Product> doInBackground(Integer... integers) {

        int userid = integers[0];

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API.URL)
                .client(new OkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RequestTUVAN service = retrofit.create(RequestTUVAN.class);
        Call<List<Product>> call = service.requestTUVAN(userid);

        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if(response.isSuccessful()){
                    list = response.body();
                    for(Product product : list){
                        ListTUVANProductActivity.list.add(product);
                        String content = product.getName()+" "+product.getPrice()+" "+product.getImageURL()+"\n";
                        Log.i("content",content);
                    }

                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Log.i("Error",t.getMessage());
            }
        });


        return list;
    }

    public interface RequestTUVAN{
        @GET("requestTUVAN")
        Call<List<Product>> requestTUVAN(@Query("userid") int id);
    }
}
