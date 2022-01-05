package com.example.newlastproject.customer;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newlastproject.R;

public class CusAdaper extends RecyclerView.Adapter<CusAdaper.ViewHolder> {

    @NonNull
    @Override
    public CusAdaper.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull CusAdaper.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imgv;
        TextView tv_no, tv_nume, tv_phone;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgv = itemView.findViewById(R.id.cus_imgv);
        }
    }
}
