package com.hungrie.myrestuarant;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by thanakrit_taw on 2/18/2016.
 */
public class MyOpenHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Restaurant.db";
    private static final int DATABASE_VERSION = 1;
    private static final String USER_TABLE = "create table tuser (_id integer primary key, User text, Password text, Officer text);";
    private static final String ORDER_TABLE = "create table torder (_id integer primary key, Officer text, Date text, Food text, Item integer);";

    public MyOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }// Constructor

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(USER_TABLE);
        db.execSQL(ORDER_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}// end main class
