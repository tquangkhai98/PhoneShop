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

public class CartItemAdapter extends RecyclerView.Adapter<CartItemAdapter.ViewHolder>{

    private List<CartItem> itemList;
    private Context context;

    public CartItemAdapter(Context context, List<CartItem> itemList){
        this.context = context;
        this.itemList = itemList;
    }


    @NonNull
    @Override
    public CartItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item_card_view,parent,false);
        return new ViewHolder(view,context);
    }

    @Override
    public void onBindViewHolder(@NonNull final CartItemAdapter.ViewHolder holder, final int position) {
        final CartItem item = itemList.get(position);
        Picasso.get().load(item.getImageURL()).error(R.drawable.alternate).into(holder.product_image);
        //Picasso.get().load(item.getImageURL()).error(R.drawable.alternate).into(holder.product_image);
        holder.product_titlte.setText(item.getTitle());
        holder.product_price.setText(NumberFormat.getInstance().format(item.getPrice())+" đ");
        holder.product_amount.setText(item.getAmount()+"");
        holder.bt_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(context,"Deleted ",Toast.LENGTH_SHORT).show();
                itemList.remove(item);
                MainActivity.db.deleteCartItem(item.getItemID());
                CartItemAdapter.this.notifyDataSetChanged();
                context.startActivity(new Intent(context,CartActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_NO_ANIMATION));
                Log.i("Item list",itemList.toString());
            }
        });
        holder.bt_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(context,"Item_ID : "+item.getItem_ID(),Toast.LENGTH_SHORT).show();
                item.setAmount(item.getAmount()+1);
                MainActivity.db.updateCartItem(item);
                CartItemAdapter.this.notifyDataSetChanged();
                context.startActivity(new Intent(context,CartActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_NO_ANIMATION));
            }
        });

        holder.bt_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(item.getAmount()==1){
                    return;
                }
                item.setAmount(item.getAmount()-1);
                MainActivity.db.updateCartItem(item);
                CartItemAdapter.this.notifyDataSetChanged();
                context.startActivity(new Intent(context,CartActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_NO_ANIMATION));
            }
        });

    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder  {

        public ImageButton bt_delete;
        public ImageView product_image;
        public TextView product_titlte;  // = product name + option
        public TextView product_price;
        public TextView product_amount;
        public ImageButton bt_plus;
        public ImageButton bt_minus;
        public TextView total;


        public ViewHolder(@NonNull View itemView, Context cxt){
            super(itemView);
            context = cxt;
            bt_delete = itemView.findViewById(R.id.bt_delete);
            bt_plus = itemView.findViewById(R.id.bt_plus);
            bt_minus = itemView.findViewById(R.id.bt_minus);
            product_image = itemView.findViewById(R.id.image_product_in_cart);
            product_titlte = itemView.findViewById(R.id.product_titlte_in_cart);
            product_price = itemView.findViewById(R.id.product_price_in_cart);
            product_amount = itemView.findViewById(R.id.amount);
            total = itemView.findViewById(R.id.total_cart);
        }

    }
}


