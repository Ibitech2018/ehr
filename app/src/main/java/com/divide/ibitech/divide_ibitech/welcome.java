package com.divide.ibitech.divide_ibitech;

import android.os.Build;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewParent;
import android.view.WindowManager;

public class welcome extends AppCompatActivity {
     private ViewPager mPager;
     private    int [] layouts={R.layout.activity_slide_one,R.layout.activity_slide_two};

     private  MpagerAdapter mpagerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
if (Build.VERSION.SDK_INT >= 19)

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

    }
}


