package com.example.safing.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.safing.R;

import com.example.safing.adapter.Producdt_Review_Apdater;

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

        product_review_rec.setLayoutManager(manager);
        Producdt_Review_Apdater adapter_pager1 = new Producdt_Review_Apdater(context);
        product_review_rec.setAdapter(adapter_pager1);

        return rootView;
    }
}