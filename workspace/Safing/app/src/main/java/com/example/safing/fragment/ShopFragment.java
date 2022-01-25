package com.example.safing.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.safing.DTO.Shop_PackageDTO;
import com.example.safing.R;
import com.example.safing.activity.MainActivity;
import com.example.safing.adapter.Shop_Rec_Adapter;
import com.example.safing.adapter.Shop_Package_Apdater;
import com.example.safing.async.OnItemClick_Package_Listener;
import com.example.safing.async.OnItemClick_product_Listener;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class ShopFragment extends Fragment{
    Context context;
    TabLayout tab_layout;
    Toolbar toolbar;
    RecyclerView shop_rec1, shop_rec2;
    LinearLayoutManager manager;
    SwipeRefreshLayout swipe;
    NavigationView shop_view;
    MainActivity mainActivity = new MainActivity();
    Shop_Package_Apdater adapter_rec1;
    Shop_Rec_Adapter adapter_rec2;

    public ShopFragment(Context context){
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_shop, container, false);


        tab_layout = rootView.findViewById(R.id.shop_tab);
        shop_rec1 = rootView.findViewById(R.id.shop_rec1);
        shop_rec2 = rootView.findViewById(R.id.shop_rec2);
        toolbar = rootView.findViewById(R.id.toolbar);
        swipe = rootView.findViewById(R.id.spot_swipe);
        shop_view = rootView.findViewById(R.id.shop_view);

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

        View nav_headerview = shop_view.getHeaderView(0);
        ImageView header_imge = nav_headerview.findViewById(R.id.header_imge);
        TextView header_text= nav_headerview.findViewById(R.id.header_text);

      //  Glide.with(context).load(CommonVal.loginInfo.getMember_filepath()).into(header_imge);
      //  header_text.setText(CommonVal.loginInfo.getMember_id());

        //========= 탭 기능 ==============

        tab_layout.addTab(tab_layout.newTab().setText("감성용품"));
        tab_layout.addTab(tab_layout.newTab().setText("텐트"));
        tab_layout.addTab(tab_layout.newTab().setText("의자"));
        tab_layout.addTab(tab_layout.newTab().setText("테이블"));
        tab_layout.addTab(tab_layout.newTab().setText("매트/침낭"));
        tab_layout.addTab(tab_layout.newTab().setText("취사/BBQ"));

        tab_layout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if(tab.getPosition() == 0){
                    Toast.makeText(context, "tab1", Toast.LENGTH_SHORT).show();
                    setRec2();
                }
                if(tab.getPosition() == 1){
                    Toast.makeText(context, "tab2", Toast.LENGTH_SHORT).show();
                    setRec2();
                }
                if(tab.getPosition() == 2){
                    Toast.makeText(context, "tab3", Toast.LENGTH_SHORT).show();
                    setRec2();
                }
                if(tab.getPosition() == 3){
                    Toast.makeText(context, "tab4", Toast.LENGTH_SHORT).show();
                    setRec2();
                }
                if(tab.getPosition() == 4){

                    Toast.makeText(context, "tab5", Toast.LENGTH_SHORT).show();
                    setRec2();
                }
                if(tab.getPosition() == 5){
                    Toast.makeText(context, "tab6", Toast.LENGTH_SHORT).show();
                    setRec2();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        }); //tab_layout

        setRec1();
        setRec2();

        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                setRec2();
                swipe.setRefreshing(false);
            }
        });

        //============= navigation view 기능=====

        shop_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
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

        return rootView;
    }

    public void setRec1(){
        manager = new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false);
        ArrayList<Shop_PackageDTO> list = new ArrayList<>();
        list.add(new Shop_PackageDTO(R.layout.rec_item_sfzone, ""));
        list.add(new Shop_PackageDTO(R.layout.rec_item_sfzone, ""));
        list.add(new Shop_PackageDTO(R.layout.rec_item_sfzone, ""));
        list.add(new Shop_PackageDTO(R.layout.rec_item_sfzone, ""));
        list.add(new Shop_PackageDTO(R.layout.rec_item_sfzone, ""));
        list.add(new Shop_PackageDTO(R.layout.rec_item_sfzone, ""));


        shop_rec1.setLayoutManager(manager);
        adapter_rec1 = new Shop_Package_Apdater(context, list);
        shop_rec1.setAdapter(adapter_rec1);

        adapter_rec1.setOnItemClickListener(new OnItemClick_Package_Listener() {
            @Override
            public void onItemClick_package(Shop_Package_Apdater.ViewHolder holderm, View view, int position) {
                mainActivity.changeFragment(new Product_Package_Fragment(context));
            }
        });


    }
    public void setRec2(){
        manager = new LinearLayoutManager(context, RecyclerView.VERTICAL, false);
        //ArrayList<ProductDTO> list = new ArrayList<>();

        shop_rec2.setLayoutManager(manager);
        adapter_rec2 = new Shop_Rec_Adapter(context);
        shop_rec2.setAdapter(adapter_rec2);

        adapter_rec2.setOnItemClickListener(new OnItemClick_product_Listener() {
            @Override
            public void onItemClick_product(Shop_Rec_Adapter.ViewHolder holderm, View view, int position) {
                mainActivity.changeFragment(new Product_Fragment(context));
            }
        });
    }
}