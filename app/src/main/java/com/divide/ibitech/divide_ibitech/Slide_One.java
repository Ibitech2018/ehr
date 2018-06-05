package com.divide.ibitech.divide_ibitech;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Slide_One extends AppCompatActivity {
    // Variable to store  data from previous activity//String userID,userCellNo,userMail,userPass,userCPassword;
    //End
    Button btn_NextSlide;
    EditText et_Name,et_Surname,et_DateofBirth,et_Address,et_Suburb,et_City,et_PostalCode;
    String name,surname,dob,gender,address,suburb,city,code;
    AlertDialog.Builder builder;
   // String url="http://sict-iis.nmmu.ac.za/Ibitech/app/Register.php";
    ImageView top;
    Boolean valid;
    RadioGroup rg_Gender;
    RadioButton rb_Gender;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide_one);


        et_Name = findViewById(R.id.Fname);
        et_Surname = findViewById(R.id.Lname);
        et_DateofBirth = findViewById(R.id.Dob);
        rg_Gender = findViewById(R.id.rgGender);
        et_Address = findViewById(R.id.address1);
        et_Suburb = findViewById(R.id.suburb);
        et_City = findViewById(R.id.City);
        et_PostalCode = findViewById(R.id.postalCode);

        btn_NextSlide = findViewById(R.id.btnNext);

        btn_NextSlide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent slideTwoIntent = new Intent(Slide_One.this, Slide_two.class);
                startActivity(slideTwoIntent);

            }
        });

        //Real-time validation
        //First name
        et_Name.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(et_Name.getText().length() > 0){
                    valid = FirstNameValidate();
                }
                else {
                    et_Name.setError(null);
                }
            }
        });

        //Surname
        et_Surname.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(et_Surname.getText().length() > 0){
                    valid = SurnameValidate();
                }
                else {
                    et_Surname.setError(null);
                }
            }
        });

        //DOB
        et_DateofBirth.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(et_DateofBirth.getText().length() > 0){
                    valid = DOBValidate();
                }
                else {
                    et_DateofBirth.setError(null);
                }
            }
        });

        //retrive  dara from 1st form
      //  userID = (getIntent().getStringExtra("IDNumber"));
       // userCellNo = (getIntent().getStringExtra("CellNumber"));
      //  userMail = (getIntent().getStringExtra("Email_Address"));
       // userPass = (getIntent().getStringExtra("Password"));
       // userCPassword = (getIntent().getStringExtra("ConfirmPassword"));
        //End


     //   SharedPreferences preferences= getSharedPreferences("MYP",MODE_PRIVATE);
     //   String displayLog= preferences.getString("display","");




//        nextSlide.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                next();
//            }
//        });


        builder = new AlertDialog.Builder(Slide_One.this);
    }

    //Validate Methods
    private Boolean FirstNameValidate() {
        name = et_Name.getText().toString();
        valid = true;

        if(name.isEmpty() || name.length() < 2){
            et_Name.setError("Please enter your name");
            valid = false;
        }
        return valid;
    }
    private Boolean SurnameValidate() {
        surname = et_Surname.getText().toString();
        valid = true;

        if(surname.isEmpty() || surname.length() < 2){
            et_Surname.setError("Please enter your surname");
            valid = false;
        }
        return valid;
    }
    private Boolean DOBValidate() {
        dob = et_DateofBirth.getText().toString();
        valid = true;

        if(dob.isEmpty()){
            et_DateofBirth.setError("Please enter your surname");
            valid = false;
        }
        return valid;
    }

    //Radio button
    public void OnRbClick(View view) {
        int radioID = rg_Gender.getCheckedRadioButtonId();
        rb_Gender = findViewById(radioID);

        if(rb_Gender.getText() == "Female"){
            gender = "Female";
        }
        else {
            gender = "Male";
        }

    }



/*    public void next() {
        initialize();
        if (!Validate()) {
            Toast.makeText(this, "Cannot Proceed Data Missing Or Invalid Data Input", Toast.LENGTH_SHORT).show();
        } else {
            nextSuccess();
        }
    }
    public  void  nextSuccess(){
        SharedPreferences preferences = getSharedPreferences("MYP",MODE_PRIVATE);
        name=et_Name.getText().toString();
        surname=et_Surname.getText().toString();
        String userReg=preferences.getString(name+surname+"data",name+""+surname);
        SharedPreferences.Editor editor=preferences.edit();
        editor.putString(name + "data", name);
        editor.putString(surname + "data", surname);
        editor.putString("display", userReg);
        editor.commit();

        Intent intent = new Intent(this, Slide_two.class);
        intent.putExtra("Firstname", name);
        intent.putExtra("Surname", surname);
        intent.putExtra("DateOfBirth", date);
        intent.putExtra("Address", address);
        intent.putExtra("Suburb", suburb);
        intent.putExtra("City", city);
        intent.putExtra("PostalCode", code);
        startActivity(intent);


    }

    //Validate user input
    public boolean Validate() {
        boolean valid = true;
        if (name.isEmpty() || name.length() > 32) {
            et_Name.setError("Please Enter Valid Name");
            valid = false;
        }

        if (surname.isEmpty() || surname.length() > 32) {
            et_Surname.setError("Please Enter Valid Surname");
            valid = false;
        }
        if (city.isEmpty() || city.length() > 10) {
            et_City.setError("Please Enter Valid City");
            valid = false;
        }

        if (date.isEmpty() || date.length() > 32) {
            et_DateofBirth.setError("Please Enter Valid Date");
            valid = false;
        }
        if (suburb.isEmpty() || suburb.length() > 32) {
            et_Suburb.setError("Please Enter Valid Suburb");
            valid = false;
        }


        if (city.isEmpty() || city.length() > 10) {
            et_City.setError("Please Enter Valid City");
            valid = false;
        }

        if (code.isEmpty() || code.length() > 10) {
            et_PostalCode.setError("Please Enter Valid Postal Code");
            valid = false;
        }



        return valid;
    }
    public void initialize() {
        //*********Passing data to new variables************
        name = et_Name.getText().toString().trim();
        surname = et_Surname.getText().toString().trim();
        address = et_Address.getText().toString().trim();
        suburb = et_Suburb.getText().toString().trim();
        city = et_City.getText().toString().trim();
        code = et_PostalCode.getText().toString().trim();



    }

    public void displayAlert(final String code) {
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                if (code.equals("input_error")) {
                    et_Name.setText("");
                    et_Surname.setText("");
                    et_Address.setText("");
                    et_Suburb.setText("");
                    et_City.setText("");
                    et_PostalCode.setText("");

                } else if (code.equals("reg_success")) {
                    Intent intent = new Intent(Slide_One.this, Login.class);
                    startActivity(intent);
                }
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }*/



}
