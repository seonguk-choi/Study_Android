package com.example.safing.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.safing.DTO.Address_RepositoryDTO;
import com.example.safing.DTO.Product_Cart_RecDTO;
import com.example.safing.R;
import com.example.safing.adapter.Address_Repository_Rec_Adapter;
import com.example.safing.adapter.Product_Cart_Rec_Adapter;

import java.util.ArrayList;

public class Address_Repogitory_Fragment extends Fragment {
    Context context;
    LinearLayoutManager manager;
    RecyclerView Address_Repogitory_rec;

    public Address_Repogitory_Fragment(Context context){
        this.context = context;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = (ViewGroup)inflater.inflate(R.layout.fragment_address_repogitory, container, false);

        Address_Repogitory_rec = rootView.findViewById(R.id.Address_Repogitory_rec);

        setRec1();

        return rootView;
    }
    public void setRec1(){
        manager = new LinearLayoutManager(context, RecyclerView.VERTICAL, false);
        ArrayList<Address_RepositoryDTO> list = new ArrayList<>();

        Address_Repogitory_rec.setLayoutManager(manager);
        Address_Repository_Rec_Adapter adapter_rec1 = new Address_Repository_Rec_Adapter(context);
        Address_Repogitory_rec.setAdapter(adapter_rec1);
    }
}