package com.capstone.phoneshop.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.icu.text.UnicodeSetSpanner;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.InputType;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.capstone.phoneshop.Data.ChangePassword;
import com.capstone.phoneshop.Data.UpdateProfile;
import com.capstone.phoneshop.Model.User;
import com.capstone.phoneshop.R;
import com.capstone.phoneshop.ui.DrawableClickListener;
import com.capstone.phoneshop.ui.home.HomeFragment;
import com.capstone.phoneshop.ui.userpage.UserPageFragment;
import com.capstone.phoneshop.ui.CustomEditText;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static com.capstone.phoneshop.ui.DrawableClickListener.DrawablePosition.RIGHT;

public class UserDetailActivity extends AppCompatActivity {

    private EditText textBirthday;
    private DatePickerDialog.OnDateSetListener mOnDateSetListener;
    private CheckBox checkBox;
    private LinearLayout changepasswordLayout;
    private Button saveButton;
    private CustomEditText oldPassword;
    private CustomEditText newPassword;
    private CustomEditText replaceNewPassword;
    private GregorianCalendar birthday;
    public static boolean refresh = false;
    public static String changePWResult = "Fail";
    //private UpdateProfile updater;


    private String name;
    private String birthdayText;
    private int sex;

    public String updateToServer(){
        try {
            return new UpdateProfile().execute(new String[]{MainActivity.user.getUserID()+"",name,birthdayText,sex+""}).get();
        }catch (Exception e){
            e.printStackTrace();
            Log.i("Lỗi","cmnr");
        }
        return "Fail";

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);
        setTitle(R.string.title_user_detail);

        //updater = new UpdateProfile();


        final ViewHolder viewHolder = new ViewHolder(findViewById(android.R.id.content).getRootView());
        //import dữ liệu nếu có
        if(MainActivity.user!=null){

            viewHolder.textUserName.setText(MainActivity.user.getUserName());
            viewHolder.textEmail.setText(MainActivity.user.getEmail());
            String textDate = "";
            if(MainActivity.user.getBirthday()!=null){
                Date birthday = MainActivity.user.getBirthday();
                DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                textDate = dateFormat.format(birthday);
                Log.i("Birthday",birthday.toString());
                Log.i("Birthday",textDate);
                viewHolder.textBirthday.setText(textDate);
                //textDate = new SimpleDateFormat("dd/MM/yyyy").parse(UserPageFragment.currentUser.getBirthday())
            }else {
                viewHolder.textBirthday.setText("Bổ sung");
            }

            if(MainActivity.user.getSex()==1){
                viewHolder.maleButton.setChecked(true);
                Log.i("Sex","Nam");
            }else {
                viewHolder.femaleButton.setChecked(true);
                Log.i("Sex","Nữ");
            }


        }else {
            finishActivity(1);  //neu ko co tai khoan ko vao dc
        }

        textBirthday = findViewById(R.id.textBirthday);
        textBirthday.setInputType(InputType.TYPE_NULL);

        textBirthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);

                DatePickerDialog dialog = new DatePickerDialog(
                        UserDetailActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mOnDateSetListener,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mOnDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                textBirthday.setText(dayOfMonth+"/"+(month+1)+"/"+year);
                birthday = new GregorianCalendar(year,month,dayOfMonth);
                birthdayText = year+"/"+(month+1)+"/"+dayOfMonth;
            }
        };
        checkBox = findViewById(R.id.changePasswordCheckBox);
        changepasswordLayout = findViewById(R.id.changePasswordLayout);

        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkBox.isChecked()){
                    changepasswordLayout.setVisibility(View.VISIBLE);

                    Log.i("Checkbox","checked");
                }
                else {
                    changepasswordLayout.setVisibility(View.INVISIBLE);
                    Log.i("Checkbox","no");
                }

            }
        });

        saveButton = findViewById(R.id.buttonSaveUserInfomation);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                User userUpdate = MainActivity.user;
                if(!viewHolder.textUserName.getText().toString().equals("")){
                    userUpdate.setUserName(viewHolder.textUserName.getText().toString());
                    name = viewHolder.textUserName.getText().toString();
                }

                Fragment frg = null;


                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                Date birthday = new Date();
                try {
                    birthday = (Date) formatter.parse(viewHolder.textBirthday.getText().toString());
                    userUpdate.setBirthday(birthday);
                } catch (ParseException e) {
                    e.printStackTrace();
                }


                if(viewHolder.maleButton.isChecked()){
                    userUpdate.setSex(1);
                    sex = 1;
                }else {
                    userUpdate.setSex(0);
                    sex = 0;
                }

                MainActivity.user = userUpdate;

                MainActivity.db.deleteOldUser();

                MainActivity.db.addUser(userUpdate); //update thong tin

                //Đỗi password chưa làm
                if(viewHolder.checkBoxChangePW.isChecked()){
                   String textOldPassword,textNewPassword,textReplacePassword;
                   textOldPassword = oldPassword.getText().toString();
                   textNewPassword = newPassword.getText().toString();
                   textReplacePassword = replaceNewPassword.getText().toString();
                   if(textOldPassword.equals("")||textNewPassword.equals("")||textReplacePassword.equals("")){
                       Toast.makeText(getApplicationContext(),"Không để trống",Toast.LENGTH_SHORT).show();
                       return;
                   }
                   if(!textNewPassword.equals(textReplacePassword)){
                       Toast.makeText(getApplicationContext(),"Mật khẩu và mật khẩu nhập lại không trùng nhau",Toast.LENGTH_SHORT).show();
                       return;
                   }

                    if(textOldPassword.equals(textNewPassword)){
                        Toast.makeText(getApplicationContext(),"Mật khẩu mới trùng mật khẩu cũ",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    new ChangePassword().execute(new String[]{MainActivity.user.getUserID()+"",textOldPassword,textNewPassword});
                    new CountDownTimer(1500,1000){
                        @Override
                        public void onTick(long millisUntilFinished) {

                        }

                        @Override
                        public void onFinish() {
                            if(changePWResult.equals("Success")){
                                Toast.makeText(getApplicationContext(),"Đổi mật khẩu thành công",Toast.LENGTH_LONG).show();
                            }else {
                                Toast.makeText(getApplicationContext(),"Đổi mật khẩu thất bại",Toast.LENGTH_LONG).show();
                            }
                        }
                    }.start();

                }


                refresh = true;
                //Toast.makeText(getApplicationContext(),userUpdate.getUserName(), Toast.LENGTH_SHORT).show();
                try {
                    String result = updateToServer();
                }catch (Exception e){
                    e.printStackTrace();
                    Log.i("Lỗi","cmnr");
                }



            }
        });

        oldPassword = findViewById(R.id.textOldPassword);
        oldPassword.setDrawableClickListener(new DrawableClickListener() {
            @Override
            public void onClick(DrawablePosition target) {
                if(target==RIGHT){
                    if(oldPassword.getTag().equals("hide")) {
                        oldPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                        oldPassword.setTag("unhide");
                        oldPassword.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.hide,0);
                        //Toast.makeText(getApplicationContext(), "Show", Toast.LENGTH_SHORT).show();
                    }else {
                        oldPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                        oldPassword.setTag("hide");
                        oldPassword.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.unhide,0);
                        //Toast.makeText(getApplicationContext(), "Hide", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        newPassword = findViewById(R.id.textNewPassword);
        newPassword.setDrawableClickListener(new DrawableClickListener() {
            @Override
            public void onClick(DrawablePosition target) {
                if(target==RIGHT){
                    if(newPassword.getTag().equals("hide")) {
                        newPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                        newPassword.setTag("unhide");
                        newPassword.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.hide,0);
                        //Toast.makeText(getApplicationContext(), "Show", Toast.LENGTH_SHORT).show();
                    }else {
                        newPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                        newPassword.setTag("hide");
                        newPassword.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.unhide,0);
                        //Toast.makeText(getApplicationContext(), "Hide", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        replaceNewPassword = findViewById(R.id.textReplacePassword);
        replaceNewPassword.setDrawableClickListener(new DrawableClickListener() {
            @Override
            public void onClick(DrawablePosition target) {
                if(target==RIGHT){
                    if(replaceNewPassword.getTag().equals("hide")) {
                        replaceNewPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                        replaceNewPassword.setTag("unhide");
                        replaceNewPassword.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.hide,0);
                        //Toast.makeText(getApplicationContext(), "Show", Toast.LENGTH_SHORT).show();
                    }else {
                        replaceNewPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                        replaceNewPassword.setTag("hide");
                        replaceNewPassword.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.unhide,0);
                        //Toast.makeText(getApplicationContext(), "Hide", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    public class ViewHolder{
        public EditText textUserName;
        public EditText textEmail;
        public EditText textBirthday;
        public RadioGroup sexGroup;
        public RadioButton maleButton;
        public RadioButton femaleButton;
        public CheckBox checkBoxChangePW;

        public ViewHolder(View view){
            textUserName = view.findViewById(R.id.textUsername);
            textEmail = view.findViewById(R.id.textEmail);
            textBirthday = view.findViewById(R.id.textBirthday);
            sexGroup = view.findViewById(R.id.sexGroup);
            maleButton = view.findViewById(R.id.maleRadioBtn);
            femaleButton = view.findViewById(R.id.femaleRadioBtn);
            checkBoxChangePW = view.findViewById(R.id.changePasswordCheckBox);
            //set thuoc tinh cho textview sinh nhat


        }

    }

}
