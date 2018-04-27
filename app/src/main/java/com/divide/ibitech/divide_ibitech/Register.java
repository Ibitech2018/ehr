package com.divide.ibitech.divide_ibitech;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Register extends AppCompatActivity {

    EditText idNum, enterPassword, confirmPassword, emailAddress, contactNo;
    Button buttonRegister;
    TextView Already_User;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        idNum = findViewById(R.id.ID_Num);
        enterPassword = findViewById(R.id.Password);
        confirmPassword= findViewById(R.id.confirm_password);
        emailAddress= findViewById(R.id.Email);
        contactNo = findViewById(R.id.Contact_Number);
        Already_User=findViewById(R.id.already_user);
        buttonRegister= findViewById(R.id.button_Register);
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startSlideOne();
            }
        });


    }

    private void startSlideOne() {
        Intent intent = new Intent(this, Slide_one.class);
        startActivity(intent);
    }

}
