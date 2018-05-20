package com.divide.ibitech.divide_ibitech;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Register extends AppCompatActivity {
    AlertDialog.Builder builder;
    String IDnum ,newPassword,cPassword,emailP,cellNo;
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

                next();


            }
        });
        builder= new AlertDialog.Builder(Register.this);


    }

    public void next() {
        initialize();
        if (!Validate()) {
            Toast.makeText(this, "Cannot Proceed Data Missing Or Invalid Data Input", Toast.LENGTH_SHORT).show();
        } else {
            nextSuccess();
        }
    }

    public  void  nextSuccess(){
        SharedPreferences preferences = getSharedPreferences("MYP",MODE_PRIVATE);
        IDnum=idNum.getText().toString();
        newPassword=enterPassword.getText().toString();
        String userReg=preferences.getString(IDnum+cellNo+"data",IDnum+""+cellNo);
        SharedPreferences.Editor editor=preferences.edit();
        editor.putString(IDnum + "data", IDnum);
        editor.putString(cellNo + "data", cellNo);
        editor.putString("display", userReg);
        editor.commit();

        Intent intent = new Intent(this, Slide_one.class);
        intent.putExtra("IDNumber", IDnum);
        intent.putExtra("Password", newPassword);
        intent.putExtra("Email_Address", emailP);
        intent.putExtra("CellNumber", cellNo);
        intent.putExtra("ConfirmPassword", cPassword);
        startActivity(intent);


    }
    //Validate user input
    public boolean Validate() {
        boolean valid = true;
        if (IDnum.isEmpty() || IDnum.length() > 13) {
            idNum.setError("Please Enter Valid ID Number");
            valid = false;
        }

        if (cellNo.isEmpty() || cellNo.length() > 10) {
            contactNo.setError("Please Enter Valid CellNumber");
            valid = false;
        }


        if (emailP.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(emailP).matches()) {
            emailAddress.setError("Please Enter Valid Email Address");
            valid = false;
        }

        if (newPassword.isEmpty()) {
            enterPassword.setError("Please Enter A Password");
            valid = false;
        }

        if (!(newPassword.equals(cPassword))) {
            builder.setTitle("Something Went Wrong...");
            builder.setMessage("Your passwords are not matching");
            displayAlert("input_error");
            valid = false;
        }

        if (cPassword.isEmpty()) {
            confirmPassword.setError("Please Enter A Confirm Password");
            valid = false;
        }
        return valid;
    }

    public void initialize() {
        //*********Passing data to new variables************
        IDnum = idNum.getText().toString().trim();
        cellNo = contactNo.getText().toString().trim();
        emailP = emailAddress.getText().toString().trim();
        newPassword = enterPassword.getText().toString().trim();
        cPassword = confirmPassword.getText().toString().trim();
    }

    public void displayAlert(final String code) {
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                if (code.equals("input_error")) {
                    enterPassword.setText("");
                    confirmPassword.setText("");
                } else if (code.equals("reg_success")) {
                    Intent intent = new Intent(Register.this, Login.class);
                    startActivity(intent);
                }
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();


}
}
