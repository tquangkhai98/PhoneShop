package com.capstone.phoneshop.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.capstone.phoneshop.Data.CreateInvoice;
import com.capstone.phoneshop.Data.DatabaseHandler;
import com.capstone.phoneshop.Data.PostCartItemToInvoice;
import com.capstone.phoneshop.Model.CartItem;
import com.capstone.phoneshop.R;

import java.util.List;

public class OrderSuccessActivity extends AppCompatActivity {
    //private CreateInvoice createInvoice;
    //private PostCartItemToInvoice postCartItem;
    public static int invoiceID = 0;
    private List<CartItem> cartListItem;
    private Button backToHomePage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_success);
        setTitle("Đặt đơn thanh công");

        backToHomePage = findViewById(R.id.backToHome);
        backToHomePage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });

        MainActivity.db = new DatabaseHandler(getApplicationContext());
        cartListItem = MainActivity.db.getAllCartItem();

        int payID = getIntent().getIntExtra("payID",0);

        //createInvoice = new CreateInvoice();
        //postCartItem = new PostCartItemToInvoice();
        try {
            new CreateInvoice().execute(new String[]{MainActivity.user.getUserID()+"",String.valueOf(CartActivity.edAmount),payID+""}).get();
        }catch (Exception e){
            e.printStackTrace();
        }
        new CountDownTimer(3000,1000){
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                Log.i("invoiceID",invoiceID+"");
                if(invoiceID!=0){
                    for(CartItem cartItem : cartListItem){
                        String text[] = cartItem.getTitle().split(" - ");
                        String color = text[text.length-1];
                        cartItem.getImageURL().replace("http://phoneshop-env.iqfbpp9tyx.us-east-2.elasticbeanstalk.com/images/AnhDT/","");
                        new PostCartItemToInvoice().execute(new String[]{invoiceID+"",cartItem.getProductID()+"",cartItem.getImageURL().replace("http://phoneshop-env.iqfbpp9tyx.us-east-2.elasticbeanstalk.com/images/AnhDT/",""),String.valueOf(cartItem.getPrice()),color,cartItem.getAmount()+""});
                        //postCartItem.execute(new String[]{invoiceID+"",cartItem.getProductID()+"",cartItem.getImageURL(),String.valueOf(cartItem.getPrice()),cartItem.getTitle(),cartItem.getAmount()+""});


                    }
                    MainActivity.db.deleteAllCart();
                }
            }
        }.start();


    }
}
