package com.capstone.phoneshop.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.capstone.phoneshop.Data.UpdateTUVANInformation;
import com.capstone.phoneshop.R;
import com.capstone.phoneshop.ui.userpage.UserPageFragment;

import java.util.ArrayList;
import java.util.List;

public class TuVanActivity extends AppCompatActivity {
    private List<Hobby> hobbyList;
    private List<Income> incomeList;
    //private List<String> generation;
    private Spinner spinnerHobby;
    private Spinner spinnerIncome;
    private List<String> textHobbyList;
    private  List<String> textIncomeList;
    //private Spinner spinnerGeneration;
    private Button buttonConfirm;

    //UpdateTUVANInformation

    public void init(){
        hobbyList = new ArrayList<>();
        incomeList = new ArrayList<>();
        //generation = new ArrayList<>();

        hobbyList.add(new Hobby(1,"Nghe nhạc"));
        hobbyList.add(new Hobby(2,"Chơi game"));
        hobbyList.add(new Hobby(4,"Lướt web"));
        hobbyList.add(new Hobby(5,"Chụp ảnh và quay video"));

        textHobbyList = new ArrayList<>();
        for(Hobby hobby : hobbyList){
            textHobbyList.add(hobby.hobbyName);
        }

        incomeList.add(new Income(1,"Cao (trên 20 triệu)"));
        incomeList.add(new Income(2,"Trung bình (trên 7 triệu)"));
        incomeList.add(new Income(3,"Thấp (dưới 7 triệu)"));

        textIncomeList = new ArrayList<>();
        for(Income income : incomeList){
            textIncomeList.add(income.incomeName);
        }

        /*generation.add("Trẻ");
        generation.add("Trung niên");
        generation.add("Già");*/
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tu_van);
        setTitle("Tư vấn sản phẩm");

        //updater = new UpdateTUVANInformation();

        spinnerHobby = findViewById(R.id.hobby);

        spinnerIncome = findViewById(R.id.incomeLevel);

        //spinnerGeneration = findViewById(R.id.generation);
        //pinnerGeneration.setVisibility(View.GONE);
        buttonConfirm  = findViewById(R.id.confirmbutton);


        init();

        spinnerHobby.setAdapter(new ArrayAdapter<>(this,R.layout.support_simple_spinner_dropdown_item,textHobbyList));
        spinnerIncome.setAdapter(new ArrayAdapter<>(this,R.layout.support_simple_spinner_dropdown_item, textIncomeList));
        //spinnerGeneration.setAdapter(new ArrayAdapter<>(this,R.layout.support_simple_spinner_dropdown_item,generation));

        buttonConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int userid = MainActivity.user.getUserID();
                int hobbyID = 0;
                switch (spinnerHobby.getSelectedItemPosition()){
                    case 0 : hobbyID =1; break;
                    case 1 : hobbyID =2; break;
                    case 2 : hobbyID =4; break;
                    case 3 : hobbyID =5; break;
                }

                int incomeLevel = spinnerIncome.getSelectedItemPosition()+1;

                Toast.makeText(getApplicationContext(),"Loading...",Toast.LENGTH_LONG).show();


                new UpdateTUVANInformation().execute(userid,hobbyID,incomeLevel);
                //startActivity(new Intent(getApplicationContext(),ListTUVANProductActivity.class));
                new CountDownTimer(1500,1000){
                    @Override
                    public void onTick(long millisUntilFinished) {

                    }

                    @Override
                    public void onFinish() {
                        startActivity(new Intent(getApplicationContext(),ListTUVANProductActivity.class));
                    }
                }.start();

            }
        });

        //nap du lieu cho spinnner
    }

    public class Hobby{
        private int id;
        private String hobbyName;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getHobbyName() {
            return hobbyName;
        }

        public void setHobbyName(String hobbyName) {
            this.hobbyName = hobbyName;
        }

        public Hobby(int id, String hobbyName){
            this.id = id;
            this.hobbyName=hobbyName;
        }

        public Hobby(){
        }

    }
    public class Income{
        private int id;
        private String incomeName;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getIncomeName() {
            return incomeName;
        }

        public void setIncomeName(String incomeName) {
            this.incomeName = incomeName;
        }

        public Income(int id, String incomeName) {
            this.id = id;
            this.incomeName = incomeName;
        }

        public Income(){

        }
    }



}
