package com.hungrie.myrestuarant;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by thanakrit_taw on 2/18/2016.
 */
public class OrderTable {

    private MyOpenHelper objMyOpenHelper;
    private SQLiteDatabase readSQlite, writeSQlite;
    public static final String TABLE_ORDER = "torder";
    public static final String COLUMN_ORDER_ID = "_id";
    public static final String COLUMN_OFFICER = "Officer";
    public static final String COLUMN_DATE = "Date";
    public static final String COLUMN_FOOD = "Food";
    public static final String COLUMN_ITEM = "Item";

    public OrderTable(Context context) {

        objMyOpenHelper = new MyOpenHelper(context);
        readSQlite = objMyOpenHelper.getReadableDatabase();
        writeSQlite = objMyOpenHelper.getWritableDatabase();



    }// Constructor

    public long addValueToOrder(String strOfficer, String strDate, String strFood, int intItem) {

        ContentValues objContentValue = new ContentValues();
        objContentValue.put(COLUMN_OFFICER,strOfficer);
        objContentValue.put(COLUMN_DATE,strDate);
        objContentValue.put(COLUMN_FOOD,strFood);
        objContentValue.put(COLUMN_ITEM,intItem);


        return writeSQlite.insert(TABLE_ORDER, null, objContentValue);
    } /// Add Value to Order
}//End Main Class
