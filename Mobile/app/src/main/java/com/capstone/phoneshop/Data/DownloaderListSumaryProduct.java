package com.capstone.phoneshop.Data;

import android.os.AsyncTask;
import android.os.CountDownTimer;
import android.util.Log;

import com.capstone.phoneshop.Activities.MainActivity;
import com.capstone.phoneshop.Adapter.ProductAdapter;
import com.capstone.phoneshop.Model.Product;
import com.capstone.phoneshop.Model.User;
import com.capstone.phoneshop.Utils.API;
import com.capstone.phoneshop.ui.home.HomeFragment;
import com.capstone.phoneshop.ui.userpage.UserPageFragment;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

import static com.capstone.phoneshop.ui.home.HomeFragment.productList;

public class DownloaderListSumaryProduct extends AsyncTask<String,Void, List<Product>> {
    public List<Product> list;

    @Override
    protected List<Product> doInBackground(String... strings) {
        //newList = new ArrayList<>();

        //String url = strings[0];

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .client(new OkHttpClient())
                .baseUrl(API.URL)
                .build();
        GetSumaryListProduct service  = retrofit.create(GetSumaryListProduct.class);
        Call<List<Product>> call = service.getList();

        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if(response.isSuccessful()){
                    list = response.body();
                    //textView.setText(list.toArray().toString());
                    for(Product product : list){
                        productList.add(product);
                        String content = "";
                        content+=" id : "+product.getProductID()+"\n";
                        content+=" name : "+product.getName()+"\n";
                        content+=" price : "+product.getPrice()+"\n------------------------------\n";
                        Log.i("product",content);
                    }
                    Log.i("Result","Success");
                }

                else{
                    Log.i("Result","Fail");
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Log.i("Connection","Fail");
            }
        });
        return list;
    }



    @Override
    protected void onPreExecute() {
        super.onPreExecute();

    }

    @Override
    protected void onPostExecute(List<Product> products) {
        super.onPostExecute(products);
        /*new CountDownTimer(1000,1000){
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                HomeFragment.productAdapter.notifyDataSetChanged();
            }
        }.start();*/

    }

    public interface GetSumaryListProduct{
        @GET("getSumaryListProduct")
        Call<List<Product>> getList();
    }


}
