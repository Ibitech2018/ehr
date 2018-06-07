package com.divide.ibitech.divide_ibitech;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.ybs.passwordstrengthmeter.PasswordStrength;

public class  Register extends AppCompatActivity implements TextWatcher {

    AlertDialog.Builder builder;
    String idNumber ,newPassword,cPassword,emailAddress,cellphoneNumber;
    EditText et_IDNumber, et_EnterPassword, et_ConfirmPassword, et_EmailAddress, et_CellphoneNum;
    Button btn_Register;
    TextView tv_login,strengthView;
    String passStrength;
    ProgressBar progressBar;
    Boolean validID = false,validCell = false,validEmail = false,validNewPass = false,validCpass = false,checked = false;
    CheckBox policyCheck;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //Get input values from xml
        et_IDNumber = findViewById(R.id.etIDNumber);

        et_EnterPassword = findViewById(R.id.etCreatePassword);
        et_EnterPassword.addTextChangedListener(this);

        et_ConfirmPassword= findViewById(R.id.etConfirmPassword);
        et_EmailAddress= findViewById(R.id.etEmailAddress);
        et_CellphoneNum = findViewById(R.id.etCellphoneNumber);
        tv_login = findViewById(R.id.login);
        btn_Register= findViewById(R.id.btnRegister);

        policyCheck = findViewById(R.id.chkPolicy);


