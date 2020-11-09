package com.example.sqlitetestapp;

import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ViewAllDataAdapter extends RecyclerView.Adapter<ViewAllDataHolder> {

    private Cursor cursor;
    private String[] testData = {"den", "mal", "123456", "ytyt@hjh.com","bill", "kraz", "542376", "gg@k.com"};

    public ViewAllDataAdapter(Cursor cursor) {
        this.cursor = cursor;
    }

    @NonNull
    @Override
    public ViewAllDataHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.all_data_viewholder, parent, false);

        return new ViewAllDataHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewAllDataHolder holder, int position) {
        holder.txtFname.setText(testData[position]);
        holder.txtLname.setText(testData[position]);
        holder.txtPhone.setText(testData[position]);
        holder.txtEmail.setText(testData[position]);
//        holder.txtFname.setText(cursor.getString(position));
//        holder.txtLname.setText(cursor.getString(position+1));
//        holder.txtPhone.setText(cursor.getString(position+2));
//        holder.txtEmail.setText(cursor.getString(position+3));
    }

    @Override
    public int getItemCount() {
        return testData.length;
//        return cursor.getCount();
    }
}
