package com.example.safing.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.safing.DTO.Product_PurchaseHistory_RecDTO;
import com.example.safing.R;

import java.util.ArrayList;

public class Product_PurchaseHistory_Rec_Adapter extends RecyclerView.Adapter<Product_PurchaseHistory_Rec_Adapter.ViewHolder> {

    Context context;
    ArrayList<Product_PurchaseHistory_RecDTO> list;
    LayoutInflater inflater;

    public Product_PurchaseHistory_Rec_Adapter(Context context) {
        this.context = context;
        this.list = list;
        this.inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview = inflater.inflate(R.layout.item_product_purchasehistory_rec, parent , false );
        return new ViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }


    @Override
    public int getItemCount() {
        return 5;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView item_product_purchasehistory_rec_img;
        TextView item_product_purchasehistory_rec_tv1, item_product_purchasehistory_rec_tv2, item_product_purchasehistory_rec_tv3, item_product_purchasehistory_rec_tv4;
        CheckBox item_product_purchasehistory_rec_box;
        ImageButton item_product_purchasehistory_rec_btn1;
        Button item_product_purchasehistory_rec_btn2, item_product_purchasehistory_rec_btn3;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            item_product_purchasehistory_rec_img = itemView.findViewById(R.id.item_product_purchasehistory_rec_img);
            item_product_purchasehistory_rec_tv1 = itemView.findViewById(R.id.item_product_purchasehistory_rec_tv1);
            item_product_purchasehistory_rec_tv2 = itemView.findViewById(R.id.item_product_purchasehistory_rec_tv2);
            item_product_purchasehistory_rec_tv3 = itemView.findViewById(R.id.item_product_purchasehistory_rec_tv3);
            item_product_purchasehistory_rec_tv4 = itemView.findViewById(R.id.item_product_purchasehistory_rec_tv4);
            item_product_purchasehistory_rec_box = itemView.findViewById(R.id.item_product_purchasehistory_rec_box);
            item_product_purchasehistory_rec_btn1 = itemView.findViewById(R.id.item_product_purchasehistory_rec_btn1);
            item_product_purchasehistory_rec_btn2 = itemView.findViewById(R.id.item_product_purchasehistory_rec_btn2);
            item_product_purchasehistory_rec_btn3 = itemView.findViewById(R.id.item_product_purchasehistory_rec_btn3);


            item_product_purchasehistory_rec_box.setOnClickListener(new CheckBox.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(((CheckBox)v).isChecked()){
                        Toast.makeText(context, "체크", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(context, "체크 취소", Toast.LENGTH_SHORT).show();
                    }
                }
            });

            item_product_purchasehistory_rec_box.setOnClickListener(new CheckBox.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(((CheckBox)v).isChecked()){
                        Toast.makeText(context, "체크", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(context, "체크 취소", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            item_product_purchasehistory_rec_btn1.setOnClickListener(new CheckBox.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //AlertDilog Custom 인터넷 예제보고 공부해보기
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle("주문삭제 확인");
                    builder.setMessage("선택하신 주문을 \n 삭제하시겠습니까? ");
                    builder.setIcon(R.drawable.question);

                    builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(context, "예 눌림", Toast.LENGTH_SHORT).show();

                        }
                    });

                    //builder.nagative 아니오 눌림 Toast
                    builder.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(context, "예 눌림", Toast.LENGTH_SHORT).show();
                        }
                    });

                    AlertDialog dialog  = builder.create();
                    dialog.show();
                }
            });

            item_product_purchasehistory_rec_btn2.setOnClickListener(new CheckBox.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "리뷰쓰기", Toast.LENGTH_SHORT).show();
                }
            });

            item_product_purchasehistory_rec_btn3.setOnClickListener(new CheckBox.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "환불하기", Toast.LENGTH_SHORT).show();
                }
            });



        }
        public void binding(Shop_Rec_Adapter.ViewHolder holder, int position){

        }
    }
}
