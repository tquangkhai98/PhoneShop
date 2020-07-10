package com.capstone.phoneshop.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;

import com.capstone.phoneshop.R;

import ru.nikartm.support.ImageBadgeView;

public class BaseActivity extends AppCompatActivity {

    private ImageBadgeView badgeView;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ImageBadgeView badgeCart = findViewById(R.id.badge_cart);
        Log.i("BaseActivity","Active");
        /*badgeCart.setBadgeValue(5)
                .setMaxBadgeValue(20)
                .setLimitBadgeValue(true);*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.on_top_menu, menu);

        //getMenuInflater().inflate(R.menu.on_top_menu,menu);
        //final MenuItem menuItem = menu.findItem(R.id.action_cart);

        return true;

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId()==R.id.action_cart){
            Intent intent = new Intent(getApplicationContext(),CartActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }


}
