package com.example.sqlitetestapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    //UI
    private Button btnEnterData, btnShowData;
    private EditText editFName, editLName, editPhone, editEmail;
    private TextView txtDataView;
    //DATA
    private DBHandler dbHandler;
    private SQLiteDatabase database;
    private ArrayList<String> userData = new ArrayList<>();
    private String dbName = "my_test.db";
    private String tableName = "contacts";
    private String[] dbData;
    private Cursor cursor;
    String query = "";

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//UI init
        editFName = findViewById(R.id.edit_f_name);
        editLName = findViewById(R.id.edit_l_name);
        editPhone = findViewById(R.id.edit_phone);
        editEmail = findViewById(R.id.edit_email);
        btnEnterData = findViewById(R.id.btn_enter_data);
        btnShowData = findViewById(R.id.btn_show_data);
        txtDataView = findViewById(R.id.txt_data_view);


//DB
        try {
            dbHandler = new DBHandler(this, dbName, null, 3);
            database = dbHandler.getWritableDatabase();
//           database.execSQL("drop table " + tableName);
            query = "CREATE TABLE IF NOT EXISTS " + tableName +
                    "(ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "FNAME TEXT NOT NULL," +
                    "LNAME TEXT NOT NULL);";
            database.execSQL(query);
//           query = "INSERT INTO NAMES (ID, NAME) VALUES (null, 'ghi');";
//           database.execSQL(query);

        } catch (Exception e) {
            txtDataView.setText(e.toString());
        }


//Listeners
        btnEnterData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                userData.add(editFName.getText().toString());
//                userData.add(editLName.getText().toString());
//                userData.add(editPhone.getText().toString());
//                userData.add(editEmail.getText().toString());
//                createDB();
//                insertQuery(buildQuery(userData));
//                txtDataView.setText(buildQuery(userData));

                try {
                    insertQuery(editFName.getText().toString(), editLName.getText().toString());
                } catch (Exception e) {
                    showError(e);
                }
            }
        });

        btnShowData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Cursor cursor = getAllData();
                    if (cursor.moveToFirst()) {
                        do {
                            userData.add(cursor.getString(0));
                            userData.add(cursor.getString(1));
                            userData.add(cursor.getString(2));
                        } while (cursor.moveToNext());
                    }
//                    String tmp = null;
                    for (String data : userData) {
                        txtDataView.append(data + " ");
                    }

                } catch (Exception e) {
                    showError(e);
                }
            }
        });
    }

    private void showError(Exception e) {
        txtDataView.setText(e.toString());
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

    private void insertQuery(String fName, String lName) {
        query = "INSERT INTO " + tableName + "(ID, FNAME, LNAME) VALUES (null, '" + fName + "', '" + lName + "');";
        Toast.makeText(this, query, Toast.LENGTH_LONG).show();
        database.execSQL(query);

    }

//    private void createDB() {
//        cursor = database.rawQuery("CREATE TABLE IF NOT EXISTS " + tableName + " (" +
//                "contact_id INTEGER PRIMARY KEY AUTOINCREMENT," +
//                "first_name TEXT NOT NULL," +
//                "last_name TEXT NOT NULL," +
//                "email TEXT NOT NULL UNIQUE," +
//                "phone TEXT NOT NULL UNIQUE" +
//                ");", null);
//        cursor.close();
//    }

//    private String buildQuery(ArrayList<String> userData) {
//        String query = "";
////        String query = "(contact_id,first_name,last_name,email,phone)";
//        for (int i = 0; i < userData.size(); i++) {
//            query += userData.get(i);
//            if (i < userData.size() - 1) query += ",";
//        }
//        return query;
//    }

}