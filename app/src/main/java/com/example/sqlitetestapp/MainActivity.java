package com.example.sqlitetestapp;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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
//Listeners
        btnEnterData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userData.add(editFName.getText().toString());
                userData.add(editLName.getText().toString());
                userData.add(editPhone.getText().toString());
                userData.add(editEmail.getText().toString());
                createDB();
                insertQuery(buildQuery(userData));
//                txtDataView.setText(buildQuery(userData));
            }
        });

        btnShowData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                txtDataView.setText(Arrays.toString(getAllData()));
            }
        });
    }

    private String[] getAllData() {
        String query = "SELECT * FROM " + tableName + ";";
        database.rawQuery(query, dbData);
        return dbData;
    }

    private void createDB() {
        dbHandler = new DBHandler(this, dbName, null, 1);
        database = dbHandler.getWritableDatabase();
        database.execSQL("CREATE TABLE IF NOT EXISTS " + tableName + " (" +
                "contact_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "first_name TEXT NOT NULL," +
                "last_name TEXT NOT NULL," +
                "email TEXT NOT NULL UNIQUE," +
                "phone TEXT NOT NULL UNIQUE" +
                ");");
    }

    private String buildQuery(ArrayList<String> userData) {
        String query = "";
        for (int i = 0; i < userData.size(); i++) {
            query += userData.get(i);
            if (i < userData.size() - 1) query += ",";
        }
        return query;
    }

    private void insertQuery(String query) {
        query = "INSERT INTO " + tableName + " VALUES (null," + query + ");";
        txtDataView.setText(query);
//        database.execSQL(query);
    }
}