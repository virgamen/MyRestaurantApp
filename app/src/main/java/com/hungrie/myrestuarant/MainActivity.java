package com.hungrie.myrestuarant;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private OrderTable objOrderTable;
    private UserTable objUserTable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        objOrderTable = new OrderTable(this);
        objUserTable = new UserTable(this);

    }
}
