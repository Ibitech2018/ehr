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
    TextView Login_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        idNum = findViewById(R.id.ID_Num);
        enterPassword = findViewById(R.id.Password);
        confirmPassword= findViewById(R.id.confirm_password);
        emailAddress= findViewById(R.id.Email);
        contactNo = findViewById(R.id.Contact_Number);

        buttonRegister= findViewById(R.id.button_Register);

        Login_user = findViewById(R.id.Login_user);
        Login_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent loginIntent = new Intent(Register.this,Login.class);
                Register.this.startActivity(loginIntent);
            }
        });

    }
}
