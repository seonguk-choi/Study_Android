package com.example.safing.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.safing.R;
import com.example.safing.activity.MainActivity;
import com.google.android.material.navigation.NavigationView;


public class Address_Defualt_Fragment extends Fragment {
    Context context;
    LinearLayoutManager manager;


    public Address_Defualt_Fragment(Context context){
        this.context = context;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_address_defualt, container, false);



        return rootView;
    }
}