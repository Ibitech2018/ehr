package com.divide.ibitech.divide_ibitech.Model;

/**
 * Created by s216100801 on 2018/08/27.
 */

public class Patient {
    String Fullname;
    String Date;
    String TellNo;
    public Patient(String fullname, String date, String tellNo) {
        Fullname = fullname;
        Date = date;
        TellNo = tellNo;
    }

    public String getFullname() {
        return Fullname;
    }

    public String getDate() {
        return Date;
    }

    public String getTellNo() {
        return TellNo;
    }
}