        tv_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent loginIntent = new Intent(Register.this, Login.class);
                startActivity(loginIntent);
            }
        });

        btn_Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                        if(!checked){
                    policyCheck.setError("Please confirm you have read the privacy policy and the terms and conditions");
                }
                if((validID) && (validCell) && (validEmail) && (validCpass) && (checked)) {  //validNewPass is not included

                    saveRegisterInfo();
                    startActivity(new Intent(Register.this, IntroActivity.class));
                    finish();
                }
                else {
                    Toast.makeText(getApplicationContext(), "Please ensure all fields are filled!",Toast.LENGTH_LONG).show();
                }
            }
        });

        policyCheck = findViewById(R.id.chkPolicy);

        policyCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checked = false;
                if(policyCheck.isChecked()){
                    checked = true;
                    policyCheck.setError(null);
                }
            }
        });

        //Real-time validation
        //ID Number
        et_IDNumber.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(et_IDNumber.getText().length() > 0){
                    validID = IDNumberValidate();
                }
                else {
                    et_IDNumber.setError(null);
                }

            }
        });


        //Cellphone number
        et_CellphoneNum.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(et_CellphoneNum.getText().length() > 0){
                    validCell = CellphoneValidate();
                }
                else {
                    et_CellphoneNum.setError(null);
                }
            }
        });

        //Email address
        et_EmailAddress.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(et_EmailAddress.getText().length() > 0){
                    validEmail = EmailAddressValidate();
                }
                else {
                    et_EmailAddress.setError(null);
                }
            }
        });

        //New password
        et_EnterPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(et_ConfirmPassword.getText().length() > 0){
                    validNewPass = NewPasswordValidate();
                }
                else {
                    et_EnterPassword.setError(null);
                }
            }
        });

        //Confirm password
        et_ConfirmPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(et_ConfirmPassword.getText().length() > 0){
                    validCpass = ConfirmPasswordValidate();
                }
                else {
                    et_ConfirmPassword.setError(null);
                }
            }
        });

    }

    private void saveRegisterInfo() {
        SharedPreferences preferences = getSharedPreferences("userInfo",MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("pIDNumber",et_IDNumber.getText().toString());
        editor.putString("pCellphoneNum",et_CellphoneNum.getText().toString());
        editor.putString("pEmailAddress",et_EmailAddress.getText().toString());
        editor.putString("pPassword",et_EnterPassword.getText().toString());
        editor.apply();

        Toast.makeText(this,"Saved",Toast.LENGTH_LONG).show(); // To be removed !!!

    }

    SlideOne s1;
    SlideTwo s2;

    //Constructor
    public  Register(SlideOne slideOne,SlideTwo slideTwo){
        s1 = slideOne;
        s2 = slideTwo;
    }

    public Register(){

    }


    //Validate methods

    private boolean IDNumberValidate() {
        idNumber = et_IDNumber.getText().toString();
        validID = false;

        if(idNumber.isEmpty() || idNumber.length() != 13){
            et_IDNumber.setError("Please enter a valid ID number");
        }
        else {
            validID = true;
        }

        return validID;
    }

    private boolean EmailAddressValidate() {
        emailAddress = et_EmailAddress.getText().toString();
        validEmail = false;

        if(emailAddress.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(emailAddress).matches()){
            et_EmailAddress.setError("Please enter a valid email address");
        }
        else {
            validEmail = true;
        }
        return validEmail;
    }

    private boolean CellphoneValidate() {
        cellphoneNumber = et_CellphoneNum.getText().toString();
        validCell = false;

        if(cellphoneNumber.isEmpty() || cellphoneNumber.length() != 10){
            et_CellphoneNum.setError("Please enter a valid cellphone number");
        }
        else {
            validCell = true;
        }

        return validCell;
    }

    private boolean NewPasswordValidate() {
        newPassword = et_EnterPassword.getText().toString();
        passStrength = strengthView.getText().toString();
        validNewPass = false;

        if(newPassword.isEmpty() || (passStrength.equals("Weak"))){
            et_EnterPassword.setError("Password should be at least 8 characters, with at least 1 number and 1 special character.");
        }
        else {
            validNewPass = true;
        }
        return validNewPass;
    }

    private boolean ConfirmPasswordValidate() {
        newPassword = et_EnterPassword.getText().toString();
        cPassword = et_ConfirmPassword.getText().toString();
        validCpass = false;

        if(cPassword.isEmpty() || !(newPassword.equals(cPassword))){
            et_ConfirmPassword.setError("Passwords entered don't match");
        }
        else {
            validCpass = true;
        }
        return validCpass;
    }

    //PASSWORD STRENGTH CHECKER
    @Override
    public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
        updatePasswordStrengthView(charSequence.toString());
    }

    private void updatePasswordStrengthView(String password) {

        progressBar = findViewById(R.id.progressBar);
        strengthView = findViewById(R.id.password_strength);
        if(TextView.VISIBLE != strengthView.getVisibility()){
            return;
        }
        if(password.isEmpty()){
            strengthView.setText("");
            progressBar.setProgress(0);
            return;
        }

        PasswordStrength strength = PasswordStrength.calculateStrength(password);
        strengthView.setText(strength.getText(this));
        strengthView.setTextColor(strength.getColor());

        progressBar.getProgressDrawable().setColorFilter(strength.getColor(), PorterDuff.Mode.SRC_IN);
        if(strength.getText(this).equals("Weak")){
            progressBar.setProgress(25);
        }
        else if(strength.getText(this).equals("Medium")){
                progressBar.setProgress(50);
        }
        else if(strength.getText(this).equals("Strong")){
            progressBar.setProgress(75);
        }
        else {
            progressBar.setProgress(100);
        }
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }

   /* public void OnRegister() {

        String idNumber = et_IDNumber.getText().toString();
        String cellphoneNum = et_CellphoneNum.getText().toString();
        String emailAddress = et_EmailAddress.getText().toString();
        String password = et_EnterPassword.getText().toString();
        String firstName = s1.name;
        String surname = s1.surname;
        String dob = s1.dob;
        String gender = s1.gender;
        String address = s1.address;
        String suburb = s1.suburb;
        String city = s1.city;
        String postal = s1.code;
        Float weight = s2.weight;
        Float height = s2.height;
        String type = "register";

        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type, idNumber, cellphoneNum,emailAddress, password,firstName,surname,dob,gender,address,suburb,city,postal,weight.toString(),height.toString());

    }*/

}
