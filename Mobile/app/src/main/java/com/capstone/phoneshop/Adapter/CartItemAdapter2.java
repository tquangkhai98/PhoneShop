package com.capstone.phoneshop.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.capstone.phoneshop.Activities.CartActivity;
import com.capstone.phoneshop.Activities.MainActivity;
import com.capstone.phoneshop.Model.CartItem;
import com.capstone.phoneshop.R;
import com.squareup.picasso.Picasso;


import java.text.NumberFormat;
import java.util.List;

public class CartItemAdapter2 extends RecyclerView.Adapter<CartItemAdapter2.ViewHolder>{

    private List<CartItem> itemList;
    private Context context;

    public CartItemAdapter2(Context context, List<CartItem> itemList){
        this.context = context;
        this.itemList = itemList;
    }


    @NonNull
    @Override
    public CartItemAdapter2.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item_card_view2,parent,false);
        return new ViewHolder(view,context);
    }

    @Override
    public void onBindViewHolder(@NonNull final CartItemAdapter2.ViewHolder holder, final int position) {
        final CartItem item = itemList.get(position);
        Picasso.get().load(item.getImageURL1()).error(R.drawable.alternate).into(holder.product_image);
        //Log.i("url",item.getImageURL().toString());
        holder.product_titlte.setText(item.getTitle());
        holder.product_price.setText(NumberFormat.getInstance().format(item.getPrice())+" đ");
        holder.product_amount.setText(item.getAmount()+"");


    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder  {

        public ImageView product_image;
        public TextView product_titlte;  // = product name + option
        public TextView product_price;
        public TextView product_amount;
        //public TextView total;


        public ViewHolder(@NonNull View itemView, Context cxt){
            super(itemView);
            context = cxt;
            //bt_delete = itemView.findViewById(R.id.bt_delete);
            //bt_plus = itemView.findViewById(R.id.bt_plus);
           // bt_minus = itemView.findViewById(R.id.bt_minus);
            product_image = itemView.findViewById(R.id.image_product_in_cart);
            product_titlte = itemView.findViewById(R.id.product_titlte_in_cart);
            product_price = itemView.findViewById(R.id.product_price_in_cart);
            product_amount = itemView.findViewById(R.id.amount);
            //total = itemView.findViewById(R.id.total_cart);
        }

    }
}


