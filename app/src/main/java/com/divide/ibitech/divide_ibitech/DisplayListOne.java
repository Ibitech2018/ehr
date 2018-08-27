package com.divide.ibitech.divide_ibitech;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.divide.ibitech.divide_ibitech.Adapter.DocAdapter;
import com.divide.ibitech.divide_ibitech.Model.Doctor;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DisplayListOne extends AppCompatActivity {
ListView listView;
List<Doctor> doctorList;
String listOne_url="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_list_one);
        listView=(ListView)findViewById(R.id.list_data);
        doctorList=new ArrayList<>();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent fast =new Intent(DisplayListOne.this,ViewOne.class);

                startActivity(fast);
                showlist();
            }
        });



    }

    public  void showlist(){

        StringRequest stringRequest = new StringRequest(Request.Method.GET, listOne_url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject object= new JSONObject(response);
                            JSONArray array = object.getJSONArray("server_response");
                            for(int i =0;i<array.length();i++) {
                                JSONObject docObj = array.getJSONObject(i);
                                Doctor d = new Doctor(docObj.getString(""),docObj.getString(""),docObj.getString(""));
                                doctorList.add(d);

                            }
                            DocAdapter adapter = new DocAdapter(doctorList,getApplication());
                            listView.setAdapter(adapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){


        };

Singleton.getInstance(getApplicationContext()).addToRequestQue(stringRequest);
    }
}
