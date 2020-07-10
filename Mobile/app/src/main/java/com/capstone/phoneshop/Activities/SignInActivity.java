package com.capstone.phoneshop.Activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.capstone.phoneshop.Data.DatabaseHandler;
import com.capstone.phoneshop.Data.DownloaderLoginInformation;
import com.capstone.phoneshop.Model.User;
import com.capstone.phoneshop.R;
import com.capstone.phoneshop.R2;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.GregorianCalendar;


public class SignInActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView email_username;
    private TextView password;
    private LinearLayout signInLayout;
    private ImageButton bt_signin_with_google;
    public static GoogleSignInClient mGoogleSignInClient;
    private SignInButton googleSignInButton;
    private Button normalSignInButton;
    final int RC_SIGN_IN = 0;   //login with google
    private String TAG = "Sign In";
    private TextView signup;
    private TextView title;
    private DownloaderLoginInformation downloader;
    public static User newUser;
    public static boolean flag = false;





    public void signInNormal(){
        Toast.makeText(getApplicationContext(),"Đang đang nhập",Toast.LENGTH_SHORT).show();
        if((email_username.getText().toString().equals("")||email_username.getText().toString()==null)&&(password.getText().toString().equals("")||password.getText().toString()==null)) {
            Toast.makeText(getApplicationContext(),"Hai trường trống",Toast.LENGTH_SHORT).show();
        }else{
            if (email_username.getText().toString().equals("") || email_username.getText().toString() == null) {
                Toast.makeText(getApplicationContext(), "Username hoặc email trống", Toast.LENGTH_SHORT).show();
            } else {
                if (password.getText().toString().equals("") || password.getText().toString() == null)
                    Toast.makeText(getApplicationContext(), "Password trống", Toast.LENGTH_SHORT).show();
                else {
                    try {

                        downloader.execute(new String[]{email_username.getText().toString(),password.getText().toString()});

                    }catch (Exception e){
                        e.printStackTrace();
                    }

                    new CountDownTimer(2000,1000){
                        @Override
                        public void onTick(long millisUntilFinished) {

                        }

                        @Override
                        public void onFinish() {
                            try {
                                if(newUser.getUserID()!=0){
                                    Toast.makeText(getApplicationContext(),"Xin chào "+newUser.getUserName(),Toast.LENGTH_SHORT).show();
                                    MainActivity.db = new DatabaseHandler(getApplicationContext());
                                    MainActivity.db.addUser(newUser);

                                    startActivity(new Intent(getApplicationContext(),MainActivity.class));

                                    //Toast.makeText(getApplicationContext(),"Đăng nhập thất bại",Toast.LENGTH_SHORT).show();

                                }else {
                                    Toast.makeText(getApplicationContext(),"Đăng nhập thất bại",Toast.LENGTH_SHORT).show();
                                }
                            }catch (Exception e){
                                e.printStackTrace();
                                Toast.makeText(getApplicationContext(),"Đăng nhập thất bại",Toast.LENGTH_SHORT).show();
                                finish();
                                overridePendingTransition(0, 0);
                                startActivity(getIntent());
                                overridePendingTransition(0, 0);
                            }


                        }
                    }.start();

                    //User newUser = new User(1,"Võ Văn Thái Sơn", new GregorianCalendar(1998,1,3).getTime(),0,"0976308098","sonvo0302@gmai.com","Đà Nẵng","https://scontent.fdad3-3.fna.fbcdn.net/v/t1.0-0/p552x414/68756685_907580156264726_2472861728870760448_n.jpg?_nc_cat=111&_nc_ohc=BaydYXVeScYAQmw82o2tIJUQSgJrz_gMi7KNxpbFI5XgKYU1KY2fpT3yg&_nc_ht=scontent.fdad3-3.fna&oh=6ab3bb8d52d438283c09ecbf74c372f3&oe=5E4D7065");
                    //MainActivity.db.addUser(newUser);

                }
            }
        }
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        newUser = new User();
        downloader = new DownloaderLoginInformation();
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        normalSignInButton = findViewById(R.id.bt_normal_signin);
        normalSignInButton.setOnClickListener(this);
        googleSignInButton = findViewById(R.id.sign_in_button);
        googleSignInButton.setSize(SignInButton.SIZE_WIDE);

        googleSignInButton.setOnClickListener(this);



        setTitle(R.string.name_button_signin);

        email_username = findViewById(R.id.edit_text_email_username);
        password = findViewById(R.id.edit_text_password);
        signInLayout = findViewById(R.id.signInLayout);
        signup = findViewById(R.id.signup);
        title = findViewById(R.id.title);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),SignUpActivity.class));
            }
        });

        password.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER) {

                    signInNormal();

                }
                return false;
            }
        });
        signInLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v.getId()==R.id.signInLayout){
                    //InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                    //inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);
                    //hideSoftKeyboard(SignInActivity.this);
                }
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        updateUI(account);
    }

    private void updateUI(@Nullable GoogleSignInAccount account) {
        if (account != null) {
            Log.i("Google user","Yes");
           /* mStatusTextView.setText(getString(R.string.signed_in_fmt, account.getDisplayName()));

            findViewById(R.id.sign_in_button).setVisibility(View.GONE);
            findViewById(R.id.sign_out_and_disconnect).setVisibility(View.VISIBLE);*/
        } else {
            Log.i("Google user","No");
           /* mStatusTextView.setText(R.string.signed_out);

            findViewById(R.id.sign_in_button).setVisibility(View.VISIBLE);
            findViewById(R.id.sign_out_and_disconnect).setVisibility(View.GONE);*/
        }
    }

    public void signInWithGoogle(){
        //Toast.makeText(getApplicationContext(),"Sign In with google",Toast.LENGTH_SHORT).show();
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
            startActivity(new Intent(getApplicationContext(),MainActivity.class).putExtra("SignIn","Google"));
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            // Signed in successfully, show authenticated UI.
            updateUI(account);
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w(TAG, "signInResult:failed code=" + e.getStatusCode());
            updateUI(null);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sign_in_button:
                signInWithGoogle();
                break;
            case R.id.bt_normal_signin:
                signInNormal();
                break;

        }
    }

    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(
                activity.getCurrentFocus().getWindowToken(), 0);
    }
}

