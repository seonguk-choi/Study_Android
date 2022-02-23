package com.example.safing.shop.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.safing.R;

import java.util.ArrayList;

public class Review_Image_Rec_Adapter extends RecyclerView.Adapter<Review_Image_Rec_Adapter.ViewHolder> {
    Context context;
  //  ArrayList<Shop_Product_PagerDTO> list;
    LayoutInflater inflater;

    public Review_Image_Rec_Adapter(Context context) {
        this.context = context;
   //     this.list = list;
        this.inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview = inflater.inflate(R.layout.item_review_image_pager, parent , false );
        return new ViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        binding(holder, position);
    }


    @Override
    public int getItemCount() {
        return 5;
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
        holder.product_tv1.setText((position+1) +"/"+ 5);
    }
}
