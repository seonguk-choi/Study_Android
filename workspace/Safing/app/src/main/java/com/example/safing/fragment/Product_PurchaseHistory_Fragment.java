package com.example.safing.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.safing.DTO.Product_PurchaseHistory_RecDTO;
import com.example.safing.DTO.Shop_PackageDTO;
import com.example.safing.R;
import com.example.safing.activity.MainActivity;
import com.example.safing.adapter.Product_PurchaseHistory_Rec_Adapter;
import com.example.safing.adapter.Shop_Package_Apdater;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class Product_PurchaseHistory_Fragment extends Fragment {
    Context context;
    Toolbar toolbar;
    RecyclerView product_purchaseHistory_rec1;
    LinearLayoutManager manager;
    SwipeRefreshLayout swipe;
    NavigationView product_purchaseHistory_view;
    MainActivity mainActivity = new MainActivity();

    public Product_PurchaseHistory_Fragment(Context context){
        this.context = context;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_product_purchase_history, container, false);

        toolbar = rootView.findViewById(R.id.product_purchaseHistory_toolbar);
        swipe = rootView.findViewById(R.id.product_purchaseHistory_swipe);
        product_purchaseHistory_view = rootView.findViewById(R.id.product_purchaseHistory_view);
        product_purchaseHistory_rec1 = rootView.findViewById(R.id.product_purchaseHistory_rec1);

        mainActivity = (MainActivity) getActivity();

        //========= 햄버커 기능 ==============

        DrawerLayout drawer = rootView.findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                (Activity) context, drawer, toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_open
        );
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        View nav_headerview = product_purchaseHistory_view.getHeaderView(0);
        ImageView header_imge = nav_headerview.findViewById(R.id.header_imge);
        TextView header_text= nav_headerview.findViewById(R.id.header_text);

        //  Glide.with(context).load(CommonVal.loginInfo.getMember_filepath()).into(header_imge);
        //  header_text.setText(CommonVal.loginInfo.getMember_id());

        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipe.setRefreshing(false);
            }
        });

        //============= navigation view 기능=====

        product_purchaseHistory_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId() == R.id.menu_cart){
                    mainActivity.changeFragment(new Product_Cart_Fragment(context));
                }else if(item.getItemId() == R.id.menu_purchasehistory){
                    mainActivity.changeFragment(new Product_PurchaseHistory_Fragment(context));
                }else if(item.getItemId() == R.id.menu_customerservice){
                }
                return false;
            }
        });

        setRec1();

        return rootView;
    }

    public void setRec1(){
        manager = new LinearLayoutManager(context, RecyclerView.VERTICAL, false);
        ArrayList<Product_PurchaseHistory_RecDTO> list = new ArrayList<>();


        product_purchaseHistory_rec1.setLayoutManager(manager);
        Product_PurchaseHistory_Rec_Adapter adapter_rec1 = new Product_PurchaseHistory_Rec_Adapter(context);
        product_purchaseHistory_rec1.setAdapter(adapter_rec1);
    }
}