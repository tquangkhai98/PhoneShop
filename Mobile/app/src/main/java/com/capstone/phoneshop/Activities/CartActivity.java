package com.capstone.phoneshop.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.icu.text.UnicodeSetSpanner;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.capstone.phoneshop.Adapter.CartItemAdapter;
import com.capstone.phoneshop.Model.CartItem;
import com.capstone.phoneshop.R;

import java.text.NumberFormat;
import java.util.List;

public class CartActivity extends AppCompatActivity {

    //private static List<CartItem> itemList;
    private CartItemAdapter cartAdapter;
    private RecyclerView recyclerView;
    private Button order_button;
    public static float edAmount;



    public void init(){
        //MainActivity.db.addCartItem(new CartItem(1,"Iphone X - Đen",15000000,"https://salt.tikicdn.com/cache/w1200/ts/product/1a/f0/a2/90626a66ef0205023ad6e49274861b43.jpg",1));
        //MainActivity.db.addCartItem(new CartItem(3,"Samsung Note 10 - Xanh cực quang",10000000,"https://cdn.cellphones.com.vn/media/catalog/product/cache/7/image/9df78eab33525d08d6e5fb8d27136e95/n/o/note_10_plus_xanh_2.jpg",1));
        //itemList = MainActivity.db.getAllCartItem();
        //itemList.add(new CartItem(1,"Iphone X - Đen",15000000,"https://salt.tikicdn.com/cache/w1200/ts/product/1a/f0/a2/90626a66ef0205023ad6e49274861b43.jpg",1));
        //itemList.add(new CartItem(2,"Iphone XS Max - Vàng",20000000,"https://cdn.tgdd.vn/Products/Images/42/190321/iphone-xs-max-gold-400x460.png",1));
        //itemList.add(new CartItem(3,"Samsung Note 10 - Xanh cực quang",10000000,"https://cdn.cellphones.com.vn/media/catalog/product/cache/7/image/9df78eab33525d08d6e5fb8d27136e95/n/o/note_10_plus_xanh_2.jpg",1));
    }


    public void calTotal(){
        List<CartItem> itemList = MainActivity.db.getAllCartItem();
        float total = 0;
        for(int i = 0; i< itemList.size(); i++)
            total+=(itemList.get(i).getAmount()* itemList.get(i).getPrice());
        edAmount = total;
        TextView textTotal = findViewById(R.id.total_cart);
        textTotal.setText(NumberFormat.getInstance().format(total)+ " đ");
        //Toast.makeText(getApplicationContext(),"Cal Total",Toast.LENGTH_SHORT).show();
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        setTitle(R.string.title_cart);

        this.overridePendingTransition(0,0);

        init();

        recyclerView = findViewById(R.id.viewCart);
        recyclerView.setHasFixedSize(true);
        cartAdapter = new CartItemAdapter(getApplicationContext(), MainActivity.db.getAllCartItem());


        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));


        recyclerView.setAdapter(cartAdapter);
        cartAdapter.notifyDataSetChanged();

        order_button = findViewById(R.id.order_button);
        order_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(MainActivity.user==null){
                    Toast.makeText(getApplicationContext(),"Hãy đăng nhập để đặt hàng",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(MainActivity.db.getCartItemCount()==0){
                    Toast.makeText(getApplicationContext(),"Giỏ hàng trống", Toast.LENGTH_SHORT).show();
                }else {
                    //Bundle items = new Bundle();
                    //tao du lieu nap cho vao thanh toan
                    Intent intent = new Intent(getApplicationContext(), SelectPaymentMethodActivity.class);
                    //intent.putExtra("edAmount",edAmount);
                    //intent.putExtras(items);
                    startActivity(intent);
                }
            }
        });

        calTotal();



    }
}
