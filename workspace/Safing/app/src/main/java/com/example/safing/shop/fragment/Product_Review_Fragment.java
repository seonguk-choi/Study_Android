package com.example.safing.shop.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.safing.R;

import com.example.safing.shop.DAO.ShopDAO;
import com.example.safing.shop.VO.ReviewVO;
import com.example.safing.shop.adapter.Product_Review_Apdater;

import java.util.ArrayList;

public class Product_Review_Fragment extends Fragment {
    Context context;
    RecyclerView product_review_rec;
    LinearLayoutManager manager;
    int num = 0;
    String category = "";
    ArrayList<ReviewVO> list = new ArrayList<>();
    ShopDAO dao = new ShopDAO();

    public Product_Review_Fragment(Context context,  int num, String category){
        this.context = context;
        this.num = num;
        this.category = category;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_review_product, container, false);

        product_review_rec = rootView.findViewById(R.id.product_review_rec);

        setRec(num, category);

        return rootView;
    }

    public void setRec(int num, String category){
        if(("product").equals(category)){
            list = dao.review_list_pro(num);
        } else {
            list = dao.review_list_pack(num);
        }

        manager = new LinearLayoutManager(context, RecyclerView.VERTICAL, false);

        product_review_rec.setLayoutManager(manager);
        Product_Review_Apdater adapter_rec1 = new Product_Review_Apdater(context, list);
        product_review_rec.setAdapter(adapter_rec1);

    }
}