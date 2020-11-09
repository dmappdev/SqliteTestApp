package com.example.sqlitetestapp;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ShowData extends AppCompatActivity {


//UI

    private TextView txtAllDataView;

    //DB
    private DBHandler dbHandler;
    private SQLiteDatabase database;
    private ArrayList<String> userData = new ArrayList<>();
    private String dbName = "my_test.db";
    private String tableName = "contacts";
    private Cursor cursor;
    String query = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_data);
        Toast.makeText(getApplicationContext(), "Hello", Toast.LENGTH_SHORT).show();

//UI init

        txtAllDataView = findViewById(R.id.txtAllDataView);

//DB
        try {
            dbHandler = new DBHandler(this, dbName, null, 3);
            database = dbHandler.getWritableDatabase();
//           database.execSQL("drop table " + tableName);
//            query = "CREATE TABLE IF NOT EXISTS " + tableName +
//                    "(ID INTEGER PRIMARY KEY AUTOINCREMENT," +
//                    "FNAME TEXT NOT NULL," +
//                    "LNAME TEXT NOT NULL," +
//                    "PHONE INTEGER," +
//                    "EMAIL TEXT);";
//            database.execSQL(query);
        } catch (Exception e) {
            txtAllDataView.setText(e.toString());
        }


        try {
            Cursor cursor = getAllData();
            if (cursor.moveToFirst()) {
                do {
                    userData.add(cursor.getString(0));
                    userData.add(cursor.getString(1));
                    userData.add(cursor.getString(2));
                    userData.add(cursor.getString(3));
                    userData.add(cursor.getString(4));
                } while (cursor.moveToNext());
            }
//                    String tmp = null;
            for (String data : userData) {
                txtAllDataView.append(data + " ");
            }

        } catch (Exception e) {
            showError(e);
        }


    }

    private Cursor getAllData() {
        Cursor cursor = null;
        try {
            String query = "select * from " + tableName + ";";
            cursor = database.rawQuery(query, null);
        } catch (Exception e) {
            showError(e);
        }
        return cursor;
    }

    private void showError(Exception e) {
        txtAllDataView.setText(e.toString());
    }


}