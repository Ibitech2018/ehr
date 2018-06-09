package com.divide.ibitech.divide_ibitech;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class SlideTwo extends AppCompatActivity {

    EditText et_Weight,et_Height;
    Spinner sp_MaritalStatus, sp_BloodType;
    String maritalStatus, bloodType;
    Boolean validWeight =false,validHeight = false;
    Float weight, height;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide_two);

        sp_MaritalStatus = findViewById(R.id.marital_status);
        ArrayAdapter<CharSequence> maritalAdapter = ArrayAdapter.createFromResource(this,R.array.marital_status,R.layout.support_simple_spinner_dropdown_item);
        maritalAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        sp_MaritalStatus.setAdapter(maritalAdapter);

        sp_MaritalStatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                maritalStatus = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        sp_BloodType = findViewById(R.id.list_blood);
        ArrayAdapter<CharSequence> bloodTypeAdapter = ArrayAdapter.createFromResource(this,R.array.list_blood,R.layout.support_simple_spinner_dropdown_item);
        bloodTypeAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        sp_BloodType.setAdapter(bloodTypeAdapter);

        sp_BloodType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                bloodType = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        et_Weight = findViewById(R.id.weight);
        et_Height = findViewById(R.id.height);

        et_Weight.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(et_Weight.getText().length() > 0){
                    validWeight = ValidateWeight();
                }
            }
        });

        et_Height.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(et_Height.getText().length() > 0){
                    validHeight = ValidateHeight();
                }
            }
        });

        //Should be under next in IntroActivity ???
        if(!sp_MaritalStatus.isSelected()){

        }
        if((validWeight)&&(validHeight) && (sp_MaritalStatus.isSelected()) && (sp_BloodType.isSelected())){
            saveSlideTwoInfo();
        }
        else {
            Toast.makeText(this,"Please enter all necessary details",Toast.LENGTH_LONG).show();
        }

    }

    public void saveSlideTwoInfo() {
        SharedPreferences preferences = getSharedPreferences("userInfo",MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("pMaritalStatus",maritalStatus);
        editor.putString("pBloodType",bloodType);
        editor.putString("pWeight",et_Weight.getText().toString());
        editor.putString("pHeight", et_Height.getText().toString());
        editor.apply();
    }

    private Boolean ValidateHeight() {
        height = Float.parseFloat(et_Height.getText().toString());
        validHeight = true;

        if(height.isNaN()){
            et_Height.setError("Please enter a valid height (in cm's)");
            validHeight = false;
        }
        return validHeight;
    }

    private Boolean ValidateWeight() {
        weight = Float.parseFloat(et_Weight.getText().toString());
        validWeight = true;

        if(weight.isNaN()){
            et_Weight.setError("Please enter a valid weight (in kg's)");
            validWeight = false;
        }

        return validWeight;
    }

}
