package com.capstone.phoneshop.Data;

import android.os.AsyncTask;
import android.util.Log;

import com.capstone.phoneshop.Utils.API;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class UpdateTUVANInformation extends AsyncTask<Integer,Void,String> {

    private String result = "Faile";

    @Override
    protected String doInBackground(Integer... integers) {
        int id =  integers[0];
        int hobbyID = integers[1];
        int incomeLevel = integers[2];

        Retrofit retrofit = new Retrofit.Builder()
                .client(new OkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(API.URL)
                .build();

        Update service =retrofit.create(Update.class);
        Call<String> call = service.update(id,hobbyID,incomeLevel);

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.isSuccessful()){
                    result = response.body();
                }

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.i("Error",t.getMessage());
            }
        });


        return result;
    }

    public interface Update{
        @GET("updateInformationTUVAN")
        Call<String> update(@Query("userid") int id,@Query("hobbyID") int hobbyID,
                            @Query("incomeLevel") int incomeLevel);
    }
}
