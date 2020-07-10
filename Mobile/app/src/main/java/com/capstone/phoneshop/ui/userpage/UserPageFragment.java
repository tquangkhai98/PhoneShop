package com.capstone.phoneshop.ui.userpage;

import android.content.Context;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.capstone.phoneshop.Activities.AddressInformationActivity;
import com.capstone.phoneshop.Activities.MainActivity;
import com.capstone.phoneshop.Activities.OrderedProductActivity;
import com.capstone.phoneshop.Activities.SignInActivity;
import com.capstone.phoneshop.Activities.TuVanActivity;
import com.capstone.phoneshop.Activities.UserDetailActivity;
import com.capstone.phoneshop.Data.DatabaseHandler;
import com.capstone.phoneshop.Model.User;
import com.capstone.phoneshop.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.card.MaterialCardView;
import com.squareup.picasso.Picasso;

import java.util.GregorianCalendar;

import static com.capstone.phoneshop.Activities.SignInActivity.mGoogleSignInClient;

public class UserPageFragment extends Fragment {

    private UserPageViewModel userPageViewModel;
    public static User currentUser;
    private MaterialCardView address;
    private MaterialCardView tuvan;
    private MaterialCardView ordered;


    public void initUser(){
        //import user data

        currentUser = MainActivity.user;
        //new GregorianCalendar(1998,1,3).getTime()
        //currentUser = new User(1,"Võ Văn Thái Sơn", new GregorianCalendar(1998,1,3).getTime(),0,"0976308098","sonvo0302@gmai.com","Đà Nẵng","https://scontent.fdad3-3.fna.fbcdn.net/v/t1.0-0/p552x414/68756685_907580156264726_2472861728870760448_n.jpg?_nc_cat=111&_nc_ohc=BaydYXVeScYAQmw82o2tIJUQSgJrz_gMi7KNxpbFI5XgKYU1KY2fpT3yg&_nc_ht=scontent.fdad3-3.fna&oh=6ab3bb8d52d438283c09ecbf74c372f3&oe=5E4D7065");
        //currentUser = new User("Võ Văn Thái Sơn","sonvo0302@gmail.com","https://scontent.fdad3-3.fna.fbcdn.net/v/t1.0-0/p552x414/68756685_907580156264726_2472861728870760448_n.jpg?_nc_cat=111&_nc_ohc=BaydYXVeScYAQmw82o2tIJUQSgJrz_gMi7KNxpbFI5XgKYU1KY2fpT3yg&_nc_ht=scontent.fdad3-3.fna&oh=6ab3bb8d52d438283c09ecbf74c372f3&oe=5E4D7065");
    }


    @Override
    public void onResume() {
        super.onResume();
        if(UserDetailActivity.refresh) {
            UserDetailActivity.refresh = false;
            currentUser = MainActivity.db.getUser();
            getFragmentManager().beginTransaction().detach(this).attach(this).commit();
        }

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, final Bundle savedInstanceState) {
        userPageViewModel =
                ViewModelProviders.of(this).get(UserPageViewModel.class);
        View root = inflater.inflate(R.layout.fragment_user_page, container, false);

        address = root.findViewById(R.id.address_information);
        address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(currentUser!=null)
                    startActivity(new Intent(getActivity(),AddressInformationActivity.class));
            }
        });

        tuvan = root.findViewById(R.id.requestTUVAN);
        tuvan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(currentUser!=null)
                    startActivity(new Intent(getActivity(), TuVanActivity.class));
            }
        });

        ordered = root.findViewById(R.id.productsOrdered);
        ordered.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(currentUser!=null)
                    startActivity(new Intent(getActivity(), OrderedProductActivity.class));
            }
        });

        initUser();

        if(currentUser==null){
            Log.i("User","null");
        }else {
            Log.i("User","not null");
        }
        //Log.i("active",MainActivity.active+"");

        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.add(this,"userpage");
        Log.i("TAG",getTag());
        /*final TextView textView = root.findViewById(R.id.text_userpage);
        userPageViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/
        /*Button btnlogOut  = root.findViewById(R.id.buttonLogInOut);
        btnlogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),LoginLogoutActivity.class);
                startActivity(intent);
            }
        });*/
        //TextView textEmail = root.findViewById(R.id.textEmail1);
        //textEmail.setText("Võ Văn Thái Sơn");

        // Picasso.get().load(currentUser.getImageURL()).into(viewHolder.avatar);

        final ViewHolder viewHolder = new ViewHolder(root.getRootView());

        if(currentUser!=null){ //nếu có người sử dụng

            //Picasso.get().load(currentUser.getImageURL()).into(viewHolder.avatar);
            if(currentUser.getImageURL()!=null&&!currentUser.getImageURL().equals("")){
                Picasso.get().load(currentUser.getImageURL())
                        .error(R.drawable.avatar_alternate)
                        .into(viewHolder.avatar);
            }else {

                viewHolder.avatar.setImageResource(R.drawable.avatar_alternate);
            }


            viewHolder.name.setText(currentUser.getUserName());
            viewHolder.email.setText(currentUser.getEmail());
            viewHolder.bt_signInSignOut.setText(R.string.name_button_signout);
            viewHolder.bt_signInSignOut.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //dang xuat
                    //Toast.makeText(getContext(),"Button selected",Toast.LENGTH_SHORT).show();
                    //MainActivity.active = false;
                    MainActivity.user=null;
                    MainActivity.db = new DatabaseHandler(getActivity());
                    MainActivity.db.deleteOldUser();
                    MainActivity.db.deleteAllCart();
                    MainActivity.db.deleteAddress();

                    /*if(MainActivity.acct!=null)
                        //signOut();*/
                    //revokeAccess();
                    if(mGoogleSignInClient!=null)
                        mGoogleSignInClient.signOut();


                    Fragment frg = null;
                    frg = getFragmentManager().findFragmentByTag("userpage");
                    final FragmentTransaction ft = getFragmentManager().beginTransaction();
                    ft.detach(frg);
                    ft.attach(frg);
                    ft.commit();

                }
            });

            LinearLayout userDetailButton = root.findViewById(R.id.userDetail);
            userDetailButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(currentUser!=null) {
                        Intent intent = new Intent(getActivity(), UserDetailActivity.class);
                        startActivity(intent);
                    }
                }
            });




        }else {
            viewHolder.name.setText(R.string.greeting);
            viewHolder.email.setText(R.string.recommend);
            viewHolder.bt_signInSignOut.setText(R.string.name_button_signin);
            viewHolder.avatar.setImageResource(R.drawable.avatar_alternate);
            viewHolder.bt_signInSignOut.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getContext(), SignInActivity.class);
                    startActivity(intent);
                }
            });
        }



        return root;
    }

    /*private void revokeAccess() {
        mGoogleSignInClient.revokeAccess()
                .addOnCompleteListener(getParentFragment().getActivity(), new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        // ...
                        //Toast.makeText(getContext(),"Sign Out Success",Toast.LENGTH_SHORT);
                    }
                });
    }

    private void signOut() {
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(getParentFragment().getActivity(), new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        // ...
                    }
                });
    }
*/


    public class ViewHolder{
        public ImageView avatar;
        public TextView name;
        public TextView email;
        public Button bt_signInSignOut;

        public ViewHolder(View view){
            this.avatar = view.findViewById(R.id.userAvatar);
            this.name = view.findViewById(R.id.textUserName1);
            this.email = view.findViewById(R.id.textEmail1);
            this.bt_signInSignOut = view.findViewById(R.id.buttonSignInOut);
        }
    }
}
