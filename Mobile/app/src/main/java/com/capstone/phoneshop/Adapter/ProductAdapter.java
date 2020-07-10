package com.capstone.phoneshop.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.capstone.phoneshop.Activities.CartActivity;
import com.capstone.phoneshop.Activities.ProductDetailActivity;
import com.capstone.phoneshop.Model.Product;
import com.capstone.phoneshop.R;
import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder>{

    private Context context;
    private List<Product> productList;
    //private Intent intent;


    public ProductAdapter(Context context, List<Product> productList) {
        this.context = context;
        this.productList = productList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, int viewType) {
        //View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.banner_card_view,parent,false);
        final View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_cardview, parent, false);
        //intent = new Intent(parent.getContext(), ProductDetailActivity.class);
        /*view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("Product","selected");
                //Toast.makeText(view.getContext(),"Product Seleted",Toast.LENGTH_SHORT).show();
                //Intent intent = new Intent(parent.getContext(), ProductDetailActivity.class);
                parent.getContext().startActivity(intent);
            }
        });*/
        return new ViewHolder(view,context);
    }


    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        final Product product = productList.get(position);
        holder.title.setText(product.getName());
        holder.price.setText(NumberFormat.getInstance().format(product.getPrice()) +  " đ");
        if(product.getImageURL()!=null&&!product.getImageURL().equals("")){
            Picasso.get().load(product.getImageURL())
                    .error(R.drawable.alternate)
                    .into(holder.image);
        }else {
            holder.image.setImageResource(R.drawable.alternate);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(holder.itemView.getContext(),"Product selected",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(holder.itemView.getContext(), ProductDetailActivity.class);
                intent.putExtra("product_ID",product.getProductID());
                holder.itemView.getContext().startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return productList.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView title;
        public TextView price;
        public ImageView image;
        public ViewHolder(@NonNull View itemView, Context ctx) {
            super(itemView);
            context = ctx;
            title = itemView.findViewById(R.id.titleText);
            price = itemView.findViewById(R.id.priceText);
            image = itemView.findViewById(R.id.imageProduct);
        }

    }
}

