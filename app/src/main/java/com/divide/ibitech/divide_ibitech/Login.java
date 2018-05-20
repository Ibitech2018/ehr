package com.divide.ibitech.divide_ibitech;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Login extends AppCompatActivity {

        //Define views
        EditText ID_Num,Contact_Number,Password;
        Button button_Login;
        TextView new_userRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //Getting id by from xml
        final EditText ID_num= (EditText)findViewById(R.id.ID_Num);
        final EditText Contact_Number= (EditText) findViewById(R.id.Contact_Number);
        final EditText Password= (EditText) findViewById(R.id.Password);
        final Button button_Login= (Button) findViewById(R.id.button_Login);

        final TextView new_userRegister= (TextView) findViewById(R.id.new_userRegister);

        new_userRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerInttent = new Intent(Login.this,Register.class);
                Login.this.startActivity(registerInttent);
            }
        });

        button_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerInttent = new Intent(Login.this,dashboard.class);
                Login.this.startActivity(registerInttent);
            }
        });

    }

}
