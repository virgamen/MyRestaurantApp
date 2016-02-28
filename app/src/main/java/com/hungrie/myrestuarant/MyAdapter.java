package com.hungrie.myrestuarant;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by thanakrit_taw on 2/26/2016.
 */
public class MyAdapter extends BaseAdapter {

    //Eplic

    private Context mContext;
    private  String[] strFoodname , strPrice;
    private int[] myTarget;

    public MyAdapter(Context mContext, String[] strName, String[] strPriceFood, int[] targetId) {

        this.mContext = mContext;
        strFoodname = strName;
        strPrice = strPriceFood;
        myTarget = targetId;




    }//Constructor

    @Override
    public int getCount() {

        return strFoodname.length;
    }


    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater objLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view  = objLayoutInflater.inflate(R.layout.list_view_row, parent, false);

        ///BindWidget

        /// Set View Foodname
        TextView listFoodName = (TextView) view.findViewById(R.id.food_name);
        listFoodName.setText(strFoodname[position]);

        // Set View List Food

        TextView listFoodPrice = (TextView) view.findViewById(R.id.food_price);
        listFoodPrice.setText(strPrice[position]);

        //Set Image

        ImageView listImgView = (ImageView) view.findViewById(R.id.food_picture);
        listImgView.setBackgroundResource(myTarget[position]);


        return view;


    }//Target getView


    ///



}//main class
