package com.capstone.phoneshop.Activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.capstone.phoneshop.Data.DatabaseHandler;
import com.capstone.phoneshop.Data.DownloaderAddress;
import com.capstone.phoneshop.Data.DownloaderListCategory;
import com.capstone.phoneshop.Data.DownloaderListSumaryProduct;
import com.capstone.phoneshop.Model.Address;
import com.capstone.phoneshop.Model.User;
import com.capstone.phoneshop.R;
import com.capstone.phoneshop.Utils.UtilDB;
import com.capstone.phoneshop.ui.MovableFloatingActionButton;
import com.capstone.phoneshop.ui.category.CategoryFragment;
import com.capstone.phoneshop.ui.home.HomeFragment;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.material.bottomnavigation.BottomNavigationView;



import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.util.ArrayList;


public class MainActivity extends BaseActivity {

    //public static boolean active = true;
    public static User user;
    //public static List<CartItem> itemList;
    public static DatabaseHandler db;
    private MovableFloatingActionButton chatButton;
    public static GoogleSignInAccount acct;
    //private DownloaderAddress downloaderAddress;

    private final int REQ_CODE_SPEECH_INPUT = 100;

    public static  Address address;

    public void init(){

        //DownloaderLoginInformation downloader = new DownloaderLoginInformation();
        //downloader.equals(new String[]{"duytuong","2512"});
        //set nguoi dung
        //if(active)
            //Intent intent = getIntent();
            //boolean isLogin = intent.getBooleanExtra("login",false);
           // if(isLogin==false)
        //deleteDatabase(UtilDB.DATABASE_NAME);

        //user = new User(1,"Võ Văn Thái Sơn", new GregorianCalendar(1998,1,3).getTime(),0,"0976308098","sonvo0302@gmai.com","Đà Nẵng","https://scontent.fdad3-3.fna.fbcdn.net/v/t1.0-0/p552x414/68756685_907580156264726_2472861728870760448_n.jpg?_nc_cat=111&_nc_ohc=BaydYXVeScYAQmw82o2tIJUQSgJrz_gMi7KNxpbFI5XgKYU1KY2fpT3yg&_nc_ht=scontent.fdad3-3.fna&oh=6ab3bb8d52d438283c09ecbf74c372f3&oe=5E4D7065");

        db = new DatabaseHandler(this);
        //Address address = new Address(1,"Trần Quang Khải","0976308098",null,"Hòa Vang","Hòa Liên","Quan Nam 3");
        //db.addAddress(address);

        user = db.getUser();

        if(user!=null&&db.getAddress()==null){
            new DownloaderAddress().execute(user.getUserID());
            Toast.makeText(getApplicationContext(),"Downloading information",Toast.LENGTH_LONG).show();
            new CountDownTimer(1500,1000){
                @Override
                public void onTick(long millisUntilFinished) {




                }

                @Override
                public void onFinish() {
                    if(user.getUserID()!=0)
                        db.addAddress(new Address(address.getUserID(),address.getFullname(),address.getPhoneNumber(),address.getCity(),address.getDistrict(),address.getWards(),address.getDetailAddress()));
                    //db.addAddress(new Address(address.getUserID(),address.getFullname(),address.getPhoneNumber(),address.getCity(),address.getDistrict(),address.getWards(),address.getDetailAddress()));
                }
            }.start();
        }


        if (user==null&&getIntent().getStringExtra("SignIn").equals("Google")){


            acct = GoogleSignIn.getLastSignedInAccount(this);
            if (acct != null) {
                String personName = acct.getDisplayName();
                String personGivenName = acct.getGivenName();
                String personFamilyName = acct.getFamilyName();
                String personEmail = acct.getEmail();
                String personId = acct.getId();
                Uri personPhoto = acct.getPhotoUrl();
                User googleUser = new User(0,personGivenName+" "+personFamilyName,personEmail,personPhoto.toString());
                user = googleUser;
                db.addUser(googleUser);

            }
        }

        HomeFragment.productList = new ArrayList<>();
        CategoryFragment.categoryList = new ArrayList<>();
        try {
            new DownloaderListSumaryProduct().execute("");
            //new DownloaderListCategory().execute("");
        }catch (Exception e){
            e.printStackTrace();
        }



        //db.deleteOldUser();

        //Log.i("Number of user",db.getCountUser()+"");

        //db.addUser(new User(1,"Võ Văn Thái Sơn", new GregorianCalendar(1998,1,3).getTime(),1,"0976308098","sonvo0302@gmail.com","Đà Nẵng","https://scontent.fdad3-3.fna.fbcdn.net/v/t1.0-0/p552x414/68756685_907580156264726_2472861728870760448_n.jpg?_nc_cat=111&_nc_ohc=BaydYXVeScYAQmw82o2tIJUQSgJrz_gMi7KNxpbFI5XgKYU1KY2fpT3yg&_nc_ht=scontent.fdad3-3.fna&oh=6ab3bb8d52d438283c09ecbf74c372f3&oe=5E4D7065"));
        //db.addUser(new User(1,"Trần Quang Khải","chenguangbankai@gmail.com","https://scontent.fsgn5-2.fna.fbcdn.net/v/t1.0-1/p240x240/65781335_1369044639936846_7825645702842679296_o.jpg?_nc_cat=105&_nc_ohc=IFPrRTl1l0YAQnwHChYKrZW4wETTic7yN86JrvR6BKsFcLFfregH_hKrQ&_nc_ht=scontent.fsgn5-2.fna&oh=e9473b8a0fffc3db9efa8f153fbc9989&oe=5E7D5D65"));

        //Log.i("Name",user.getUserName());
        //getApplicationContext().deleteDatabase(UtilDB.DATABASE_NAME);
        //db.addCartItem(new CartItem(3,"Iphone XS Max - Vàng",20000000,"https://cdn.tgdd.vn/Products/Images/42/190321/iphone-xs-max-gold-400x460.png",1));
        //db.addCartItem(new CartItem(3,"Samsung Note 10 - Xanh cực quang",20000000,"https://cdn.cellphones.com.vn/media/catalog/product/cache/7/image/9df78eab33525d08d6e5fb8d27136e95/n/o/note_10_plus_xanh_2.jpg",1));
        //db.addCartItem(new CartItem(1,"Iphone X - Đen",15000000,"https://salt.tikicdn.com/cache/w1200/ts/product/1a/f0/a2/90626a66ef0205023ad6e49274861b43.jpg",1));
        //itemList = db.getAllCartItem();

        //CartItem item = db.getCartItem(1);
        //itemList.add(item);

        //
        //db.addCartItem(new CartItem());
        //CartItem newItem = db.getCartItem(0);

        //db.addCartItem(new CartItem(3,"Iphone XS Max - Vàng",20000000,"https://cdn.tgdd.vn/Products/Images/42/190321/iphone-xs-max-gold-400x460.png",1));
        //CartItem newItem = db.getCartItem(0);
        //itemList.add(db.getCartItem(3));


        //itemList = new ArrayList<>();
        //itemList.add(new CartItem(1,"Iphone X - Đen",15000000,"https://salt.tikicdn.com/cache/w1200/ts/product/1a/f0/a2/90626a66ef0205023ad6e49274861b43.jpg",1));
        //itemList.add(new CartItem(2,"Iphone XS Max - Vàng",20000000,"https://cdn.tgdd.vn/Products/Images/42/190321/iphone-xs-max-gold-400x460.png",1));
        //itemList.add(new CartItem(3,"Samsung Note 10 - Xanh cực quang",20000000,"https://cdn.cellphones.com.vn/media/catalog/product/cache/7/image/9df78eab33525d08d6e5fb8d27136e95/n/o/note_10_plus_xanh_2.jpg",1));
        //else user=null;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_search_bar, R.id.navigation_notifications,R.id.navigation_user_page)
                .build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        //downloaderAddress = new DownloaderAddress();
        //newAddress = new Address();
        init();

        chatButton = findViewById(R.id.chat_button);
        chatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getApplicationContext(),"Chatbox selected",Toast.LENGTH_SHORT).show();
                //promptSpeechInput();
                startActivity(new Intent(getApplicationContext(), ChatboxActivity.class));

            }
        });
        //Intent intent = getIntent();
        //boolean isLogin = intent.getBooleanExtra("SignUpData",false);


    }




}
