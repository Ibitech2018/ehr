package com.divide.ibitech.divide_ibitech;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class AddCondition extends AppCompatActivity {

    AutoCompleteTextView autoCompleteTextView;
    String[] conditionNames;
    String condition;

    Button btn_add,btn_cancel;

    String URL_CONDITION = "http://sict-iis.nmmu.ac.za/ibitech/app/addcondition.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_condition);

        autoCompleteTextView = findViewById(R.id.condition);
        conditionNames = getResources().getStringArray(R.array.conditions);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,conditionNames);
        autoCompleteTextView.setAdapter(adapter);

        btn_add = findViewById(R.id.btnAdd);
        btn_cancel = findViewById(R.id.btnCancel);

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddCondition.this,Dashboard.class));
            }
        });

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                condition = autoCompleteTextView.getText().toString();
                addCondition(condition);
            }
        });
    }

    public void addCondition(final String condition){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_CONDITION, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONObject jsonObject = new JSONObject(response);
                    String success = jsonObject.getString("success");

                    if (success.equals("1")) {
                        Toast.makeText(AddCondition.this, "Condition added successfully", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(AddCondition.this,Dashboard.class));
                        finish();
                    }
                    else {
                        Toast.makeText(AddCondition.this, "Condition cannot be added at the moment.", Toast.LENGTH_LONG).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(AddCondition.this, "Error : There was an internal error in adding the condition", Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(AddCondition.this,"Error : There was an internal error in adding the condition",Toast.LENGTH_LONG).show();

            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();

                params.put("cond",condition);

                return params;
            }
        };

        Singleton.getInstance(AddCondition.this).addToRequestQue(stringRequest);
    }

}
