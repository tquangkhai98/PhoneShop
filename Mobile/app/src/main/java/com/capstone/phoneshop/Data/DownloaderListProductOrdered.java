package com.capstone.phoneshop.Data;

import android.os.AsyncTask;
import android.util.Log;

import com.capstone.phoneshop.Activities.OrderedProductActivity;
import com.capstone.phoneshop.Data.DowloadListProductInCategory;
import com.capstone.phoneshop.Model.CartItem;
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

public class DownloaderListProductOrdered extends AsyncTask<Integer,Void,List<CartItem>> {

    private List<CartItem> list;

    @Override
    protected List<CartItem> doInBackground(Integer... integers) {

        int userID = integers[0];

        Retrofit retrofit = new Retrofit.Builder()
                .client(new OkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(API.URL)
                .build();

        GetListProductOrdered service = retrofit.create(GetListProductOrdered.class);
        Call<List<CartItem>> call = service.getListProduct(userID);

        call.enqueue(new Callback<List<CartItem>>() {
            @Override
            public void onResponse(Call<List<CartItem>> call, Response<List<CartItem>> response) {
                if(response.isSuccessful()){
                    list = response.body();
                    for(CartItem cartItem : list){
                        OrderedProductActivity.listProductOrdered.add(cartItem);
                        String content = cartItem.getTitle()+ " "+ cartItem.getImageURL()+"\n";
                        Log.i("content",content);
                    }
                }

            }

            @Override
            public void onFailure(Call<List<CartItem>> call, Throwable t) {
                Log.i("Erorr",t.getMessage());
            }
        });



        return null;
    }

    public interface GetListProductOrdered{
        @GET("getListProductOrdered")
        Call<List<CartItem>> getListProduct(@Query("userid") int userid);
    }
}
