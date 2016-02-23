package com.hungrie.myrestuarant;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by thanakrit_taw on 2/18/2016.
 */
public class UserTable {

    private MyOpenHelper objMyOpenHelper;
    private SQLiteDatabase readSQlite, writeSQlite;

    public UserTable(Context context) {

        objMyOpenHelper = new MyOpenHelper(context);
        writeSQlite = objMyOpenHelper.getWritableDatabase();
        readSQlite = objMyOpenHelper.getReadableDatabase();

    }// Constructor

}// End Main Class
