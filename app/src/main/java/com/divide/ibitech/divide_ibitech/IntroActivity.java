package com.divide.ibitech.divide_ibitech;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v4.view.PagerAdapter;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class IntroActivity extends Activity {

    private ViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;
    private LinearLayout dotsLayout;
    private TextView[] dots;
    private int[] layouts;
    private Button btnSkip, btnNext;
    private SlideOne slideOne = new SlideOne();
    private SlideTwo slideTwo = new SlideTwo();

    String URL_REGIST = "http://sict-iis.nmmu.ac.za/ibitech/app/register2.php";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_intro);

        SharedPreferences sharedPreferences = getSharedPreferences("PREFS",MODE_PRIVATE);
        if(sharedPreferences.getInt("INTRO",0) == 1){
//            startActivity(new Intent(IntroActivity.this,Dashboard.class));
//            finish();
        }

        viewPager =  findViewById(R.id.view_pager);
        dotsLayout =  findViewById(R.id.layoutDots);
        btnSkip =  findViewById(R.id.btn_skip);
        btnNext =  findViewById(R.id.btn_next);

        layouts = new int[]{
                R.layout.activity_slide_one,
                R.layout.activity_slide_two};

        // adding bottom dots
        addBottomDots(0);

        viewPagerAdapter = new ViewPagerAdapter();
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.addOnPageChangeListener(viewPagerPageChangeListener);


    }

    public  void btnSkipClick(View v)
    {
        launchHomeScreen();
    }

    public  void btnNextClick(View v)
    {
        // checking for last page
        // if last page home screen will be launched
        int current = getItem(1);
        if (current < layouts.length) {
            // move to next screen
            viewPager.setCurrentItem(current);
        } else {
            //METHOD TO INSERT USER REGISTRATION INTO DATABASE
            launchHomeScreen();
        }
    }

    private void retrievePreferences() {


        SharedPreferences userInfo = getSharedPreferences("userInfo",MODE_PRIVATE);

        String userID = userInfo.getString("pIDNumber","");
        String userCell = userInfo.getString("pCellphoneNum","");
        String userEmail = userInfo.getString("pEmailAddress","");
        String userPassword = userInfo.getString("pPassword","");

        String userFName = userInfo.getString("pFirstName","");
        String userSurname = userInfo.getString("pSurname","");
        String userDOB = userInfo.getString("pDOB","");
        String userGender = userInfo.getString("pGender","");
        String userAddress = userInfo.getString("pAddress","");
        String userSuburb = userInfo.getString("pSuburb","");
        String userCity = userInfo.getString("pCity","");
        String userPostalCode = userInfo.getString("pPostalCode","");

        String userMaritalStatus = userInfo.getString("pMaritalStatus","");
        String userBloodType = userInfo.getString("pBloodType","");
        String userWeight = userInfo.getString("pWeight","");
        String userHeight = userInfo.getString("pHeight","");

        userRegister(userID,userCell,userEmail,userPassword,
                userFName,userSurname,userDOB,userGender,userAddress,
                userMaritalStatus,userBloodType,userWeight,userHeight);


    }

    ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageSelected(int position) {
            addBottomDots(position);

            // changing the button text based on page active
            if (position == layouts.length - 1) {
                btnNext.setText(getString(R.string.start));
                btnSkip.setVisibility(View.GONE);

            }
            else {
                // still pages are left
                btnNext.setText(getString(R.string.next));
                btnSkip.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }
    };

    private void addBottomDots(int currentPage) {
        dots = new TextView[layouts.length];

        dotsLayout.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(getResources().getColor(R.color.dot_inactive));
            dotsLayout.addView(dots[i]);
        }

        if (dots.length > 0)
            dots[currentPage].setTextColor(getResources().getColor(R.color.dot_active));
    }

    private int getItem(int i) {
        return viewPager.getCurrentItem() + i;
    }

    private void launchHomeScreen() {

        SharedPreferences sharedPreferences = getSharedPreferences("PREFS",MODE_PRIVATE);
        SharedPreferences.Editor editor;

        editor = sharedPreferences.edit();
        editor.putInt("INTRO",1);
        editor.apply();

        retrievePreferences();

        startActivity(new Intent(this, Dashboard.class));
        finish();
    }

    private void userRegister(final String userID, final String userCell, final String userEmail, final String userPassword, final String userFName, final String userSurname, final String userDOB, final String userGender, final String userAddress, final String userMaritalStatus, final String userBloodType, final String userWeight, final String userHeight) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_REGIST, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String success = jsonObject.getString("success");

                    if (success.equals("1")) {
                        Toast.makeText(IntroActivity.this, "Register Success", Toast.LENGTH_LONG).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(IntroActivity.this, "Register Error" + e.toString(), Toast.LENGTH_LONG).show();

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(IntroActivity.this,"Register Error"+error.toString(),Toast.LENGTH_LONG).show();

            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("id",userID);
                params.put("cell",userCell);
                params.put("email",userEmail);
                params.put("pass",userPassword);

                params.put("fname",userFName);
                params.put("surname",userSurname);
                params.put("dob",userDOB);
                params.put("gender",userGender);
                params.put("address",userAddress);
                params.put("status",userMaritalStatus);
                params.put("bloodtype",userBloodType);
                params.put("weight",userWeight);
                params.put("height",userHeight);

                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }


    public class ViewPagerAdapter extends PagerAdapter {
        private LayoutInflater layoutInflater;


        public ViewPagerAdapter() {

        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View view = layoutInflater.inflate(layouts[position], container, false);
            container.addView(view);

            return view;
        }

        @Override
        public int getCount() {
            return layouts.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }


        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View view = (View) object;
            container.removeView(view);
        }
    }
}