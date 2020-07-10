package com.capstone.phoneshop.Data;

import android.os.AsyncTask;
import android.util.Log;

import com.capstone.phoneshop.Activities.SignUpActivity;
import com.capstone.phoneshop.Model.CartItem;
import com.capstone.phoneshop.Utils.API;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class SignUpData extends AsyncTask<String,Void,Integer> {
    private int userID = 0;
    @Override
    protected Integer doInBackground(String... strings) {
        String username = strings[0];
        String password = strings[1];
        String email = strings[2];

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(API.URL)
                .client(new OkHttpClient())
                .build();

        Signup service = retrofit.create(Signup.class);
        Call<Integer> call = service.signup(username,password,email);
        call.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                if(response.isSuccessful()){
                    userID = response.body();
                    SignUpActivity.userID = userID;
                }
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                Log.i("Error",t.getMessage());
            }
        });


        return userID;
    }

    public interface Signup{
        @GET("signup")
        Call<Integer> signup(@Query("username") String username,@Query("password") String password,@Query("email") String email);
    }
}
