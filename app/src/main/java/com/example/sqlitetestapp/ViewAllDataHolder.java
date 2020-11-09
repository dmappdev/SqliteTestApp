package com.example.sqlitetestapp;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ViewAllDataHolder extends RecyclerView.ViewHolder {

    public TextView txtFname, txtLname, txtPhone, txtEmail;

    public ViewAllDataHolder(@NonNull View itemView) {
        super(itemView);
        this.txtFname = itemView.findViewById(R.id.txtFname);
        this.txtLname = itemView.findViewById(R.id.txtLname);
        this.txtPhone = itemView.findViewById(R.id.txtPhone);
        this.txtEmail = itemView.findViewById(R.id.txtEmail);
    }
}
