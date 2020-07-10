package com.capstone.phoneshop.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.capstone.phoneshop.Data.UpdateAddressToServer;
import com.capstone.phoneshop.Model.Address;
import com.capstone.phoneshop.Model.User;
import com.capstone.phoneshop.R;
import com.capstone.phoneshop.ui.userpage.UserPageFragment;

public class AddressInformationActivity extends AppCompatActivity {

    private Address address;
    //private UpdateAddressToServer updater;
    private Button confirmAddress;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_information);
        setTitle(R.string.address);
        //updater = new UpdateAddressToServer();
        confirmAddress = findViewById(R.id.confirmAddress);

        final ViewHolder viewHolder = new ViewHolder(findViewById(android.R.id.content).getRootView());

        //Address address = new Address(1,"Trần Quang Khải","0976308098","Đà Nẵng","Hòa Vang","Hòa Liên","Quan Nam 3");
        Intent intent = getIntent();
        boolean isBuy = intent.getBooleanExtra("buy",false);
        if(isBuy == true){
            viewHolder.saveAddress.setVisibility(View.GONE);
            confirmAddress.setVisibility(View.VISIBLE);
        }else {
            viewHolder.saveAddress.setVisibility(View.VISIBLE);
            confirmAddress.setVisibility(View.GONE);
        }

        Address address = MainActivity.db.getAddress();
        if(MainActivity.user!=null){   //set du lieu cho edittext
            if(address!=null) {
                viewHolder.fullname.setText(address.getFullname());
                viewHolder.phonenumber.setText(address.getPhoneNumber() + "");
                viewHolder.city.setText(address.getCity());
                viewHolder.district.setText(address.getDistrict());
                viewHolder.wards.setText(address.getWards());
                viewHolder.detail.setText(address.getDetailAddress());
            }else {
                viewHolder.fullname.setText(MainActivity.user.getUserName());
                if(MainActivity.user.getPhoneNumber()!=null)
                    viewHolder.phonenumber.setText(MainActivity.user.getPhoneNumber()+"");
            }

        }

        viewHolder.saveAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Address newAddress = new Address();
                //newAddress.setUserID(UserPageFragment.currentUser.getUserID());
                //newAddress.setFullname(UserPageFragment.currentUser.getUserName());
                newAddress.setUserID(UserPageFragment.currentUser.getUserID());
                if(!viewHolder.fullname.getText().toString().equals(""))
                    newAddress.setFullname(viewHolder.fullname.getText().toString());
                if(!viewHolder.phonenumber.getText().toString().equals(""))
                    newAddress.setPhoneNumber(viewHolder.phonenumber.getText().toString());
                if(!viewHolder.city.getText().toString().equals(""))
                    newAddress.setCity(viewHolder.city.getText().toString());
                if(!viewHolder.district.getText().toString().equals(""))
                    newAddress.setDistrict(viewHolder.district.getText().toString());
                if(!viewHolder.wards.getText().toString().equals(""))
                    newAddress.setWards(viewHolder.wards.getText().toString());
                if(!viewHolder.detail.getText().toString().equals(""))
                    newAddress.setDetailAddress(viewHolder.detail.getText().toString());
                MainActivity.db.addAddress(newAddress);
                Toast.makeText(getApplicationContext(),"Save",Toast.LENGTH_SHORT).show();
                try {
                    new UpdateAddressToServer().execute(new String[]{newAddress.getUserID()+"",newAddress.getFullname(),newAddress.getPhoneNumber(),newAddress.getCity(),newAddress.getDistrict(),newAddress.getWards(),newAddress.getDetailAddress()});
                }catch (Exception e){
                    e.printStackTrace();
                    Log.i("Save to server",e.getMessage());
                }
            }
        });

        confirmAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Address newAddress = new Address();
                //newAddress.setUserID(UserPageFragment.currentUser.getUserID());
                //newAddress.setFullname(UserPageFragment.currentUser.getUserName());
                newAddress.setUserID(MainActivity.user.getUserID());
                if(!viewHolder.fullname.getText().toString().equals(""))
                    newAddress.setFullname(viewHolder.fullname.getText().toString());
                if(!viewHolder.phonenumber.getText().toString().equals(""))
                    newAddress.setPhoneNumber(viewHolder.phonenumber.getText().toString());
                if(!viewHolder.city.getText().toString().equals(""))
                    newAddress.setCity(viewHolder.city.getText().toString());
                if(!viewHolder.district.getText().toString().equals(""))
                    newAddress.setDistrict(viewHolder.district.getText().toString());
                if(!viewHolder.wards.getText().toString().equals(""))
                    newAddress.setWards(viewHolder.wards.getText().toString());
                if(!viewHolder.detail.getText().toString().equals(""))
                    newAddress.setDetailAddress(viewHolder.detail.getText().toString());
                MainActivity.db.addAddress(newAddress);
                Toast.makeText(getApplicationContext(),"Save",Toast.LENGTH_SHORT).show();
                try {
                    new UpdateAddressToServer().execute(new String[]{newAddress.getUserID()+"",newAddress.getFullname(),newAddress.getPhoneNumber(),newAddress.getCity(),newAddress.getDistrict(),newAddress.getWards(),newAddress.getDetailAddress()});
                }catch (Exception e){
                    e.printStackTrace();
                    Log.i("Save to server",e.getMessage());
                }
                startActivity(new Intent(getApplicationContext(),OrderSuccessActivity.class).putExtra("payID",getIntent().getIntExtra("payID",0)));

            }
        });
    }

    public class ViewHolder{
        public EditText fullname;
        public EditText phonenumber;
        public EditText city;
        public EditText district;
        public EditText wards;
        public EditText detail;
        public Button saveAddress;

        public ViewHolder(View view){
            fullname = view.findViewById(R.id.fullname);
            phonenumber = view.findViewById(R.id.phonenumber);
            city = view.findViewById(R.id.textCity);
            district = view.findViewById(R.id.textDistrict);
            wards = view.findViewById(R.id.textWards);
            detail = view.findViewById(R.id.textDetail);
            saveAddress = view.findViewById(R.id.saveAddress);
        }

    }
}
