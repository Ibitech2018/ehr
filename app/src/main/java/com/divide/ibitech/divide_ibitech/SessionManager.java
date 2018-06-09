package com.divide.ibitech.divide_ibitech;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import java.util.HashMap;

public class SessionManager {
    SharedPreferences sharedPreferences;
    public SharedPreferences.Editor editor;
    public Context context;
    int PRIVATE_MODE = 0;

    private static final String PREF_NAME = "LOGIN";
    private static final String LOGIN = "IS_LOGIN";
    public static final String NAME = "NAME";
    public static final String SURNAME = "EMAIL";
    public static final String AGE = "EMAIL";
    public static final String BLOODTYPE = "EMAIL";
    public static final String GENDER = "EMAIL";
    public static final String STATUS = "EMAIL";
    public static final String ADDRESS = "EMAIL";


    public SessionManager(Context context){
        this.context = context;
        sharedPreferences = context.getSharedPreferences(PREF_NAME,PRIVATE_MODE);
        editor = sharedPreferences.edit();
    }

    public void createSession(String name, String surname,String age, String bloodtype,
                              String gender,String status,String address){
        editor.putBoolean(LOGIN,true);
        editor.putString(NAME,name);
        editor.putString(SURNAME,surname);
        editor.putString(AGE,age);
        editor.putString(BLOODTYPE,bloodtype);
        editor.putString(GENDER,gender);
        editor.putString(STATUS,status);
        editor.putString(ADDRESS,address);
        editor.apply();

    }

    public boolean isLoggin(){
        return sharedPreferences.getBoolean(LOGIN,false);
    }

    public void checkLogin(){
        if(!this.isLoggin()){
            Intent i = new Intent(context,Login.class);
            context.startActivity(i);
            ((Dashboard) context).finish();
        }
    }

    public HashMap<String,String>getUserDetails(){
        HashMap<String,String> user = new HashMap<>();
        user.put(NAME,sharedPreferences.getString(NAME,null));
        //user.put(EMAIL,sharedPreferences.getString(EMAIL,null));
        return user;
    }

    public void logout(){
        editor.clear();
        editor.commit();
        Intent i = new Intent(context,Login.class);
        ((Dashboard) context).finish();
    }
}
