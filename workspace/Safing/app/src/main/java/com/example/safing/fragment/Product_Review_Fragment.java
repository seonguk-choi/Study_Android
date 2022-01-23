package com.example.safing.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.safing.DTO.Product_Cart_RecDTO;
import com.example.safing.DTO.Product_ReviewlDTO;
import com.example.safing.R;

import com.example.safing.adapter.Producdt_Review_Apdater;
import com.example.safing.adapter.Product_Cart_Rec_Adapter;

import java.util.ArrayList;

public class Product_Review_Fragment extends Fragment {
    Context context;
    RecyclerView product_review_rec;
    LinearLayoutManager manager;

    public Product_Review_Fragment(Context context){
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_review_product, container, false);

        product_review_rec = rootView.findViewById(R.id.product_review_rec);

        setRec1();

        return rootView;
    }

    public void setRec1(){
        manager = new LinearLayoutManager(context, RecyclerView.VERTICAL, false);

        product_review_rec.setLayoutManager(manager);
        Producdt_Review_Apdater adapter_rec1 = new Producdt_Review_Apdater(context);
        product_review_rec.setAdapter(adapter_rec1);

    }
}