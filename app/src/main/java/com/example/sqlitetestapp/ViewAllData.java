package com.example.sqlitetestapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Toast;

public class ViewAllData extends AppCompatActivity {

    private DBHandler dbHandler;
    private SQLiteDatabase database;
    private String dbName = "my_test.db";
    private String tableName = "contacts";
//    private Cursor cursor;


    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_data);

        recyclerView = findViewById(R.id.allDataRecyclerView);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        try {
            dbHandler = new DBHandler(this, dbName, null, 3);
            database = dbHandler.getWritableDatabase();
        } catch (Exception e) {
//            txtAllDataView.setText(e.toString());
        }


        adapter = new ViewAllDataAdapter(getAllData());
        recyclerView.setAdapter(adapter);

    }

    private Cursor getAllData() {
        Cursor cursor = null;
        try {
            String query = "select * from " + tableName + ";";
            cursor = database.rawQuery(query, null);
        } catch (Exception e) {
//            showError(e);
        }
        return cursor;
    }

    private void showError(Exception e) {
        Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
    }
}
