package com.divide.ibitech.divide_ibitech;

import android.app.AlertDialog;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class SlideOne extends AppCompatActivity {
    // Variable to store  data from previous activity//String userID,userCellNo,userMail,userPass,userCPassword;
    //End
    EditText et_Name,et_Surname,et_DateofBirth,et_Address,et_Suburb,et_City,et_PostalCode;
    String name,surname,dob,gender,address,suburb,city,code;
   // String url="http://sict-iis.nmmu.ac.za/Ibitech/app/Register.php";
    Boolean validFName = false, validSurname = false, validDOB = false;
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

        rg_Gender = findViewById(R.id.rgGender);



        //Real-time validation
        //First name
        et_Name.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(et_Name.getText().length() > 0){
                    validFName = FirstNameValidate();
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
                    validSurname = SurnameValidate();
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
                    validDOB = DOBValidate();
                }
                else {
                    et_DateofBirth.setError(null);
                }
            }
        });

        rg_Gender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                i = rg_Gender.getCheckedRadioButtonId();
                rb_Gender = findViewById(i);

                if(rb_Gender.getText() == "Female"){
                    gender = "Female";
                }
                else {
                    gender = "Male";
                }
            }
        });

        //Should be under next in IntroActivity ???
        if(!rg_Gender.isSelected()){
            rb_Gender.setError("Please select your gender");
        }
        if((validFName)&&(validSurname)&&(validDOB) && rg_Gender.isSelected()){
            saveSlideOneInfo();
        }
        else {
            Toast.makeText(this,"Please enter all necessary details",Toast.LENGTH_LONG).show();
        }


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

    }

    private void saveSlideOneInfo() {
        SharedPreferences preferences = getSharedPreferences("userInfo",MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("pFirstName",et_Name.getText().toString());
        editor.putString("pSurname",et_Surname.getText().toString());
        editor.putString("pDOB",et_DateofBirth.getText().toString());
        editor.putString("pGender", gender);
        editor.putString("pAddress",et_Address.getText().toString());
        editor.putString("pSuburb",et_Suburb.getText().toString());
        editor.putString("pCity",et_City.getText().toString());
        editor.putString("pPostalCode",et_PostalCode.getText().toString());
        editor.apply();
    }

    //Validate Methods
    private Boolean FirstNameValidate() {
        name = et_Name.getText().toString();
        validFName = true;

        if(name.isEmpty() || name.length() < 2){
            et_Name.setError("Please enter your name");
            validFName = false;
        }
        return validFName;
    }
    private Boolean SurnameValidate() {
        surname = et_Surname.getText().toString();
        validSurname = true;

        if(surname.isEmpty() || surname.length() < 2){
            et_Surname.setError("Please enter your surname");
            validSurname = false;
        }
        return validSurname;
    }
    private Boolean DOBValidate() {
        dob = et_DateofBirth.getText().toString();
        validDOB = true;

        if(dob.isEmpty()){
            et_DateofBirth.setError("Please enter your surname");
            validDOB = false;
        }
        return validDOB;
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

        Intent intent = new Intent(this, SlideTwo.class);
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
                    Intent intent = new Intent(SlideOne.this, Login.class);
                    startActivity(intent);
                }
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }*/



}
