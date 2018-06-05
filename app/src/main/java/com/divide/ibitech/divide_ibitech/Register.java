package com.divide.ibitech.divide_ibitech;

import android.content.Intent;
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
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ybs.passwordstrengthmeter.PasswordStrength;

public class Register extends AppCompatActivity implements TextWatcher {

    AlertDialog.Builder builder;
    String idNumber ,newPassword,cPassword,emailAddress,cellphoneNumber;
    EditText et_IDNumber, et_EnterPassword, et_ConfirmPassword, et_EmailAddress, et_CellphoneNum;
    Button btn_Register;
    TextView tv_login,strengthView;
    ProgressBar progressBar;
    Boolean valid = false,checked = false;
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


        //Real-time validation
        //ID Number
        et_IDNumber.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(et_IDNumber.getText().length() > 0){
                    valid = IDNumberValidate();
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
                    valid = CellphoneValidate();
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
                    valid = EmailAddressValidate();
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
                    valid = NewPasswordValidate();
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
                    valid = ConfirmPasswordValidate();
                }
                else {
                    et_ConfirmPassword.setError(null);
                }
            }
        });

    }



    //Validate methods


    private boolean IDNumberValidate() {
        idNumber = et_IDNumber.getText().toString();
        valid = true;

        if(idNumber.isEmpty() || idNumber.length() != 13){
            et_IDNumber.setError("Please enter a valid ID number");
            valid = false;
        }

        return valid;
    }

    private boolean EmailAddressValidate() {
        emailAddress = et_EmailAddress.getText().toString();
        valid = true;

        if(emailAddress.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(emailAddress).matches()){
            et_EmailAddress.setError("Please enter a valid email address");
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

    private boolean NewPasswordValidate() {
        newPassword = et_EnterPassword.getText().toString();
        String passStrength = strengthView.getText().toString();
        valid = true;

        if(newPassword.isEmpty() || (passStrength.equals("Weak"))){
            et_EnterPassword.setError("Password should be at least 8 characters, with at least 1 number and 1 special character.");
            valid = false;
        }
        return valid;
    }

    private boolean ConfirmPasswordValidate() {
        newPassword = et_EnterPassword.getText().toString();
        cPassword = et_ConfirmPassword.getText().toString();
        valid = true;

        if(cPassword.isEmpty() || !(newPassword.equals(cPassword))){
            et_ConfirmPassword.setError("Passwords entered don't match");
            valid = false;
        }
        return valid;
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

    public void OnCheck(View view){
        if(policyCheck.isChecked()){
            checked = true;
        }
        else {
            checked = false;
        }
    }

    public void OnRegister(View view) {

        if(!checked){
            policyCheck.setError("Please confirm you have read the privacy policy and the terms and conditions");
        }
        else if(checked){
            policyCheck.setError(null);
        }
        if(valid && checked){
            Intent intent = new Intent(this, Slide_One.class);
            startActivity(intent);
        }
        else {
            //Make sure you've entered all fields correctly
        }

    }

}
