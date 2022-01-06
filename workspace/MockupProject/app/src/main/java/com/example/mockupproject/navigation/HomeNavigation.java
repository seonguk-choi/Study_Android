package com.example.mockupproject.navigation;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.mockupproject.DAO.RecyclerViewDAO;
import com.example.mockupproject.DTO.Home_Rec1DTO;
import com.example.mockupproject.R;
import com.example.mockupproject.adapter.Home_Adapter_Rec1;
import com.example.mockupproject.adapter.Home_Adapter_Rec2;
import com.example.mockupproject.adapter.Home_Adapter_Rec3;
import com.example.mockupproject.adapter.Home_Adapter_Rec4;
import com.example.mockupproject.adapter.Home_Adapter_Rec5;

import java.util.ArrayList;

public class HomeNavigation extends Fragment {
    RecyclerView rec1, rec2, rec3, rec4, rec5;
    LinearLayout linear1, linear2, linear3, linear4, linear5;
    Button btn1;
    Context context;
    RecyclerViewDAO dao = new RecyclerViewDAO();
    LinearLayoutManager manager;

    public HomeNavigation(Context context){
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.home_navigation, container, false);
        rec1 = rootView.findViewById(R.id.home_rec1);
        rec2 = rootView.findViewById(R.id.home_rec2);
        rec3 = rootView.findViewById(R.id.home_rec3);
        rec4 = rootView.findViewById(R.id.home_rec4);
        rec5 = rootView.findViewById(R.id.home_rec5);

        linear1 = rootView.findViewById(R.id.home_linear1);
        linear2 = rootView.findViewById(R.id.home_linear2);
        linear3 = rootView.findViewById(R.id.home_linear3);
        linear4 = rootView.findViewById(R.id.home_linear4);
        linear5 = rootView.findViewById(R.id.home_linear5);

        btn1 = rootView.findViewById(R.id.home_btn1);

        linear1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "페이지 이동", Toast.LENGTH_SHORT).show();
            }
        });
        linear2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "페이지 이동", Toast.LENGTH_SHORT).show();
            }
        });
        linear3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "페이지 이동", Toast.LENGTH_SHORT).show();
            }
        });
        linear4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "페이지 이동", Toast.LENGTH_SHORT).show();
            }
        });
        linear5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "페이지 이동", Toast.LENGTH_SHORT).show();
            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "페이지 이동", Toast.LENGTH_SHORT).show();
            }
        });

        setRec1();
        setRec2();
        setRec3();
        setRec4();
        setRec5();

        return  rootView;
    }

    public void setRec1(){
        manager = new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false);
        rec1.setLayoutManager(manager);
        Home_Adapter_Rec1 adapter_rec1 = new Home_Adapter_Rec1(context);
        rec1.setAdapter(adapter_rec1);
    }
    public void setRec2(){
        manager = new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false);
        rec2.setLayoutManager(manager);
        Home_Adapter_Rec2 adapter_rec2 = new Home_Adapter_Rec2(context);
        rec2.setAdapter(adapter_rec2);
    }
    public void setRec3(){
        manager = new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false);
        rec3.setLayoutManager(manager);
        Home_Adapter_Rec3 adapter_rec3 = new Home_Adapter_Rec3(context);
        rec3.setAdapter(adapter_rec3);
    }
    public void setRec4(){
        manager = new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false);
        rec4.setLayoutManager(manager);
        Home_Adapter_Rec4 adapter_rec4 = new Home_Adapter_Rec4(context);
        rec4.setAdapter(adapter_rec4);
    }
    public void setRec5(){
        manager = new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false);
        rec5.setLayoutManager(manager);
        Home_Adapter_Rec5 adapter_rec5 = new Home_Adapter_Rec5(context);
        rec5.setAdapter(adapter_rec5);
    }


}