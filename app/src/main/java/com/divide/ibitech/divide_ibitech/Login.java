package com.divide.ibitech.divide_ibitech;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {

        AlertDialog.Builder builder;
        //Declare variables
        String idNumber, cellphoneNumber, userPassword;
        Boolean valid;

        //Define views
        EditText et_IDNumber,et_CellphoneNum,et_Password;
        Button btn_Login;
        TextView tv_NewRegister,tv_ForgotPass,tv_PasswordToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Getting id by from xml
        et_IDNumber = findViewById(R.id.etIDNumber);
        et_CellphoneNum =  findViewById(R.id.etCellphoneNum);
        et_Password =  findViewById(R.id.etPassword);
        btn_Login = findViewById(R.id.btnLogin);
        tv_NewRegister = findViewById(R.id.tvNewRegister);
        tv_ForgotPass = findViewById(R.id.tvForgotPass);

        //Real-time validation
        //ID Number
        et_IDNumber.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View view, boolean b) {
                if(et_IDNumber.getText().length() > 0){
                    IDNumberValidate();
                }
                else{
                    et_IDNumber.setError(null);
                }


            }
        });

        //Cellphone Number
        et_CellphoneNum.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(et_CellphoneNum.getText().length() > 0){
                    CellphoneValidate();
                }
                else {
                    et_CellphoneNum.setError(null);
                }
            }
        });


        //Show password toggle
        tv_PasswordToggle = findViewById(R.id.tvTogglePassword);
        tv_PasswordToggle.setVisibility(View.GONE);
        et_Password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

        et_Password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(et_Password.getText().length() > 0){
                    tv_PasswordToggle.setVisibility(View.VISIBLE);
                }
                else{
                    tv_PasswordToggle.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        tv_PasswordToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(tv_PasswordToggle.getText() == "SHOW"){
                    tv_PasswordToggle.setText("HIDE");
                    et_Password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    et_Password.setSelection(et_Password.length());
                }
                else {
                    tv_PasswordToggle.setText("SHOW");
                    et_Password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    et_Password.setSelection(et_Password.length());

                }
            }
        });

        tv_NewRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(Login.this,Register.class);
                Login.this.startActivity(registerIntent);
            }
        });



    }

    private boolean IDNumberValidate() {
        idNumber = et_IDNumber.getText().toString();
        valid = true;

        if(idNumber.isEmpty() || idNumber.length() != 13){
            et_IDNumber.setError("Please enter a valid ID number");
            valid = false;
        }

        return valid;
    }
    private boolean CellphoneValidate() {
        cellphoneNumber = et_CellphoneNum.getText().toString();
        boolean valid = true;

        if(cellphoneNumber.isEmpty() || cellphoneNumber.length() != 10){
            et_CellphoneNum.setError("Please enter a valid cellphone number");
            valid = false;
        }

        return valid;
    }



    public void OnLogin(View view) {

        if(valid) {

            String idNumber = et_IDNumber.getText().toString();
            String cellphoneNum = et_CellphoneNum.getText().toString();
            String password = et_Password.getText().toString();
            String type = "login";

            BackgroundWorker backgroundWorker = new BackgroundWorker(this);
            backgroundWorker.execute(type, idNumber, cellphoneNum, password);
        }
        //else

    }

}


