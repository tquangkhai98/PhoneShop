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

import com.capstone.phoneshop.Activities.ListProductInCategory;
import com.capstone.phoneshop.Model.Category;
import com.capstone.phoneshop.R;
import com.squareup.picasso.Picasso;

import java.util.List;


public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    private Context context;
    private List<Category> categoryList;

    public CategoryAdapter(Context context, List<Category> categoryList){
        this.context = context;
        this.categoryList=categoryList;
    }

    @NonNull
    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, int viewType) {
        //return null;
        final View view;
        view= LayoutInflater.from(parent.getContext()).inflate(R.layout.category_cardview,parent,false);
        /*view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("Category","selected");
                Intent intent = new Intent(parent.getContext(), ListProductInCategory.class);
                parent.getContext().startActivity(intent);
            }
        });*/
        return new ViewHolder(view,context);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        final Category category = categoryList.get(position);
        holder.textView.setText(category.getCategoryName());
        //holder.imageButton.setImageResource(R.drawable.smartphone);
        Picasso.get()
                .load(categoryList.get(position).getImageURL()).
                error(R.drawable.alternate)
                .into(holder.imageCategory);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("Category","selected");
                Intent intent = new Intent(holder.itemView.getContext(), ListProductInCategory.class);
                intent.putExtra("category_ID",category.getCategoryID());
                holder.itemView.getContext().startActivity(intent);
            }
        });
        /*switch (position){
            case 0:
                holder.imageCategory.setImageResource(R.drawable.smartphone_tablet);break;
            case 1:
                holder.imageCategory.setImageResource(R.drawable.laptop);break;
            case 2:
                holder.imageCategory.setImageResource(R.drawable.tivi);break;
            case 3:
                holder.imageCategory.setImageResource(R.drawable.refrigerator);break;
            case 4:
                holder.imageCategory.setImageResource(R.drawable.washing_machine);break;
                default:
                    holder.imageCategory.setImageResource(R.drawable.alternate);
                    break;
        }*/

    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView imageCategory;
        public TextView textView;
        public ViewHolder(@NonNull View itemView, Context ctx) {
            super(itemView);
            context=ctx;
            imageCategory=itemView.findViewById(R.id.imageCategory);
            textView = itemView.findViewById(R.id.textCategory);

        }
    }
}
