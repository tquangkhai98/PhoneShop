package com.capstone.phoneshop.Data;

import android.os.AsyncTask;
import android.os.CountDownTimer;
import android.util.Log;

import com.capstone.phoneshop.Activities.MainActivity;
import com.capstone.phoneshop.Model.Address;
import com.capstone.phoneshop.Model.Category;
import com.capstone.phoneshop.Utils.API;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public class DownloaderAddress extends AsyncTask<Integer,Void, Address> {
    private Address address = new Address();
    @Override
    protected Address doInBackground(Integer... integers) {

        int userID = integers[0];
        Retrofit retrofit = new Retrofit.Builder()
                .client(new OkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(API.URL)
                .build();

        GetAddress service = retrofit.create(GetAddress.class);
        Call<Address> call = service.getAddress(userID);
        call.enqueue(new Callback<Address>() {
            @Override
            public void onResponse(Call<Address> call, Response<Address> response) {
                if(response.isSuccessful()){
                    address = response.body();
                    MainActivity.address=address;

                    /*String content = address.getUserID()+","+address.getPhoneNumber()
                            +","+address.getFullname()+","+address.getCity()+","+address.getDistrict()+","+address.getWards()+","+address.getDetailAddress();
                    Log.i("Address",content);*/

                    //MainActivity.newAddress = address;;
                    //String content = MainActivity.newAddress.getCity()+" "+MainActivity.newAddress.getDistrict()+" "+MainActivity.newAddress.getWards()+" "+MainActivity.newAddress.getDetailAddress();
                    //Log.i("Get Address",content);
                }
            }

            @Override
            public void onFailure(Call<Address> call, Throwable t) {
                Log.i("Get Address","Fail");
            }
        });


        return address;
    }



    /*@Override
    protected void onPostExecute(Address address) {
        super.onPostExecute(address);
        new CountDownTimer(3000,1000){
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                //Address newAddress = new Address();
                //newAddress = address;
                //address.setUserID(MainActivity.user.getUserID());
                //MainActivity.db.addAddress(address);
            }
        }.start();
    }*/

    public interface GetAddress{
        @GET("getAddress")
        Call<Address> getAddress(@Query("userID") int userID);
    }
}
