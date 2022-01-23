package com.example.safing.fragment;

import android.content.Context;
import android.os.Bundle;


import androidx.fragment.app.Fragment;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.safing.DTO.Product_Cart_RecDTO;
import com.example.safing.DTO.Product_DetailDTO;
import com.example.safing.R;
import com.example.safing.adapter.Producdt_Detail_Apdater;
import com.example.safing.adapter.Product_Cart_Rec_Adapter;

import java.util.ArrayList;

public class Product_Detail_Fragment extends Fragment {
    Context context;
    ImageView product_detail_img;
    RecyclerView product_detail_rec;
    LinearLayoutManager manager;


    public Product_Detail_Fragment(Context context){
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_detail_product, container, false);

        product_detail_rec = rootView.findViewById(R.id.product_detail_rec);
        product_detail_img = rootView.findViewById(R.id.product_detail_img);

        //product_detail_img.setImageResource();

        setRec1();

        return rootView;
    }

    public void setRec1(){
        manager = new LinearLayoutManager(context, RecyclerView.VERTICAL, false);
        ArrayList<Product_DetailDTO> list = new ArrayList<>();

        product_detail_rec.setLayoutManager(manager);
        Producdt_Detail_Apdater adapter_rec1 = new Producdt_Detail_Apdater(context);
        product_detail_rec.setAdapter(adapter_rec1);
    }
}