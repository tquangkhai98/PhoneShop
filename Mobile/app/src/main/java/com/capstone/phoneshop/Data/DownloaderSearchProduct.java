package com.capstone.phoneshop.Data;

import android.os.AsyncTask;
import android.os.CountDownTimer;
import android.util.Log;

import com.capstone.phoneshop.Model.Product;
import com.capstone.phoneshop.Utils.API;
import com.capstone.phoneshop.ui.searchbar.SearchBarFragment;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public class DownloaderSearchProduct extends AsyncTask<String,Void, List<Product>> {
    private List<Product> list;
    //private boolean downloaded = false;
    @Override
    protected List<Product> doInBackground(String... strings) {

        String searchwords = strings[0];

        Retrofit retrofit  = new Retrofit.Builder()
                .client(new OkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(API.URL)
                .build();

        GetSearchProduct service = retrofit.create(GetSearchProduct.class);
        Call<List<Product>> call = service.getSearchProduct(searchwords);

        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if(response.isSuccessful()){
                    list = response.body();
                    String content = "";
                    for(Product product : list){
                        SearchBarFragment.list.add(product);
                        content+="\nid : "+product.getProductID()+"\n";
                        content+="name:" +product.getName()+"\n----------------------\n";
                        Log.i("productsearch",content);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Log.i("Errorr",t.getMessage());
            }
        });

        return list;
    }

    @Override
    protected void onPostExecute(List<Product> products) {
        super.onPostExecute(products);
        new CountDownTimer(1000,1000){
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                SearchBarFragment.productAdapter.notifyDataSetChanged();
            }
        }.start();
    }

    public interface GetSearchProduct{
        @GET("getSearchProduct")
        Call<List<Product>> getSearchProduct(@Query("searchword") String searchword);
    }

}
