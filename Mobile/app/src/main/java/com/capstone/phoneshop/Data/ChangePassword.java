package com.capstone.phoneshop.Data;

import android.os.AsyncTask;
import android.util.Log;

import com.capstone.phoneshop.Activities.UserDetailActivity;
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

public class ChangePassword extends AsyncTask<String,Void,String> {
    private String result  = "Fail";

    @Override
    protected String doInBackground(String... strings) {
        int id = Integer.parseInt(strings[0]);
        String oldpassword = strings[1];
        String newpassword = strings[2];

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .client(new OkHttpClient())
                .baseUrl(API.URL)
                .build();

        ChangePasswordAPI service = retrofit.create(ChangePasswordAPI.class);
        Call<String> call = service.change(id,oldpassword,newpassword);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.isSuccessful()){
                    result = response.body();
                    UserDetailActivity.changePWResult = result;
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.i("Error",t.getMessage());
            }
        });

        return result;

    }
    public interface ChangePasswordAPI{
        @GET("changePassword")
        Call<String> change(@Query("userid") int userid,
                                    @Query("oldpassword") String oldpassword,
                                    @Query("newpassword") String newpassword);
    }
}
