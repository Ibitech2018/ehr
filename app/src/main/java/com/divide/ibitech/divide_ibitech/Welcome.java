package com.divide.ibitech.divide_ibitech;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewParent;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;

import javax.security.auth.login.LoginException;

import io.ghyeok.stickyswitch.widget.StickySwitch;

public class Welcome extends AppCompatActivity {
    //private ViewPager mPager;
    //private    int [] layouts={R.layout.activity_slide_one,R.layout.activity_slide_two};
    LinearLayout topPart, bottomPart;
    Button registerButton,loginButton;
    Animation uptodown,downtoup;
    StickySwitch stickySwitch;
    //private  MpagerAdapter mpagerAdapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);


        topPart = findViewById(R.id.topPart);
        bottomPart = findViewById(R.id.bottomPart);
        uptodown = AnimationUtils.loadAnimation(this,R.anim.uptodown);
        downtoup = AnimationUtils.loadAnimation(this,R.anim.downtoup);
        topPart.setAnimation(uptodown);
        bottomPart.setAnimation(downtoup);

        registerButton = findViewById(R.id.btnRegister);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openRegister();
            }
        });

        loginButton = findViewById(R.id.btnLogin);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openLogin();
            }
        });

        //Sticky toggle switch

        stickySwitch = findViewById(R.id.stick_switch);

        stickySwitch.setOnSelectedChangeListener(new StickySwitch.OnSelectedChangeListener() {
            @Override
            public void onSelectedChange(@NotNull StickySwitch.Direction direction,@NotNull String s) {
                Toast.makeText(getBaseContext(), "Now selected : ", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void openRegister() {
        Intent intent = new Intent(this, Register.class);
        startActivity(intent);
    }
    private void openLogin() {
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }
}


