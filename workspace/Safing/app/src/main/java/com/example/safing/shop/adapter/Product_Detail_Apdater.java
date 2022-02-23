package com.example.safing.shop.adapter;

import static com.example.safing.async.CommonAsk.FILE_PATH;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.safing.R;
import com.example.safing.async.OnItemClick_Product_Detail_Listener;
import com.example.safing.shop.VO.Product_DetailVO;
import com.example.safing.shop.fragment.Product_Detail_Fragment;

import java.text.NumberFormat;

public class Product_Detail_Apdater extends RecyclerView.Adapter<Product_Detail_Apdater.ViewHolder> implements OnItemClick_Product_Detail_Listener {

    Context context;
    LayoutInflater inflater;
    Product_Detail_Fragment fragment;
    OnItemClick_Product_Detail_Listener listener;
    Product_DetailVO vo = new Product_DetailVO();

    public Product_Detail_Apdater(Context context,  Product_DetailVO vo, Product_Detail_Fragment fragment) {
        this.context = context;
        this.vo = vo;
        this.inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.fragment = fragment;

    }


    public void setOnItemClickListener(OnItemClick_Product_Detail_Listener listener){
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview = inflater.inflate(R.layout.item_product_detail_rec, parent , false );
        return new ViewHolder(itemview, this);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.binding(holder, position);
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    @Override
    public void onItemClick_detail(ViewHolder holderm, View view, int position) {
        if(listener != null){
            listener.onItemClick_detail(holderm, view, position);
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView item_product_detail_img;
        TextView item_product_detail_tv1, item_product_detail_tv2, item_product_detail_tv3;
        ImageButton item_product_detail_btn1, item_product_detail_btn2;
        int cnt = 1;

        public ViewHolder(@NonNull View itemView, OnItemClick_Product_Detail_Listener listener) {
            super(itemView);

            item_product_detail_img = itemView.findViewById(R.id.item_product_detail_img);
            item_product_detail_tv1 = itemView.findViewById(R.id.item_product_detail_tv1);
            item_product_detail_tv2 = itemView.findViewById(R.id.item_product_detail_tv2);
            item_product_detail_tv3 = itemView.findViewById(R.id.item_product_detail_tv3);
            item_product_detail_btn1 = itemView.findViewById(R.id.item_product_detail_btn1);
            item_product_detail_btn2 = itemView.findViewById(R.id.item_product_detail_btn2);

        }
        public void binding(ViewHolder holder, int position){
            Glide.with(context).load(FILE_PATH  + vo.getImagelist().get(0)).into( holder.item_product_detail_img);
            holder.item_product_detail_tv1.setText(vo.getProduct_name());
            holder.item_product_detail_tv2.setText(NumberFormat.getInstance().format(vo.getProduct_price()) +"원");

            vo.setOrder_count(cnt);
            fragment.changePrice(cnt);

            holder.item_product_detail_tv3.setText(cnt+"");

            holder.item_product_detail_img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if(listener != null){
                        listener.onItemClick_detail(ViewHolder.this,
                                v, position);
                    }
                }
            });

            holder.item_product_detail_btn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(cnt == 0){
                        cnt = 0;
                    } else {
                        cnt--;
                    }
                    item_product_detail_tv3.setText(cnt+"");
                }
            });


            holder.item_product_detail_btn2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(cnt == vo.getProduct_stock()){
                        cnt = vo.getProduct_stock();
                    } else {
                        cnt++;
                    }
                    item_product_detail_tv3.setText(cnt+"");
                }
            });

            holder.item_product_detail_tv3.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    try{
                        int cnt = Integer.parseInt(s+"");
                        fragment.changePrice(cnt);
                    }catch (Exception e){

                    }
                }
                @Override
                public void afterTextChanged(Editable s) {

                }
            });

        }
    }
}
