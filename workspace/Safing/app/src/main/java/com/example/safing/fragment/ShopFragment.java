package com.example.safing.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.safing.DTO.SafeZoneRecDTO;
import com.example.safing.R;
import com.example.safing.adapter.SafeZoneRecAdapter;
import com.example.safing.adapter.Shop_Rec_Adapter;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.Collection;

public class ShopFragment extends Fragment implements View.OnClickListener {
    Context context;
    TabLayout tab_layout;
    Toolbar toolbar;
    RecyclerView shop_rec1, shop_rec2;
    LinearLayoutManager manager;
    SwipeRefreshLayout swipe;
    ColorStateList def;
    TextView item1, item2, item3, item4, item5, item6, select;

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

        item1 = rootView.findViewById(R.id.shop_tab_item1);
        item2 = rootView.findViewById(R.id.shop_tab_item2);
        item3 = rootView.findViewById(R.id.shop_tab_item3);
        item4 = rootView.findViewById(R.id.shop_tab_item4);
        item5 = rootView.findViewById(R.id.shop_tab_item5);
        item6 = rootView.findViewById(R.id.shop_tab_item6);

        item1.setOnClickListener(this);
        item2.setOnClickListener(this);
        item3.setOnClickListener(this);
        item4.setOnClickListener(this);
        item5.setOnClickListener(this);
        item6.setOnClickListener(this);



        select = rootView.findViewById(R.id.shop_tab_select);
        def  = item2.getTextColors();


        //========= 햄버커 기능 ==============

        DrawerLayout drawer = rootView.findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                (Activity) context, drawer, toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_open
        );
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView tab_view = rootView.findViewById(R.id.shop_view);

        View nav_headerview = tab_view.getHeaderView(0);
        ImageView header_imge = nav_headerview.findViewById(R.id.header_imge);
        TextView header_text= nav_headerview.findViewById(R.id.header_text);

        //Glide.with(context).load(CommonVal.loginInfo.getMember_filepath()).into(header_imge);
        //header_text.setText(CommonVal.loginInfo.getMember_id());

        //========= 탭 기능 ==============

//        tab_layout.addTab(tab_layout.newTab().setText("감성용품"));
//        tab_layout.addTab(tab_layout.newTab().setText("텐트"));
//        tab_layout.addTab(tab_layout.newTab().setText("의자"));
//        tab_layout.addTab(tab_layout.newTab().setText("테이블"));
//        tab_layout.addTab(tab_layout.newTab().setText("매트/침낭"));
//        tab_layout.addTab(tab_layout.newTab().setText("취사/BBQ"));
//
//        tab_layout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//                if(tab.getPosition() == 0){
//                    Toast.makeText(context, "tab1", Toast.LENGTH_SHORT).show();
//                    shop_rec2.setLayoutManager(manager);
//                    Shop_Rec_Adapter adapter_rec2 = new Shop_Rec_Adapter(context);
//                    shop_rec2.setAdapter(adapter_rec2);
//                }
//                if(tab.getPosition() == 1){
//                    Toast.makeText(context, "tab2", Toast.LENGTH_SHORT).show();
//                    shop_rec2.setLayoutManager(manager);
//                    Shop_Rec_Adapter adapter_rec2 = new Shop_Rec_Adapter(context);
//                    shop_rec2.setAdapter(adapter_rec2);
//                }
//                if(tab.getPosition() == 2){
//                    Toast.makeText(context, "tab3", Toast.LENGTH_SHORT).show();
//                    shop_rec2.setLayoutManager(manager);
//                    Shop_Rec_Adapter adapter_rec2 = new Shop_Rec_Adapter(context);
//                    shop_rec2.setAdapter(adapter_rec2);
//                }
//                if(tab.getPosition() == 3){
//                    Toast.makeText(context, "tab4", Toast.LENGTH_SHORT).show();
//                    shop_rec2.setLayoutManager(manager);
//                    Shop_Rec_Adapter adapter_rec2 = new Shop_Rec_Adapter(context);
//                    shop_rec2.setAdapter(adapter_rec2);
//                }
//                if(tab.getPosition() == 4){
//                    Toast.makeText(context, "tab5", Toast.LENGTH_SHORT).show();
//                    shop_rec2.setLayoutManager(manager);
//                    Shop_Rec_Adapter adapter_rec2 = new Shop_Rec_Adapter(context);
//                    shop_rec2.setAdapter(adapter_rec2);
//                }
//                if(tab.getPosition() == 5){
//                    Toast.makeText(context, "tab6", Toast.LENGTH_SHORT).show();
//                    shop_rec2.setLayoutManager(manager);
//                    Shop_Rec_Adapter adapter_rec2 = new Shop_Rec_Adapter(context);
//                    shop_rec2.setAdapter(adapter_rec2);
//                }
//            }
//
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//
//            }
//        }); //tab_layout

        setRec1();
        setRec2();

        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                setRec2();
                swipe.setRefreshing(false);
            }
        });


        return rootView;
    }

    public void setRec1(){
        manager = new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false);
        ArrayList<SafeZoneRecDTO> list = new ArrayList<>();
        list.add(new SafeZoneRecDTO(R.layout.rec_item_sfzone, ""));
        list.add(new SafeZoneRecDTO(R.layout.rec_item_sfzone, ""));
        list.add(new SafeZoneRecDTO(R.layout.rec_item_sfzone, ""));
        list.add(new SafeZoneRecDTO(R.layout.rec_item_sfzone, ""));
        list.add(new SafeZoneRecDTO(R.layout.rec_item_sfzone, ""));
        list.add(new SafeZoneRecDTO(R.layout.rec_item_sfzone, ""));


        shop_rec1.setLayoutManager(manager);
        SafeZoneRecAdapter adapter_rec1 = new SafeZoneRecAdapter(context, list);
        shop_rec1.setAdapter(adapter_rec1);
    }
    public void setRec2(){
        manager = new LinearLayoutManager(context, RecyclerView.VERTICAL, false);
        //ArrayList<ProductDTO> list = new ArrayList<>();

        shop_rec2.setLayoutManager(manager);
        Shop_Rec_Adapter adapter_rec2 = new Shop_Rec_Adapter(context);
        shop_rec2.setAdapter(adapter_rec2);
    }

    @Override
    public void onClick(View v) {
        if(getView().getId() == R.id.shop_tab_item1){
            select.animate().x(0).setDuration(100);
            item1.setTextColor(Color.rgb());
        }
    }
}