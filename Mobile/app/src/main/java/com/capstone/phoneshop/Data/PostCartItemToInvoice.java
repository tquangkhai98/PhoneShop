package com.capstone.phoneshop.Data;

import android.os.AsyncTask;
import android.util.Log;

import com.capstone.phoneshop.R;
import com.capstone.phoneshop.Utils.API;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class PostCartItemToInvoice extends AsyncTask<String,Void,String> {
    private String result = "Fail";
    @Override
    protected String doInBackground(String... strings) {
        int invoiceID = Integer.parseInt(strings[0]);
        int productID = Integer.parseInt(strings[1]);
        String url = strings[2];
        float price = Float.parseFloat(strings[3]);
        String color = strings[4];
        int amount = Integer.parseInt(strings[5]);

        Retrofit retrofit = new Retrofit.Builder().client(new OkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(API.URL)
                .build();

        PostCartItem service = retrofit.create(PostCartItem.class);
        Call<String> call = service.postCartItem(invoiceID,productID,url,price,color,amount);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.isSuccessful()){
                    result = response.body();
                    Log.i("PostItemIntoInvoice",result);
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.i("PostFail",t.getMessage());
            }
        });
        return result;
    }

    public interface PostCartItem{
        @GET("postCartItem")
        Call<String> postCartItem(@Query("invoiceID") int invoiceID,@Query("productID") int productID,
                                  @Query("url") String url,@Query("price") float price,
                                  @Query("tenmau") String color,@Query("amount") int amount);
    }
}


