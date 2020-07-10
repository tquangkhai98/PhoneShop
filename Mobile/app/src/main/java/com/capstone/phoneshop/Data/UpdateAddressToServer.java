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

public class UpdateAddressToServer extends AsyncTask<String,Void,String> {
    private String result = "Fail";
    @Override
    protected String doInBackground(String... strings) {

        int id = Integer.parseInt(strings[0]);
        String name = strings[1];
        String phone = strings[2];
        String city = strings[3];
        String district  = strings[4];
        String wards  = strings[5];
        String detail = strings[6];
        Retrofit retrofit = new Retrofit.Builder()
                .client(new OkHttpClient())
                .baseUrl(API.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        UpdateAddress service = retrofit.create(UpdateAddress.class);
        Call<String> call = service.updateAddress(id,name,phone,city,district,wards,detail);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.isSuccessful()){
                    result = response.body();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.i("Lỗi",t.getMessage());
            }
        });

        return result;
    }

    public interface UpdateAddress{
        @GET("updateAddress")
        Call<String> updateAddress(@Query("userid") int id,@Query("name")String name,
                                   @Query("phonenumber")String phonenumber,@Query("city") String city,
                                   @Query("district") String district, @Query("wards") String wards,
                                   @Query("detail") String detail);
    }


}

