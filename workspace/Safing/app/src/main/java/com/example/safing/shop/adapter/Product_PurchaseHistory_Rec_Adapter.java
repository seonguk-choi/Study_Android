package com.example.safing.shop.adapter;

import static com.example.safing.async.CommonAsk.FILE_PATH;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.safing.R;
import com.example.safing.async.OnItemClick_PurcahseHistory_Listener;
import com.example.safing.shop.DAO.ShopDAO;
import com.example.safing.shop.VO.PurchaseHistoryVO;

import java.text.NumberFormat;
import java.util.ArrayList;

public class Product_PurchaseHistory_Rec_Adapter extends RecyclerView.Adapter<Product_PurchaseHistory_Rec_Adapter.ViewHolder> implements OnItemClick_PurcahseHistory_Listener {

    Context context;
    LayoutInflater inflater;
    OnItemClick_PurcahseHistory_Listener listener;
    ArrayList<PurchaseHistoryVO> list = new ArrayList<>();
    ShopDAO dao = new ShopDAO();

    public Product_PurchaseHistory_Rec_Adapter(Context context, ArrayList<PurchaseHistoryVO> list) {
        this.context = context;
        this.list = list;
        this.inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    public void setOnItemClickListener(OnItemClick_PurcahseHistory_Listener listener){
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview = inflater.inflate(R.layout.item_product_purchasehistory_rec, parent , false );
        return new ViewHolder(itemview, this);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.binding(holder, position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onItemClick_PurchaseHistory(ViewHolder holderm, View view, int position) {
        if(listener != null){
            listener.onItemClick_PurchaseHistory(holderm, view, position);
        }
    }

    @Override
    public void onItemClick_PurchaseHistory_reivew(ViewHolder holderm, View view, int position) {
        if(listener != null){
            listener.onItemClick_PurchaseHistory_reivew(holderm, view, position);
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView item_product_purchasehistory_rec_img;
        TextView item_product_purchasehistory_rec_tv1, item_product_purchasehistory_rec_tv2, item_product_purchasehistory_rec_tv3, item_product_purchasehistory_rec_tv4;
        Button item_product_purchasehistory_rec_btn1, item_product_purchasehistory_rec_btn2;

        public ViewHolder(@NonNull View itemView, OnItemClick_PurcahseHistory_Listener listener) {
            super(itemView);
            item_product_purchasehistory_rec_img = itemView.findViewById(R.id.item_product_purchasehistory_rec_img);
            item_product_purchasehistory_rec_tv1 = itemView.findViewById(R.id.item_product_purchasehistory_rec_tv1);
            item_product_purchasehistory_rec_tv2 = itemView.findViewById(R.id.item_product_purchasehistory_rec_tv2);
            item_product_purchasehistory_rec_tv3 = itemView.findViewById(R.id.item_product_purchasehistory_rec_tv3);
            item_product_purchasehistory_rec_tv4 = itemView.findViewById(R.id.item_product_purchasehistory_rec_tv4);
            item_product_purchasehistory_rec_btn1 = itemView.findViewById(R.id.item_product_purchasehistory_rec_btn1);
            item_product_purchasehistory_rec_btn2 = itemView.findViewById(R.id.item_product_purchasehistory_rec_btn2);
        }
        public void binding(ViewHolder holder, int position){
            Glide.with(context).load(FILE_PATH + list.get(position).getFile_path()).into(holder.item_product_purchasehistory_rec_img);

            if(list.get(position).getProduct_num()>0){
                item_product_purchasehistory_rec_tv1.setText(list.get(position).getProduct_name());
            } else {
                item_product_purchasehistory_rec_tv1.setText(list.get(position).getPackage_name());
            }

            item_product_purchasehistory_rec_tv2.setText(NumberFormat.getInstance().format(list.get(position).getProduct_price()) +"원");

            if(list.get(position).getProduct_price() < 100000){
                item_product_purchasehistory_rec_tv3.setText(NumberFormat.getInstance().format(5000) +"원");
            } else {
                item_product_purchasehistory_rec_tv3.setText("무료");
            }

            item_product_purchasehistory_rec_tv4.setText(list.get(position).getOrder_state_name());

            if(("y").equals(list.get(position).getReview_check())){
                item_product_purchasehistory_rec_btn1.setText("리뷰보기");
            } else if(("n").equals(list.get(position).getReview_check()) && (list.get(position).getOrder_state_num() == 3)) {
                item_product_purchasehistory_rec_btn1.setText("리뷰쓰기");
            } else {
                item_product_purchasehistory_rec_btn1.setText("리뷰보기");
            }

            if((list.get(position).getOrder_state_num() == 3)){

            }

            if(list.get(position).getOrder_state_num() == 4) {
                item_product_purchasehistory_rec_btn2.setText("환불취소");
            }

            item_product_purchasehistory_rec_btn1.setOnClickListener(new CheckBox.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(("n").equals(list.get(position).getReview_check()) && (list.get(position).getOrder_state_num() == 3)){
                        if(listener != null){
                            listener.onItemClick_PurchaseHistory_reivew(ViewHolder.this,
                                    v, position);
                        }
                    } else {
                        if(listener != null){
                            listener.onItemClick_PurchaseHistory(ViewHolder.this,
                                    v, position);
                        }
                    }
                }
            });

            item_product_purchasehistory_rec_btn2.setOnClickListener(new CheckBox.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(list.get(position).getOrder_state_num() == 4) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        builder.setTitle("환불 확인");
                        builder.setMessage("환불취소를\n하시겠습니까? ");
                        builder.setIcon(R.drawable.question1);

                        builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                item_product_purchasehistory_rec_btn2.setText("환불하기");
                                dao.update_refund(list.get(position));
                            }
                        });

                        builder.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        });

                        AlertDialog dialog  = builder.create();
                        dialog.show();

                    } else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        builder.setTitle("환불 확인");
                        builder.setMessage("선택하신 주문을\n환불하시겠습니까? ");
                        builder.setIcon(R.drawable.question1);

                        builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                item_product_purchasehistory_rec_btn2.setText("환불취소");
                                dao.update_refund(list.get(position));
                            }
                        });

                        builder.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        });

                        AlertDialog dialog  = builder.create();
                        dialog.show();
                    }
                }
            });

        }
    }
}
