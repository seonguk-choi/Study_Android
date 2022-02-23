package com.example.safing.shop.adapter;

import static com.example.safing.async.CommonAsk.FILE_PATH;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.safing.R;
import com.example.safing.async.OnItemClick_Package_Listener;
import com.example.safing.shop.VO.Product_PackageVO;

import java.util.ArrayList;

public class Shop_Package_Apdater extends RecyclerView.Adapter<Shop_Package_Apdater.ViewHolder> implements OnItemClick_Package_Listener {

    Context context;
    ArrayList<Product_PackageVO> list;
    LayoutInflater inflater;
    OnItemClick_Package_Listener listener;


    public Shop_Package_Apdater(Context context, ArrayList<Product_PackageVO> list) {
        this.context = context;
        this.list = list;
        this.inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    public void addDto(Product_PackageVO dto){
        list.add(dto);
    }

    public void delDto(int position){
        list.remove(position);
    }

    public void setOnItemClickListener(OnItemClick_Package_Listener listener){
        this.listener = listener;
    }

    public Product_PackageVO getItem(int position){
        return list.get(position);
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview = inflater.inflate(R.layout.item_product_package_shop_rec, parent , false );
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
    public void onItemClick_package(ViewHolder holderm, View view, int position) {
        if(listener != null){
            listener.onItemClick_package(holderm, view, position);
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView item_product_package_shop_img ;
        TextView item_product_package_shop_tv;
        LinearLayout item_product_package_shop_linear;

        public ViewHolder(@NonNull View itemView, OnItemClick_Package_Listener onItemClick_package_listener) {
            super(itemView);
            item_product_package_shop_img = itemView.findViewById(R.id.item_product_package_shop_img);
            item_product_package_shop_tv = itemView.findViewById(R.id.item_product_package_shop_tv);
            item_product_package_shop_linear = itemView.findViewById(R.id.item_product_package_shop_linear);

            item_product_package_shop_linear.setOnClickListener(new View.OnClickListener() {
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
    }
    public void binding(ViewHolder holder, int position){
        holder.item_product_package_shop_img.setColorFilter(Color.parseColor("#CD959595"), PorterDuff.Mode.MULTIPLY);
        Glide.with(context).load(FILE_PATH + list.get(position).getFile_path()).into( holder.item_product_package_shop_img);
        String[] tag = list.get(position).getTag_key().split("#");
        holder.item_product_package_shop_tv.setText("#" + tag[1] + "\n" +"#" + tag[2] + "\n" + "#" + tag[3]);
    }
}
