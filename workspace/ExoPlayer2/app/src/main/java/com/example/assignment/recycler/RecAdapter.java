package com.example.assignment.recycler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assignment.MainActivity;
import com.example.assignment.OnItemClick;
import com.example.assignment.R;
import com.example.assignment.fragment.Fragment1;
import com.example.assignment.fragment.Fragment2;

import java.util.ArrayList;

public class RecAdapter extends RecyclerView.Adapter<RecAdapter.ViewHolder>
        implements OnItemClick {


    OnItemClick mCallback;
    Fragment fragment1;
    Fragment fragment2;
    Context context;
    LayoutInflater inflater;
    ArrayList<RecDTO> list;


    public RecAdapter(Context context) {
        this.context = context;
        this.list = new ArrayList<>();
        this.inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setOnItemClickListener(OnItemClick listener) {
        this.mCallback = listener;
    }

    public RecAdapter(Context context, OnItemClick listener) {
        this.context = context;
        this.mCallback = listener;
        this.list = new ArrayList<>();
        this.inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public RecAdapter(Context context, ArrayList<RecDTO> list) {
        this.context = context;
        this.list = list;
        this.inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void addDto(RecDTO dto) {
        list.add(dto);
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview =
                inflater.inflate(R.layout.recycleritem, parent,
                        false);

        return new ViewHolder(itemview, this);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        RecDTO dto = list.get(position);
        holder.setDto(dto);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onClick(String value) {
        if (mCallback != null) {
            mCallback.onClick(value);
        }
    }

        public class ViewHolder extends RecyclerView.ViewHolder {
            ImageView imgv1,icon1,icon2,icon3,icon4;
            TextView title; //xml에 있는 위젯들을 전역변수로 선언.
            TextView tv1, tv2;


            public ViewHolder(@NonNull View itemView, OnItemClick listener) {
                super(itemView);
                imgv1 = itemView.findViewById(R.id.item_imgv);
                title = itemView.findViewById(R.id.item_title);
                tv1 = itemView.findViewById(R.id.tv_1);
                tv2 = itemView.findViewById(R.id.tv_2);
                fragment1 = new Fragment1();
                fragment2 = new Fragment2();
                icon1 = itemView.findViewById(R.id.item_icon1);
                icon2 = itemView.findViewById(R.id.item_icon2);
                icon3 = itemView.findViewById(R.id.item_icon3);
                icon4 = itemView.findViewById(R.id.item_icon4);




                //https://4whomtbts.tistory.com/52 참고했음 어댑터에서 메인 작동시키는 방법
                tv1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (listener != null) {
                            Toast.makeText(context, "추천_pager2", Toast.LENGTH_SHORT).show();
                            MainActivity activity = (MainActivity) context;
                            activity.onClick("1");
                           // mCallback.onClick("1");
                        }
                    }

                });
                tv2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (listener != null) {
                            Toast.makeText(context, "물법TV_Recycler", Toast.LENGTH_SHORT).show();
                            MainActivity activity = (MainActivity) context;
                            activity.onClick("2");
                           // mCallback.onClick("2");
                        }
                    }
                });
                icon1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(context, "1번 아이콘 작동", Toast.LENGTH_SHORT).show();
                    }
                });
                icon2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(context, "2번 아이콘 작동", Toast.LENGTH_SHORT).show();
                    }
                });
                icon3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(context, "3번 아이콘 작동", Toast.LENGTH_SHORT).show();
                    }
                });
                icon4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(context, "4번 아이콘 작동", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            public void setDto(RecDTO dto) {
                title.setText(dto.getTextStr());
                imgv1.setImageResource(dto.getResId());
            }


        }
    }
