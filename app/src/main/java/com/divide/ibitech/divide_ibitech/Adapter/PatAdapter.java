package com.divide.ibitech.divide_ibitech.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.divide.ibitech.divide_ibitech.Model.Doctor;
import com.divide.ibitech.divide_ibitech.Model.Patient;
import com.divide.ibitech.divide_ibitech.R;

import java.util.List;

/**
 * Created by s216100801 on 2018/08/27.
 */

public class PatAdapter extends ArrayAdapter<Patient> {

    private List<Patient> patientList;
    private Context mCtx;

    public PatAdapter( List<Patient> P, Context c) {
       super(c,R.layout.display_list_two,P);
        this.patientList=P;
        this.mCtx=c;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater=LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.display_list_two,null,true);
        TextView name=(TextView) view.findViewById(R.id.tvName);
        TextView date=(TextView) view.findViewById(R.id.tvVisDate);
        TextView tel=(TextView) view.findViewById(R.id.tvtellNo);
        Patient patient=patientList.get(position);
        name.setText(patient.getFullname());
        date.setText(patient.getDate());
        tel.setText(patient.getTellNo());

        return  view;
    }
}
