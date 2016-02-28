package com.hungrie.myrestuarant;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by thanakrit_taw on 2/26/2016.
 */
public class FoodTable {

    private MyOpenHelper objMyOpenHelper;
    private SQLiteDatabase writeSQlite, readSQLite;
    public static final String FOOD_TABLE = "tfood";
    public static final String COLUMN_FOOD_ID = "_id";
    public static final String COMLUMN_FOOD_NAME = "Foodname";
    public static final String COMLUMN_FOOD_PRICE = "Price";

    public FoodTable(Context context) {

        objMyOpenHelper = new MyOpenHelper(context);
        writeSQlite = objMyOpenHelper.getWritableDatabase();
        readSQLite = objMyOpenHelper.getReadableDatabase();


    }

    public String[] ListFood() {

        String strListFood[] = null;
        Cursor objCursor = readSQLite.query(FOOD_TABLE, new String[]{COLUMN_FOOD_ID, COMLUMN_FOOD_NAME}, null, null, null, null, null);
        objCursor.moveToFirst();

        strListFood = new String[objCursor.getCount()];

        for( int i=0; i < objCursor.getCount();i++ ) {

            strListFood[i] = objCursor.getString(objCursor.getColumnIndex(COMLUMN_FOOD_NAME));
            objCursor.moveToNext();


        }// For

            objCursor.close();

        return strListFood;

    }//ListFood


    public  String[] ListPrice() {

        String strListPrice[] = null;
        Cursor objCursor = readSQLite.query(FOOD_TABLE, new String[]{COLUMN_FOOD_ID, COMLUMN_FOOD_PRICE}, null, null, null, null, null);
        objCursor.moveToFirst();

        strListPrice = new String[objCursor.getCount()];

        for(int i=0; i < objCursor.getCount(); i++) {

            strListPrice[i] = "ราคา " + objCursor.getString(objCursor.getColumnIndex(COMLUMN_FOOD_PRICE)) + " บาท";
            objCursor.moveToNext();

        }

          objCursor.close();
          return strListPrice;

    }//List Price



    public long addValueToFood(String strFoodName, String strPrice) {

        ContentValues objContentValue = new ContentValues();
        objContentValue.put(COMLUMN_FOOD_NAME, strFoodName);
        objContentValue.put(COMLUMN_FOOD_PRICE, strPrice);

        return writeSQlite.insert(FOOD_TABLE, null, objContentValue);
    }

}//Main Class
