package com.example.safing.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.safing.R;
import com.example.safing.activity.MainActivity;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;


public class Product_Purchase_Fragment extends Fragment {
    Context context;
    TabLayout product_purchase_tab1;
    MainActivity mainActivity = new MainActivity();
    Toolbar toolbar;
    NavigationView product_purchase_view;


    public Product_Purchase_Fragment(Context context){
        this.context = context;
    }

    public Product_Purchase_Fragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = (ViewGroup) inflater.inflate(R.layout.fragment_product_purchase, container, false);

        product_purchase_tab1 = rootView.findViewById(R.id.product_purchase_tab1);
        toolbar = rootView.findViewById(R.id.product_purchase_toolbar);
        product_purchase_view = rootView.findViewById(R.id.product_purchase_view);

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

        View nav_headerview = product_purchase_view.getHeaderView(0);
        ImageView header_imge = nav_headerview.findViewById(R.id.header_imge);
        TextView header_text= nav_headerview.findViewById(R.id.header_text);

        //  Glide.with(context).load(CommonVal.loginInfo.getMember_filepath()).into(header_imge);
        //  header_text.setText(CommonVal.loginInfo.getMember_id());


        product_purchase_tab1.addTab(product_purchase_tab1.newTab().setText("기본 주소"));
        product_purchase_tab1.addTab(product_purchase_tab1.newTab().setText("주소 선택"));

        changeFragment(new Address_Default_Fragment(context));



        product_purchase_tab1.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                if(position == 0){
                    Toast.makeText(context, "기본 주소", Toast.LENGTH_SHORT).show();
                    changeFragment(new Address_Default_Fragment(context));
                } else {
                    Toast.makeText(context, "다른주소", Toast.LENGTH_SHORT).show();

                    changeFragment(new Address_Repogitory_Fragment(context));
                }
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

        //============= navigation view 기능=====

        product_purchase_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
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
        mainActivity.getSupportFragmentManager().beginTransaction().replace(R.id.product_purchase_container , fragment).commit();
    }
}