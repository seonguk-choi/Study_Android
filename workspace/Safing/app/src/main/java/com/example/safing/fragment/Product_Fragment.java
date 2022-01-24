package com.example.safing.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.safing.R;
import com.example.safing.activity.MainActivity;
import com.example.safing.activity.Product_Purchase_Activity;
import com.example.safing.adapter.Shop_Product_Pager_Adapter;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

public class Product_Fragment extends Fragment {
    Context context;
    ViewPager2 pager;
    TabLayout shop_product_tab1;
    Toolbar toolbar;
    SwipeRefreshLayout swipe;
    Button shop_product_btn1;
    NavigationView shop_product_view;
    MainActivity mainActivity = new MainActivity();

    public Product_Fragment(Context context){
        this.context = context;
    }

    public Product_Fragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_product, container, false);

        shop_product_tab1 = rootView.findViewById(R.id.shop_product_tab1);
        pager = rootView.findViewById(R.id.shop_product_Pager);
        toolbar = rootView.findViewById(R.id.shop_product_toolbar);
        swipe = rootView.findViewById(R.id.shop_product_swipe);
        shop_product_btn1 = rootView.findViewById(R.id.shop_product_btn1);
        shop_product_view = rootView.findViewById(R.id.shop_product_view);

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

        View nav_headerview = shop_product_view.getHeaderView(0);
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


        //========= 탭 기능 ==============

        shop_product_tab1.addTab(shop_product_tab1.newTab().setText("상세정보"));
        shop_product_tab1.addTab(shop_product_tab1.newTab().setText("리뷰"));

        Shop_Product_Pager_Adapter adapter_pager1 = new Shop_Product_Pager_Adapter(context);
        pager.setAdapter(adapter_pager1);

        changeFragment(new Product_Detail_Fragment(context));

        shop_product_tab1.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                if(position == 0){
                    Toast.makeText(context, "상세정보", Toast.LENGTH_SHORT).show();
                    changeFragment(new Product_Detail_Fragment(context));
                } else {
                    Toast.makeText(context, "리뷰", Toast.LENGTH_SHORT).show();
                    changeFragment(new Product_Review_Fragment(context));
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

        shop_product_btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Product_Purchase_Activity.class);
                startActivity(intent);
            }
        });

        //============= navigation view 기능=====

        shop_product_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
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
    public void changeFragment(Fragment fragment){
        mainActivity.getSupportFragmentManager().beginTransaction().replace(R.id.shop_container2 , fragment).commit();
    }

}