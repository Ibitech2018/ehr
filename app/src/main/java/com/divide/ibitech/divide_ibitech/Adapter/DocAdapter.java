package com.divide.ibitech.divide_ibitech.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.divide.ibitech.divide_ibitech.Model.Doctor;
import com.divide.ibitech.divide_ibitech.R;

import java.util.List;

/**
 * Created by s216100801 on 2018/08/27.
 */

public class DocAdapter extends ArrayAdapter<Doctor>{
    private List<Doctor> doctorList;
    private  Context mCtx;

    public DocAdapter( List<Doctor> D, Context c) {
        super(c, R.layout.list_display,D);
        this.doctorList=D;
        this.mCtx=c;
    }


    @Override
    public View getView(int position,  View convertView,  ViewGroup parent) {

        LayoutInflater inflater=LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.list_display,null,true);
        TextView name=(TextView) view.findViewById(R.id.tvName);
        TextView date=(TextView) view.findViewById(R.id.tvVisDate);
        TextView tel=(TextView) view.findViewById(R.id.tvtellNo);
Doctor doctor=doctorList.get(position);
name.setText(doctor.getFullname());
        date.setText(doctor.getDate());
        tel.setText(doctor.getTellNo());

return  view;
    }
}
