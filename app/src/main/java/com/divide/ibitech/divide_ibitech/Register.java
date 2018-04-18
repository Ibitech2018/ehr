package com.divide.ibitech.divide_ibitech;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Register extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        final EditText ID_Num= (EditText) findViewById(R.id.ID_Num);
        final EditText Password =(EditText)findViewById(R.id.Password);
        final EditText confirm_password= (EditText) findViewById(R.id.confirm_password);
        final EditText Email= (EditText) findViewById(R.id.Email);
        final EditText Contact_Number= (EditText) findViewById(R.id.Contact_Number);

        final Button button_Register= (Button) findViewById(R.id.button_Register);

        final TextView Login_user = (TextView) findViewById(R.id.Login_user);

        Login_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent loginIntent = new Intent(Register.this,user_login.class);
                Register.this.startActivity(loginIntent);
            }
        });

    }
}
