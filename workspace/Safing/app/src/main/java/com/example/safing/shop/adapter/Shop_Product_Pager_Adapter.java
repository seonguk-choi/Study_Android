package com.example.safing.shop.adapter;

import static com.example.safing.async.CommonAsk.FILE_PATH;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.safing.R;

import java.util.ArrayList;

public class Shop_Product_Pager_Adapter extends RecyclerView.Adapter<Shop_Product_Pager_Adapter.ViewHolder> {
    boolean bookMark = true;
    Context context;
    ArrayList<String> list;
    LayoutInflater inflater;

    public Shop_Product_Pager_Adapter(Context context,  ArrayList<String> list) {
        this.context = context;
        this.list = list;
        this.inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview = inflater.inflate(R.layout.item_product_image_pager, parent , false );
        return new ViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            binding(holder, position);
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView product_img1;
        TextView product_tv1;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            product_img1 = itemView.findViewById(R.id.product_img1);
            product_tv1 = itemView.findViewById(R.id.product_tv1);

        }

    }
    public void binding(ViewHolder holder, int position){
        Glide.with(context).load(FILE_PATH + list.get(position)).into( holder.product_img1);
        holder.product_tv1.setText((position+1) +"/"+ list.size());
    }
}
