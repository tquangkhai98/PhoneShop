package com.capstone.phoneshop.Data;

import android.os.AsyncTask;
import android.os.CountDownTimer;
import android.util.Log;

import com.capstone.phoneshop.Model.Category;
import com.capstone.phoneshop.Model.Product;
import com.capstone.phoneshop.Utils.API;
import com.capstone.phoneshop.ui.category.CategoryFragment;
import com.capstone.phoneshop.ui.home.HomeFragment;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

import static com.capstone.phoneshop.ui.category.CategoryFragment.categoryList;

public class DownloaderListCategory extends AsyncTask<String,Void,List<Category>> {
    public List<Category> list;
    @Override
    protected List<Category> doInBackground(String... strings) {
        Retrofit retrofit = new Retrofit.Builder()
                .client(new OkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(API.URL)
                .build();

        GetListCategory service  = retrofit.create(GetListCategory.class);
        Call<List<Category>> call = service.getlist();
        call.enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                if(response.isSuccessful()){
                    list =  response.body();
                    for (Category category : list){
                        categoryList.add(category);
                        String content = "";
                        content+=" id : "+category.getCategoryID()+"\n";
                        content+=" name : "+category.getCategoryName()+"\n";
                        content+=" imageURL : "+category.getImageURL()+"\n------------------------------\n";
                        Log.i("category",content);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                Log.i("Download","Fail");
            }
        });
        return list;
    }

    @Override
    protected void onPostExecute(List<Category> categoryList) {
        super.onPostExecute(categoryList);
        /*new CountDownTimer(1000,1000){
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                CategoryFragment.categoryAdapter.notifyDataSetChanged();
            }
        }.start();*/
    }

    public interface GetListCategory{
        @GET("getListCategory")
        Call<List<Category>> getlist();
    }
}
