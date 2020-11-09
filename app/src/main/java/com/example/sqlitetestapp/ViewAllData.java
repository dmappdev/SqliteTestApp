package com.example.sqlitetestapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class ViewAllData extends AppCompatActivity {
    //DB
    private DBHandler dbHandler;
    private SQLiteDatabase database;
    private String dbName = "my_test.db";
    private String tableName = "contacts";
    public ArrayList<ClientData> clientDataList;
//    private Cursor cursor;


    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_data);
        showMsg("onCreate");
        recyclerView = findViewById(R.id.allDataRecyclerView);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        try {
            dbHandler = new DBHandler(this, dbName, null, 3);
            database = dbHandler.getWritableDatabase();
        } catch (Exception e) {
            showError(e);
        }
        adapter = new ViewAllDataAdapter(fillClientData(getAllData()), this);
        recyclerView.setAdapter(adapter);
    }

    public Cursor getAllData() {
        Cursor cursor = null;
        try {
            String query = "select * from " + tableName + ";";
            cursor = database.rawQuery(query, null);
        } catch (Exception e) {
            showError(e);
        }
        return cursor;
    }

    public ArrayList<ClientData> fillClientData(Cursor cursor) {
        clientDataList = new ArrayList<>();
        try {
            if (cursor.moveToFirst()) {
                do {
                    ClientData clientData = new ClientData();
                    clientData.setfName(cursor.getString(1));
                    clientData.setlName(cursor.getString(2));
                    clientData.setPhone(cursor.getInt(3));
                    clientData.setEmail(cursor.getString(4));
                    clientDataList.add(clientData);

                } while (cursor.moveToNext());
            }
//            for (String data : mClientData) {
//                txtDataView.append(data + " ");
//            }

        } catch (Exception e) {
            showError(e);
        }
        return clientDataList;
    }

    public void showMsg(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    public void showError(Exception e) {
        Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
    }
}
