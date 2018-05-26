package com.divide.ibitech.divide_ibitech;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {

        AlertDialog.Builder builder;
        //Declare variables
        String idNumber, cellphoneNumber, userPassword;

        //Define views
        EditText et_IDNumber,et_CellphoneNum,et_Password;
        Button btn_Login;
        TextView tv_NewRegister,tv_ForgotPass;

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

        builder = new AlertDialog.Builder(Login.this);
        btn_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                next();
            }
        });

        //OnClick Listeners
        tv_NewRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(Login.this,Register.class);
                Login.this.startActivity(registerIntent);
            }
        });



    }

    public void next(){
        initialize();
        if(!Validate()){
            Toast.makeText(this,"Cannot proceed, incorrect input",Toast.LENGTH_SHORT).show();
        }
        else {
            nextSuccess();
        }
    }

    public void nextSuccess(){
        /*SharedPreferences preferences = getSharedPreferences("MYP", MODE_PRIVATE);
        idNumber = et_IDNumber.getText().toString();
        cellphoneNumber = et_CellphoneNum.getText().toString();
        userPassword = et_Password.getText().toString();

        String userLog = preferences.getString(idNumber + cellphoneNumber + userPassword + "data", idNumber + ) */

        Intent intent = new Intent(Login.this, Register.class);
        intent.putExtra("USERID", idNumber);
        intent.putExtra("CELLNUMBER",cellphoneNumber);
        intent.putExtra("PASSWORD",userPassword);
        startActivity(intent);

    }

    public boolean Validate(){
        boolean valid = true;
        if(idNumber.isEmpty() || idNumber.length() != 13){
            et_IDNumber.setError("Please enter a valid ID number");
            valid = false;
        }
        if(cellphoneNumber.isEmpty() || cellphoneNumber.length() != 10){
            et_CellphoneNum.setError("Please enter a valid cellphone number");
            valid = false;
        }
        if(userPassword.isEmpty()){
            et_Password.setError("Please enter a password");
            displayAlert("input error");
            valid = false;
        }
        return valid;
    }

    public void initialize(){
        idNumber = et_IDNumber.getText().toString().trim();
        cellphoneNumber = et_CellphoneNum.getText().toString().trim();
        userPassword = et_Password.getText().toString().trim();
    }

    public void displayAlert(final String code){
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(code.equals("input error")){
                    et_Password.setText("");
                }
                else if (code.equals("reg success")){
                    Intent intent = new Intent(Login.this,Register.class);
                }
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public void OnLogin(View view) {
        String idNumber = et_IDNumber.getText().toString();
        String cellphoneNum = et_CellphoneNum.getText().toString();
        String password = et_Password.getText().toString();
        String type = "login";

        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type,idNumber,cellphoneNum,password);
    }

}


