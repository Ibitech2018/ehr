package com.divide.ibitech.divide_ibitech;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.HashMap;

public class Dashboard extends AppCompatActivity {

    TextView tv_FullName, tv_Age, tv_BloodType, tv_Address,tv_Gender,tv_MaritalStatus;
    String fullName, bloodType,address,gender,maritalStatus;
    Integer age;
    ImageView img_ProfilePic;
    Button btn_Logout;

    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        sessionManager = new SessionManager(this);
        sessionManager.checkLogin();



        tv_FullName = findViewById(R.id.tvName);
        tv_Age = findViewById(R.id.age);
        tv_BloodType = findViewById(R.id.bloodType);
        tv_Address = findViewById(R.id.tvAddress);
        tv_Gender = findViewById(R.id.gender);
        tv_MaritalStatus = findViewById(R.id.maritalStatus);

        img_ProfilePic = findViewById(R.id.imgProfilePic);

        btn_Logout = findViewById(R.id.btnLogout);

        HashMap<String,String> user = sessionManager.getUserDetails();
        String mName = user.get(sessionManager.NAME);
        //String mEmail = user.get(sessionManager.EMAIL);

        tv_FullName.setText(mName);
        //more preferences setText ...

        btn_Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sessionManager.logout();
            }
        });

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.aya);
        RoundedBitmapDrawable roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(getResources(),bitmap);
        roundedBitmapDrawable.setCircular(true);
        img_ProfilePic.setImageDrawable(roundedBitmapDrawable);

        //Should it be in IntroActivity ????
        SharedPreferences preferences = getSharedPreferences("userInfo",MODE_PRIVATE);
        fullName = preferences.getString("pFirstName" + " " + "pSurname","");
        //age
        bloodType = preferences.getString("pBloodType","");
        address = preferences.getString("pAddress","");
        gender = preferences.getString("pGender","");
        maritalStatus = preferences.getString("pMaritalStatus","");


        if(fullName.isEmpty() && bloodType.isEmpty() && address.isEmpty() && gender.isEmpty() && maritalStatus.isEmpty()){ //age is not included
            tv_FullName.setText(fullName);
            //tv_Age.setText(age);
            tv_BloodType.setText(bloodType);
            tv_Address.setText(address);
            tv_Gender.setText(gender);
            tv_MaritalStatus.setText(maritalStatus);
        }
    }
}
