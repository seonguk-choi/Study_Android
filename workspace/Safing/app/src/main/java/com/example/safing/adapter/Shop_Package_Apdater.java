package com.example.safing.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.safing.DTO.Shop_PackageDTO;
import com.example.safing.R;
import com.example.safing.async.OnItemClick_Package_Listener;

import java.util.ArrayList;

public class Shop_Package_Apdater extends RecyclerView.Adapter<Shop_Package_Apdater.ViewHolder> implements OnItemClick_Package_Listener {

    Context context;
    ArrayList<Shop_PackageDTO> list;
    LayoutInflater inflater;
    OnItemClick_Package_Listener listener;

    public Shop_Package_Apdater(Context context, ArrayList<Shop_PackageDTO> list) {
        this.context = context;
        this.list = list;
        this.inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    public void addDto(Shop_PackageDTO dto){
        list.add(dto);
    }

    // dtos의 특정위치에 dto를 삭제할수 있는 매소드
    public void delDto(int position){
        list.remove(position);
    }


    public void setOnItemClickListener(OnItemClick_Package_Listener listener){
        this.listener = listener;
    }

    public Shop_PackageDTO getItem(int position){
        return list.get(position);
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview = inflater.inflate(R.layout.rec_item_sfzone, parent , false );
        return new ViewHolder(itemview, this);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onItemClick_package(ViewHolder holderm, View view, int position) {
        if(listener != null){
            listener.onItemClick_package(holderm, view, position);
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView sfimgv ;
        TextView sftext;
        LinearLayout rec_item_theme_linearlayout;

        public ViewHolder(@NonNull View itemView, OnItemClick_Package_Listener onItemClick_package_listener) {
            super(itemView);
            sfimgv = itemView.findViewById(R.id.sfimgv);
            sftext = itemView.findViewById(R.id.sftext);
            rec_item_theme_linearlayout = itemView.findViewById(R.id.rec_item_theme_linearlayout);


            rec_item_theme_linearlayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if(listener != null){
                        listener.onItemClick_package(ViewHolder.this,
                                v, position);
                    }
                }
            });





        }
        public void binding(ViewHolder holder, int position){

        }
    }
}
