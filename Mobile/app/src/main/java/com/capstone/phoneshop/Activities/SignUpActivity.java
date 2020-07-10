package com.capstone.phoneshop.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.capstone.phoneshop.Adapter.CategoryAdapter;
import com.capstone.phoneshop.Data.SignUpData;
import com.capstone.phoneshop.R;

public class SignUpActivity extends AppCompatActivity {
    private TextView signin;
    public static int userID = 0;

    private EditText username;
    private EditText email;
    private EditText password;

    private Button signUpButton;

    private SignUpData signupData;



    public void signUp(){
        Toast.makeText(getApplicationContext(),"Đang đăng ký",Toast.LENGTH_SHORT).show();
        if(username.getText().toString().equals("")||email.getText().toString().equals("")||password.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(),"Không để trống",Toast.LENGTH_SHORT).show();
            return;
        }
        String textUserName = username.getText().toString();
        String textEmail = email.getText().toString();
        String textPassword = password.getText().toString();

        signupData.execute(new String[]{textUserName,textPassword,textEmail});

        new CountDownTimer(2000,1000){
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                if(userID!=0){
                    Toast.makeText(getApplicationContext(),"Đăng ký thành công",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(),SignInActivity.class));
                }else {
                    Toast.makeText(getApplicationContext(),"Đăng ký thất bại",Toast.LENGTH_SHORT).show();
                }
            }
        }.start();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        setTitle("Đăng ký");

        signin = findViewById(R.id.signin);
        username = findViewById(R.id.edit_text_email_username);
        email = findViewById(R.id.edit_text_email);
        password = findViewById(R.id.edit_text_password);

        signupData = new SignUpData();

        signUpButton = findViewById(R.id.bt_signup);
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Đang đăng ký...",Toast.LENGTH_SHORT).show();
                signUp();
        }
        });



        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),SignInActivity.class));
            }
        });




    }
}
