package com.hungrie.myrestuarant;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

/**
 * Created by thanakrit_taw on 2/25/2016.
 */
public class MyAlertDialog {

    AlertDialog.Builder objAlertDialog;


    public  void errorDialog(Context context, String strTitle, String strMessage) {

        objAlertDialog = new AlertDialog.Builder(context);
        objAlertDialog.setIcon(R.drawable.icon_warnning);
        objAlertDialog.setTitle(strTitle);
        objAlertDialog.setMessage(strMessage);
        objAlertDialog.setCancelable(false);
        objAlertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        objAlertDialog.show();


    }

}//End Main Class
