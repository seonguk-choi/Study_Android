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
import com.example.safing.async.OnItemClick_Product_Listener;
import com.example.safing.shop.VO.ProductVO;

import java.text.NumberFormat;
import java.util.ArrayList;

public class Shop_Rec_Adapter extends RecyclerView.Adapter<Shop_Rec_Adapter.ViewHolder> implements OnItemClick_Product_Listener {
    boolean bookMark = true;
    Context context;
    ArrayList<ProductVO> list;
    LayoutInflater inflater;
    OnItemClick_Product_Listener listener;

    public Shop_Rec_Adapter(Context context, ArrayList<ProductVO> list) {
        this.context = context;
        this.list = list;
        this.inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    public void addDto(ProductVO dto){
        list.add(dto);
    }

    public void delDto(int position){
        list.remove(position);
    }

    public void setOnItemClickListener(OnItemClick_Product_Listener listener){
        this.listener = listener;
    }

    public ProductVO getItem(int position){
        return list.get(position);
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview = inflater.inflate(R.layout.item_product_rec, parent , false );
        return new ViewHolder(itemview, this);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        binding(holder, position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onItemClick_product(ViewHolder holderm, View view, int position) {
        if(listener != null){
            listener.onItemClick_product(holderm, view, position);
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView product_rec_img1;
        TextView product_rec_tv1, product_rec_tv2, product_rec_tv3, product_rec_tv4, product_rec_tv5, product_rec_tv6, product_rec_tv7, product_rec_tv8;

        public ViewHolder(@NonNull View itemView, OnItemClick_Product_Listener listener) {
            super(itemView);
            product_rec_img1 = itemView.findViewById(R.id.product_rec_img1);
            product_rec_tv1 = itemView.findViewById(R.id.product_rec_tv1);
            product_rec_tv2 = itemView.findViewById(R.id.product_rec_tv2);
            product_rec_tv3 = itemView.findViewById(R.id.product_rec_tv3);
            product_rec_tv4 = itemView.findViewById(R.id.product_rec_tv4);
            product_rec_tv5 = itemView.findViewById(R.id.product_rec_tv5);
            product_rec_tv6 = itemView.findViewById(R.id.product_rec_tv6);
            product_rec_tv7 = itemView.findViewById(R.id.product_rec_tv7);
            product_rec_tv8 = itemView.findViewById(R.id.product_rec_tv8);




            product_rec_img1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if(listener != null){
                        listener.onItemClick_product(ViewHolder.this,
                                v, position);
                    }
                }
            });
        }
    }

    public void binding(ViewHolder holder, int position){
        if(list.get(position).getProduct_stock() < 1){
            delDto(position);
        }else {
            Glide.with(context).load(FILE_PATH + list.get(position).getFile_path()).into( holder.product_rec_img1);
            holder.product_rec_tv1.setText(list.get(position).getProduct_name());
            holder.product_rec_tv2.setText(list.get(position).getRating()+"");
            holder.product_rec_tv3.setText("("+list.get(position).getRe_count()+")");
            holder.product_rec_tv4.setText(NumberFormat.getInstance().format(list.get(position).getProduct_price())+"원");

            String[] tag = list.get(position).getTag_key().split("#");
            holder.product_rec_tv5.setText("#" + tag[1]);
            holder.product_rec_tv6.setText("#" + tag[2]);
            holder.product_rec_tv7.setText("#" + tag[3]);
            holder.product_rec_tv8.setText("#" + tag[4]);
        }

    }
}
