package com.example.safing.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.safing.DTO.Shop_Product_PagerDTO;
import com.example.safing.R;

import java.util.ArrayList;

public class Shop_Product_Pager_Adapter extends RecyclerView.Adapter<Shop_Product_Pager_Adapter.ViewHolder> {
    boolean bookMark = true;
    Context context;
    ArrayList<Shop_Product_PagerDTO> list;
    LayoutInflater inflater;

    public Shop_Product_Pager_Adapter(Context context) {
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
        return 5;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView product_img1, product_img2;
        TextView product_tv1;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            product_img1 = itemView.findViewById(R.id.product_img1);
            product_img2 = itemView.findViewById(R.id.product_img2);
            product_tv1 = itemView.findViewById(R.id.product_tv1);

            product_img2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(bookMark== true) {
                        Toast.makeText(context, "장바구니", Toast.LENGTH_SHORT).show();
                        product_img2.setImageResource(R.drawable.bookmark2);
                        bookMark= false;
                    }else {
                        Toast.makeText(context, "장바구니 취소", Toast.LENGTH_SHORT).show();
                        product_img2.setImageResource(R.drawable.bookmark1);
                        bookMark= true;
                    }
                }
            });
        }

    }
    public void binding(ViewHolder holder, int position){
        holder.product_tv1.setText((position+1) +"/"+ 5);
    }
}
