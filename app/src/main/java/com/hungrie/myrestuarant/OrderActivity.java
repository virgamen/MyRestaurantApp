package com.hungrie.myrestuarant;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class OrderActivity extends AppCompatActivity {

    // Explicit

    private FoodTable objFoodTable;
    private String[] strListFood, strListPrice;
    private TextView txtShowOfficer;
    private EditText strTableNumber;
    private String strTextShowOfficer, strMyDesk, strMyNumber , strSumOrder, strOfficer, strMyFood;
    private String strItems;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        bindWidget();

        objFoodTable = new FoodTable(this);

        //strMyDesk = strTableNumber.getText().toString().trim();







        //Syncronize Json to food database

        syncJsonToFood();

        //Setup Array

        setUpAllArray();


        //Create Listview

        createListView();

        //Setup text Officer

        setUpOfficerShow();




        //Toast.makeText(OrderActivity.this, "Welcome to order activity", Toast.LENGTH_SHORT).show();
    }//OnCreate

    private void bindWidget() {

        // EditText tableNumber = (EditText) findViewById(R.id.table_number);

         strTableNumber = (EditText) findViewById(R.id.deskNum);


    }


    private void setUpOfficerShow() {

        txtShowOfficer = (TextView) findViewById(R.id.txt_show_officer);


        String strTextShowOfficer = getIntent().getExtras().getString("Name");
        txtShowOfficer.setText(strTextShowOfficer);
        strOfficer = strTextShowOfficer;


    }//Setup OfficerShow

    private void createListView() {

        int[] strMyTarget = {R.drawable.food_1, R.drawable.food_2, R.drawable.food_3, R.drawable.food_4, R.drawable.food_5, R.drawable.food_6, R.drawable.food_7, R.drawable.food_8,
                R.drawable.food_9, R.drawable.food_10, R.drawable.food_11};

        MyAdapter objMyAdapter = new MyAdapter(OrderActivity.this, strListFood, strListPrice, strMyTarget);
        ListView objListView = (ListView) findViewById(R.id.food_listview);
        objListView.setAdapter(objMyAdapter);

        ///Active Listveiw

       objListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

               ///Check zero

               strMyDesk = strTableNumber.getText().toString().trim();

               if (strMyDesk.equals("")) {

                   MyAlertDialog objAlertDialog = new MyAlertDialog();
                   objAlertDialog.errorDialog(OrderActivity.this, "Table Valid", "Plase fill table number!");


               } else {

                   strMyFood = strListFood[position];
                   //ckLog();
                   chooseFoodItem();


               }

           }
       });



    }///Create ListView

    private void chooseFoodItem() {

        CharSequence[] charItem = {"1 จาน","2 จาน","3 จาน","4 จาน","5 จาน","6 จาน","7 จาน","8 จาน","9 จาน","10 จาน"};

        AlertDialog.Builder objAlertDialogBuilder = new AlertDialog.Builder(this);
        objAlertDialogBuilder.setTitle("Choose Item");
        objAlertDialogBuilder.setCancelable(false);
        objAlertDialogBuilder.setSingleChoiceItems(charItem, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                //dialog.dismiss();

                switch (which) {

                    case 0:
                        strItems = "1";
                        break;
                    case 1:
                        strItems = "2";
                        break;
                    case 2:
                        strItems = "3";
                        break;
                    case 3:
                        strItems = "4";
                        break;
                    case 4:
                        strItems = "5";
                        break;
                    case 5:
                        strItems = "6";
                        break;
                    case 6:
                        strItems = "7";
                        break;
                    case 7:
                        strItems = "8";
                        break;
                    case 8:
                        strItems = "9";
                        break;
                    case 9:
                        strItems = "10";
                        break;


                }

                dialog.dismiss();
                ckLog();

                // Up Value
                addValueToMysql();


            }
        });
        AlertDialog objAlertDialog = objAlertDialogBuilder.create();
        objAlertDialog.show();

    }///Choose Food Item

    private void addValueToMysql() {

        /// Setup Policy
        if (Build.VERSION.SDK_INT > 9) {

            StrictMode.ThreadPolicy myPolicy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(myPolicy);
        }

        try {

            ArrayList<NameValuePair> objNameValuePair = new ArrayList<NameValuePair>();
            objNameValuePair.add(new BasicNameValuePair("isAdd","true"));
            objNameValuePair.add(new BasicNameValuePair("officer", strOfficer));
            objNameValuePair.add(new BasicNameValuePair("food", strMyFood));
            objNameValuePair.add(new BasicNameValuePair("desk", strMyDesk));
            objNameValuePair.add(new BasicNameValuePair("item", strItems));


            HttpClient objHttpClient = new DefaultHttpClient();
            HttpPost objHttpPost = new HttpPost("http://sumrejgroup.com/app/rest/send_order.php");
            objHttpPost.setEntity(new UrlEncodedFormEntity(objNameValuePair, "UTF-8"));
            objHttpClient.execute(objHttpPost);

            Toast.makeText(OrderActivity.this, "Update " + strMyFood, Toast.LENGTH_SHORT).show();



        } catch (Exception e) {


            ///Log Error

            Log.d("Restaurant", "Error : AddMysql ===>" + e.toString());

        }




    }//Up Mysql

    private void ckLog() {

        Log.d("order", "Officer ==> " + strOfficer);
        Log.d("order","Desk ===>" + strMyDesk);
        Log.d("order", "Food ===>" + strMyFood);
        Log.d("order", "Items ===>" + strItems);
    }

    private void setUpAllArray() {

        strListFood = objFoodTable.ListFood();
        strListPrice = objFoodTable.ListPrice();



    }//setUpAllArray

    private void syncJsonToFood() {

        //Setup Policy
        if (Build.VERSION.SDK_INT > 9) {

            StrictMode.ThreadPolicy myPolicy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(myPolicy);

        }//if

        /// step 01 Inputsream

        InputStream objInputStream = null;
        String strJon = "";

        ///Create inputstream

        try {

            HttpClient objHttpClient = new DefaultHttpClient();
            HttpPost objHttpPost = new HttpPost("http://sumrejgroup.com/app/rest/feed-food.php");
            HttpResponse objResponse = objHttpClient.execute(objHttpPost);
            HttpEntity objEntity = objResponse.getEntity();
            objInputStream = objEntity.getContent();




        } catch (Exception e) {

            Log.d("Restaurant", "Error: from Inputstreaming ==>" + e.toString());

        }

        //Create StrJson

        try {

            BufferedReader objBufferedReader = new BufferedReader(new InputStreamReader(objInputStream, "UTF-8"));
            StringBuilder objStringBuilder = new StringBuilder();
            String strLine = "";

            while ((strLine = objBufferedReader.readLine()) != null) {


                objStringBuilder.append(strLine);



            }// while

            objInputStream.close();

            /// Put to strJson

            strJon = objStringBuilder.toString();



        } catch (Exception e) {


            Log.d("Restaurant", "Error: from Create strJson ==>" + e.toString());

        }

        ///Update Value To SQlite

        try {

            /// Create Json Array

            final JSONArray objJsonArray = new JSONArray(strJon);

            for (int i = 0; i < objJsonArray.length(); i++) {

                JSONObject objJsonObject = objJsonArray.getJSONObject(i);
                String strFoodName = objJsonObject.getString("foodname");
                String strPrice = objJsonObject.getString("price");

                long insertValue = objFoodTable.addValueToFood(strFoodName, strPrice);

                Log.d("Restaurant", "Synchronize to Food SQlite complate!");


            }//For




        } catch (Exception e) {

            Log.d("Restaurant", "Error: from Update Value to SQLite ==>" + e.toString());

        }

    }//syncJson TO Food
}// main class
