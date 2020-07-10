package com.capstone.phoneshop.Data;

import android.content.Context;
import android.hardware.usb.UsbRequest;
import android.os.AsyncTask;
import android.os.CountDownTimer;
import android.util.Log;

import com.capstone.phoneshop.Activities.MainActivity;
import com.capstone.phoneshop.Activities.SignInActivity;
import com.capstone.phoneshop.Model.Address;
import com.capstone.phoneshop.Model.User;
import com.capstone.phoneshop.Utils.API;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public class DownloaderLoginInformation extends AsyncTask<String,Void,User> {

    public User user = new User();

    @Override
    protected User doInBackground(String... strings) {

        String email = strings[0];
        String password = strings[1];

        Gson gson = new GsonBuilder().setDateFormat("MM/dd/yyyy hh:mm:ss a").create();

        Retrofit retrofit =  new Retrofit.Builder()
                .client(new OkHttpClient())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(API.URL)
                .build();

        Login service = retrofit.create(Login.class);
        Call<User> call  = service.login(email,password);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.isSuccessful()){
                    SignInActivity.newUser = response.body();
                    SignInActivity.flag = true;

                    /*String content =
                            user.getUserID()+"\n"
                            + user.getUserName()+"\n"
                            + user.getPhoneNumber()+"\n"
                            + user.getEmail()+"\n"
                            + user.getSex()+"\n"
                            + user.getAddress()+"\n"
                            + user.getImageURL()+"\n";
                    Log.i("loginuser",content);*/
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

                Log.i("SignUpData","Fail");
                SignInActivity.flag = false;
            }
        });



        return user;
    }


    @Override
    protected void onPostExecute(User user) {
        super.onPostExecute(user);

        /*new CountDownTimer(1000,1000){
            @Override
            public void onFinish() {
                MainActivity.db.addUser(user);
            }

            @Override
            public void onTick(long millisUntilFinished) {

            }
        }.start();*/
        //Log.i("user information",user.getUserID()+"");

    }

    public interface Login{
        @GET("login")
        Call<User> login(@Query("email") String email,@Query("password") String password);
    }

}
