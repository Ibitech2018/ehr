package com.divide.ibitech.divide_ibitech;
import android.support.v7.app.AppCompatDialogFragment;

/**
 * Created by s216100801 on 2018/06/11.
 */
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;


public class Request_dialog extends AppCompatDialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder= new AlertDialog.Builder(getActivity());
        builder.setTitle("Request Device")

                .setMessage("Device Successfully Requested")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
        return builder.create();

    }
}
