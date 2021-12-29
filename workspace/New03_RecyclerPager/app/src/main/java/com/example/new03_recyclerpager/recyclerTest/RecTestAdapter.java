package com.example.new03_recyclerpager.recyclerTest;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.new03_recyclerpager.R;

import java.util.ArrayList;

public class RecTestAdapter extends RecyclerView.Adapter<RecTestAdapter.ViewHolder> {
    private static final String TAG = "test";
    Context context;
    ArrayList<RecTestDTO> list;
    LayoutInflater inflater;

    public RecTestAdapter(Context context, ArrayList<RecTestDTO> list) {
        this.context = context;
        this.list = list;
        this.inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview = inflater.inflate(R.layout.recyclertestitem, parent, false);

        return new ViewHolder(itemview);
    }

    //아이템이 셋팅되는 곳
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: view");
        holder.binding(holder, position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout linear;
        TextView tv1, tv2;
        ImageView imgv1, imgv2;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            linear = itemView.findViewById(R.id.linear);
            tv1 = itemView.findViewById(R.id.tv1);
            tv2 = itemView.findViewById(R.id.tv2);
            imgv1 = itemView.findViewById(R.id.imgv1);
            imgv2 = itemView.findViewById(R.id.imgv2);


            linear.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "페이지이동", Toast.LENGTH_SHORT).show();
                }
            });
        }
        public void binding(ViewHolder holder, int position){
            holder.tv1.setText(list.get(position).getTv1());
            holder.tv2.setText(list.get(position).getTv2());
            holder.imgv1.setImageResource(list.get(position).getImgv1());
            holder.imgv2.setImageResource(list.get(position).getImgv2());
        }
    }
}
