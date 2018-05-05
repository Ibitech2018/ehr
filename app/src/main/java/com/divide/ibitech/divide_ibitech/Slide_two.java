package com.divide.ibitech.divide_ibitech;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class Slide_two extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide_two);

        Spinner maritalSpinner = findViewById(R.id.marital_status);
        ArrayAdapter<CharSequence> maritalAdapter = ArrayAdapter.createFromResource(this,R.array.marital_status,R.layout.support_simple_spinner_dropdown_item);
        maritalAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        maritalSpinner.setAdapter(maritalAdapter);

        Spinner bloodTypeSpinner = findViewById(R.id.list_blood);
        ArrayAdapter<CharSequence> bloodTypeAdapter = ArrayAdapter.createFromResource(this,R.array.list_blood,R.layout.support_simple_spinner_dropdown_item);
        bloodTypeAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        bloodTypeSpinner.setAdapter(bloodTypeAdapter);



    }
}
