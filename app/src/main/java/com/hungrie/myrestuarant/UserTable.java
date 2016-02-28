package com.hungrie.myrestuarant;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by thanakrit_taw on 2/18/2016.
 */
public class UserTable {

    private MyOpenHelper objMyOpenHelper;
    private SQLiteDatabase readSQlite, writeSQlite;
    public static final String TABLE_USER = "tuser";
    public static final String COLUMN_USER_ID = "_id";
    public static final String COLUMN_USER_USER = "User";
    public static final String COLUMN_USER_PASSWORD = "Password";
    public static final String COLUMN_USER_OFFICER = "Officer";

    public UserTable(Context context) {

        objMyOpenHelper = new MyOpenHelper(context);
        writeSQlite = objMyOpenHelper.getWritableDatabase();
        readSQlite = objMyOpenHelper.getReadableDatabase();

    }// Constructor

    public String[] searchUser(String strUser) {

        try {

            String strData[] = null;
            Cursor objCursor = readSQlite.query(TABLE_USER, new String[]{COLUMN_USER_ID, COLUMN_USER_USER, COLUMN_USER_PASSWORD, COLUMN_USER_OFFICER} , COLUMN_USER_USER +"=?",
                               new String[]{String.valueOf(strUser)},null,null,null,null);

            if (objCursor!= null) {




                if (objCursor.moveToFirst()) {

                    /// Move to First Record and Put Data to Array Set

                    strData = new String[objCursor.getColumnCount()];
                    strData[0] = objCursor.getString(0);
                    strData[1] = objCursor.getString(1);
                    strData[2] = objCursor.getString(2);
                    strData[3] = objCursor.getString(3);




                } // if

            }// if

            objCursor.close();
            return strData;

        } catch (Exception e) {

            return null;
        }


        //return new String[0];


    }// End Search User

    public long addValueToUser(String strUser, String strPassword, String strOfficer) {

        ContentValues objContentValue = new ContentValues();
        objContentValue.put(COLUMN_USER_USER, strUser);
        objContentValue.put(COLUMN_USER_PASSWORD,strPassword);
        objContentValue.put(COLUMN_USER_OFFICER,strOfficer);


        return writeSQlite.insert(TABLE_USER, null, objContentValue);
    }// Add Value method

}// End Main Class
