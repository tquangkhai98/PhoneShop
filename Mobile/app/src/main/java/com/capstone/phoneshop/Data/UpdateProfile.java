package com.capstone.phoneshop.Data;

import android.content.Intent;
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

public class UpdateProfile extends AsyncTask<String,Void,String> {

    private String result = "Fail";
    @Override
    protected String doInBackground(String... strings) {
        int id = Integer.parseInt(strings[0]);
        String name = strings[1];
        //String email = strings[1];
        String textBirthDay= strings[2];
        int sex = Integer.parseInt(strings[3]);

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(API.URL)
                .client(new OkHttpClient())
                .build();

        UpdateUserInformation service = retrofit.create(UpdateUserInformation.class);
        Call<String> call = service.updateUserInformation(id,name,textBirthDay,sex);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.isSuccessful()){
                    result = response.body();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.i("Update","Fail");
            }
        });


        return result;
    }

    public interface UpdateUserInformation{
        @GET("updateUserInformation")
        Call<String> updateUserInformation(@Query("userid") int id, @Query("username") String name, @Query("ngaysinh") String birthday, @Query("sex") int sex);
    }
}
