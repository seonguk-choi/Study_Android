package com.example.safing.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.safing.DTO.SafeZoneRecDTO;
import com.example.safing.DTO.Shop_PackageDTO;
import com.example.safing.R;
import com.example.safing.activity.ThemePagerActivity;

import java.util.ArrayList;

public class Shop_Package_Apdater extends RecyclerView.Adapter<Shop_Package_Apdater.ViewHolder> {

    Context context;
    ArrayList<Shop_PackageDTO> list;
    LayoutInflater inflater;
    ImageView sfimgv ;
    TextView sftext;

    public Shop_Package_Apdater(Context context, ArrayList<Shop_PackageDTO> list) {
        this.context = context;
        this.list = list;
        this.inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview = inflater.inflate(R.layout.rec_item_sfzone, parent , false );
        return new ViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            sfimgv = itemView.findViewById(R.id.sfimgv);
            sftext = itemView.findViewById(R.id.sftext);

            sfimgv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "패키지상품", Toast.LENGTH_SHORT).show();
                }
            });





        }
        public void binding(ViewHolder holder, int position){

        }
    }
}
