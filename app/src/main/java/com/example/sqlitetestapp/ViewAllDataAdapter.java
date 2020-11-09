package com.example.sqlitetestapp;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ViewAllDataAdapter extends RecyclerView.Adapter<ViewAllDataHolder> {

    private Context mContext;
    private String[] testData = {"den", "mal", "123456", "ytyt@hjh.com", "bill", "kraz", "542376", "gg@k.com"};
    public ArrayList<ClientData> clientDataList;

    public ViewAllDataAdapter(ArrayList<ClientData> clientDataList, Context context) {
        this.clientDataList = clientDataList;
        this.mContext = context;
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
//        holder.txtFname.setText(position + "");
        holder.txtFname.setText(clientDataList.get(position).getfName());
        holder.txtLname.setText(clientDataList.get(position).getlName());
        holder.txtPhone.setText(Integer.toString(clientDataList.get(position).getPhone()));
        holder.txtEmail.setText(clientDataList.get(position).getEmail());
    }
    @Override
    public int getItemCount() {
//        return testData.length;
        return clientDataList.size();
    }

}
//        holder.txtFname.setText(cursor.getString(position));
//        holder.txtLname.setText(cursor.getString(position+1));
//        holder.txtPhone.setText(cursor.getString(position+2));
//        holder.txtEmail.setText(cursor.getString(position+3));
//    protected void fillClientData() {
//        clientDataList = new ArrayList<ClientData>();
//        try {
//            if (cursor.moveToFirst()) {
//                do {
//                    ClientData clientData = new ClientData();
//                    clientData.setfName(cursor.getString(0));
//                    clientData.setlName(cursor.getString(1));
//                    clientData.setPhone(cursor.getInt(2));
//                    clientData.setEmail(cursor.getString(3));
//                    clientDataList.add(clientData);
//
//                } while (cursor.moveToNext());
//            }
////            for (String data : mClientData) {
////                txtDataView.append(data + " ");
////            }
//
//        } catch (Exception e) {
////            showError(e);
//        }
//
//    }
