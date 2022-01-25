package com.example.safing.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.safing.DTO.Address_RepositoryDTO;
import com.example.safing.DTO.Product_PurchaseHistory_RecDTO;
import com.example.safing.R;

import java.util.ArrayList;

public class Address_Repository_Rec_Adapter extends RecyclerView.Adapter<Address_Repository_Rec_Adapter.ViewHolder> {

    Context context;
    ArrayList<Address_RepositoryDTO> list;
    LayoutInflater inflater;

    public Address_Repository_Rec_Adapter(Context context) {
        this.context = context;
        this.list = list;
        this.inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview = inflater.inflate(R.layout.item_address_repository_rec, parent , false );
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
        CheckBox item_address_repository_box;
        TextView item_address_repository_tv1, item_address_repository_tv2, item_address_repository_tv3;
        ImageButton item_address_repository_btn1;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            item_address_repository_box = itemView.findViewById(R.id.item_address_repository_box);
            item_address_repository_tv1 = itemView.findViewById(R.id.item_address_repository_tv1);
            item_address_repository_tv2 = itemView.findViewById(R.id.item_address_repository_tv2);
            item_address_repository_tv3 = itemView.findViewById(R.id.item_address_repository_tv3);
            item_address_repository_btn1 = itemView.findViewById(R.id.item_address_repository_btn1);


            item_address_repository_box.setOnClickListener(new CheckBox.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(((CheckBox)v).isChecked()){
                        Toast.makeText(context, "체크", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(context, "체크 취소", Toast.LENGTH_SHORT).show();
                    }
                }
            });

            item_address_repository_btn1.setOnClickListener(new CheckBox.OnClickListener() {
                @Override
                public void onClick(View v) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle("삭제 확인");
                    builder.setMessage("선택하신 주소를\n삭제하시겠습니까? ");
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
                            Toast.makeText(context, "아니오 눌림", Toast.LENGTH_SHORT).show();
                        }
                    });

                    AlertDialog dialog  = builder.create();
                    dialog.show();
                }
            });

        }
        public void binding(ViewHolder holder, int position){

        }
    }
}
