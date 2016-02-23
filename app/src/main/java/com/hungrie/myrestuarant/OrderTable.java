package com.hungrie.myrestuarant;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by thanakrit_taw on 2/18/2016.
 */
public class OrderTable {

    private MyOpenHelper objMyOpenHelper;
    private SQLiteDatabase readSQlite, writeSQlite;

    public OrderTable(Context context) {

        objMyOpenHelper = new MyOpenHelper(context);
        readSQlite = objMyOpenHelper.getReadableDatabase();
        writeSQlite = objMyOpenHelper.getWritableDatabase();



    }// Constructor
}//End Main Class
