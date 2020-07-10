package com.capstone.phoneshop.Data;

import android.os.AsyncTask;
import android.util.Log;

import com.capstone.phoneshop.Activities.OrderSuccessActivity;
import com.capstone.phoneshop.Utils.API;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class CreateInvoice extends AsyncTask<String,Void,Integer> {
    private int invoiceID = 0;
    @Override
    protected Integer doInBackground(String... strings) {
        int userID = Integer.parseInt(strings[0]);
        float total = Float.parseFloat(strings[1]);
        int payID = Integer.parseInt(strings[2]);

        Retrofit retrofit = new Retrofit.Builder().client(new OkHttpClient())
                .baseUrl(API.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PostInvoice service = retrofit.create(PostInvoice.class);
        Call<Integer> call = service.postInvoice(userID,total,payID);

        call.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                if(response.isSuccessful()){
                    invoiceID = response.body();
                    OrderSuccessActivity.invoiceID = invoiceID;
                    Log.i("invoiceID",invoiceID+"");
                }
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                Log.i("Create Invoice",t.getMessage());
            }
        });

        return invoiceID;
    }

    public interface PostInvoice{
        @GET("postInvoice")
        Call<Integer> postInvoice(@Query("userid") int id,@Query("total") float total,@Query("payID") int payID);
    }
}
