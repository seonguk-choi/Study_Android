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

import com.example.safing.DTO.Product_InfoDTO;
import com.example.safing.DTO.SafeZoneRecDTO;
import com.example.safing.R;
import com.example.safing.async.OnItemClick_Package_Listener;
import com.example.safing.async.OnItemClick_product_Listener;

import java.util.ArrayList;

public class Shop_Rec_Adapter extends RecyclerView.Adapter<Shop_Rec_Adapter.ViewHolder> implements OnItemClick_product_Listener {
    boolean bookMark = true;
    Context context;
    ArrayList<Product_InfoDTO> list;
    LayoutInflater inflater;

    OnItemClick_product_Listener listener;

    public Shop_Rec_Adapter(Context context) {
        this.context = context;
        this.list = list;
        this.inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    public void addDto(Product_InfoDTO dto){
        list.add(dto);
    }

    public void delDto(int position){
        list.remove(position);
    }

    public void setOnItemClickListener(OnItemClick_product_Listener listener){
        this.listener = listener;
    }

    public Product_InfoDTO getItem(int position){
        return list.get(position);
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview = inflater.inflate(R.layout.item_product_info, parent , false );
        return new ViewHolder(itemview, this);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 5;
    }

    @Override
    public void onItemClick_product(ViewHolder holderm, View view, int position) {
        if(listener != null){
            listener.onItemClick_product(holderm, view, position);
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView product_img1, product_img2;
        TextView product_tv1, product_tv2, product_tv3, product_tv4, product_tv5, product_tv6, product_tv7, product_tv8, product_tv9;

        public ViewHolder(@NonNull View itemView, OnItemClick_product_Listener listener) {
            super(itemView);
            product_img1 = itemView.findViewById(R.id.product_img1);
            product_img2 = itemView.findViewById(R.id.product_img2);
            product_tv1 = itemView.findViewById(R.id.product_tv1);
            product_tv2 = itemView.findViewById(R.id.product_tv2);
            product_tv3 = itemView.findViewById(R.id.product_tv3);
            product_tv4 = itemView.findViewById(R.id.product_tv4);
            product_tv5 = itemView.findViewById(R.id.product_tv5);
            product_tv6 = itemView.findViewById(R.id.product_tv6);
            product_tv7 = itemView.findViewById(R.id.product_tv7);
            product_tv8 = itemView.findViewById(R.id.product_tv8);
            product_tv9 = itemView.findViewById(R.id.product_tv9);

            product_img1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if(listener != null){
                        listener.onItemClick_product(ViewHolder.this,
                                v, position);
                    }
                }
            });

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
        public void binding(ViewHolder holder, int position){

        }
    }
}
