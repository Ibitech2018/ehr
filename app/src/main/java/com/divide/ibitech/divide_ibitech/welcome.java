package com.divide.ibitech.divide_ibitech;

import android.graphics.Typeface;
import android.os.Build;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewParent;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class welcome extends AppCompatActivity {
    //private ViewPager mPager;
    //private    int [] layouts={R.layout.activity_slide_one,R.layout.activity_slide_two};
    LinearLayout topPart, bottomPart;
    Button registerButton,loginButton;
    Animation uptodown,downtoup;
    //private  MpagerAdapter mpagerAdapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        registerButton = findViewById(R.id.btnRegister);
        loginButton = findViewById(R.id.btnLogin);
        topPart = findViewById(R.id.topPart);
        bottomPart = findViewById(R.id.bottomPart);
        uptodown = AnimationUtils.loadAnimation(this,R.anim.uptodown);
        downtoup = AnimationUtils.loadAnimation(this,R.anim.downtoup);
        topPart.setAnimation(uptodown);
        bottomPart.setAnimation(downtoup);

/*if (Build.VERSION.SDK_INT >= 19)

{
    getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
}
else
{
getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS );

}
            mPager= (ViewPager)findViewById(R.id.viewPager);

            mpagerAdapter = new MpagerAdapter(layouts,this);
            mPager.setAdapter(mpagerAdapter);

    }*/
    }
}


