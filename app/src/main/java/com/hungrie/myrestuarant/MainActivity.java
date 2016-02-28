package com.hungrie.myrestuarant;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    private OrderTable objOrderTable;
    private UserTable objUserTable;
    private FoodTable objFoodTable;
    private EditText editUser;
    private EditText editPassword;
    private String strUserChoose, strPasswordChoose;
    private String strPasswordTrue, strShowUser;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /// Hidden Status Bar and Action Bar

        if (Build.VERSION.SDK_INT > 16) {

            // Hide the status bar
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
            // Hide the action bar
            getSupportActionBar().hide();

        } else {

            // Hide the status bar
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
            // Hide the action bar

            getActionBar().hide();

        }



        objOrderTable = new OrderTable(this);
        objUserTable = new UserTable(this);
        objFoodTable = new FoodTable(this);


        //// Test Add Value

        //testAddValue();

        deleteAllData();

        //Sync

        syncJsonToSQlite();

        bindWidget();



    }

    private void bindWidget() {

        editUser = (EditText) findViewById(R.id.username);
        editPassword = (EditText) findViewById(R.id.password);
        btnLogin = (Button) findViewById(R.id.button);
    }



    public void clickLogin(View view) {

        strUserChoose = editUser.getText().toString().trim();
        strPasswordChoose = editPassword.getText().toString().trim();

        if (strUserChoose.equals("") || strPasswordChoose.equals("") ) {

            MyAlertDialog objAlertDialog = new MyAlertDialog();
            objAlertDialog.errorDialog(this ,"Not Allow Blank", "Please fill every field!");



        } else {

            checkUser();



        }// If


    }// Click Login

    private void checkUser() {

        try {

            /// Call Method Searchuser in UserTable Class

            String strData[] = objUserTable.searchUser(strUserChoose);
            strShowUser = strData[3];
            strPasswordTrue = strData[2];

            Log.d("Restaurant", "Welcome user ==>" + strShowUser);

            checkPassword();




        } catch (Exception e) {

            MyAlertDialog objMyDialog = new MyAlertDialog();
            objMyDialog.errorDialog(this, "Not Found","Not found "+ strUserChoose +" in database!");


        }


    }// CheckUser

    private void checkPassword() {

        if (strPasswordChoose.equals(strPasswordTrue)) {

            /// Intent to

            welcomeUser();

        } else {

            MyAlertDialog objMyDialog = new MyAlertDialog();
            objMyDialog.errorDialog(this, "Wrong Password", "Please Check Password again!");

        }
    }

    private void welcomeUser() {

        AlertDialog.Builder objAlertDialog = new AlertDialog.Builder(this);
        objAlertDialog.setIcon(R.drawable.icon_smile);
        objAlertDialog.setTitle("My Restaurant");
        objAlertDialog.setMessage("Hi, " + strShowUser + " welcome to work!.");
        objAlertDialog.setCancelable(false);
        objAlertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // dialog.dismiss();

                /// Intent to Order Activity

                Intent objIntent = new Intent(MainActivity.this, OrderActivity.class);
                objIntent.putExtra("Name", strShowUser);
                startActivity(objIntent);
                finish();

            }
        });

        objAlertDialog.show();


    }


    private void deleteAllData() {

        SQLiteDatabase objSQLiteDatabase = openOrCreateDatabase("Restaurant.db", MODE_PRIVATE, null);
        objSQLiteDatabase.delete("tuser", null, null);
        objSQLiteDatabase.delete("torder", null, null);
        objSQLiteDatabase.delete("tfood", null, null);

    }

    private void syncJsonToSQlite() {

        // Setup Policy

        if (Build.VERSION.SDK_INT > 9) {

            StrictMode.ThreadPolicy myPolicy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(myPolicy);

        }  // Check Policy

            InputStream objInputStream = null;


            // var strJson

            String strJson = "";

            /// Create Inputstream

            try {

                HttpClient objHttpClient = new DefaultHttpClient();
                HttpPost objHttpPost = new HttpPost("http://sumrejgroup.com/app/rest/feed-users.php");
                HttpResponse objResponse = objHttpClient.execute(objHttpPost);

                HttpEntity objHttpEntity = objResponse.getEntity();
                objInputStream = objHttpEntity.getContent();



            } catch (Exception e) {

                ///Commment Inputstream

                Log.d("Restaurant", "Error From InputStream ==>" + e.toString());
            }// Try



            /// InputStream to String

            try {

                BufferedReader objBufferedReader = new BufferedReader(new InputStreamReader(objInputStream, "UTF-8"));
                StringBuilder objStringBuilder = new StringBuilder();
                String strLine = null;
                //strLine = objBufferedReader.readLine();

                while ((strLine = objBufferedReader.readLine()) != null) {

                    // append string

                    objStringBuilder.append(strLine);


                }// while

                objInputStream.close();
                strJson = objStringBuilder.toString();



            } catch (Exception e) {

                Log.d("Restaurant", "Error From Inputstream To String ==>" + e.toString());

            } // Try

        /// Up Value to SQlite

        try {

            final JSONArray objJSONArray = new JSONArray(strJson);

            for (int i = 0 ;i < objJSONArray.length();i++) {

                JSONObject objJSONObject = objJSONArray.getJSONObject(i);
                String strUser = objJSONObject.getString("username");
                String strPassword = objJSONObject.getString("password");
                String strOfficer = objJSONObject.getString("officer");

                long insertValue = objUserTable.addValueToUser(strUser, strPassword, strOfficer);

            }//for



        } catch (Exception e) {

            Log.d("Restaurant", "Error from Up Value SQlite ==> " + e.toString());

        }// Try






    }// Sync Json

    private void testAddValue() {

        objUserTable.addValueToUser("User", "Password", "Officer");
        objOrderTable.addValueToOrder("Officer", "Date", "Food", 1);
        objFoodTable.addValueToFood("Foodname" ,"Price");


    }// Test Value


}



