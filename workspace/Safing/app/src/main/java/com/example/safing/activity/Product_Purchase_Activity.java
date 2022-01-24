package com.example.safing.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.widget.Toast;

import com.example.safing.R;
import com.example.safing.adapter.Shop_Product_Pager_Adapter;
import com.example.safing.fragment.Address_Defualt_Fragment;
import com.example.safing.fragment.Address_Repogitory_Fragment;
import com.example.safing.fragment.Product_Detail_Fragment;
import com.example.safing.fragment.Product_Review_Fragment;
import com.google.android.material.tabs.TabLayout;

public class Product_Purchase_Activity extends AppCompatActivity {
    TabLayout product_purchase_tab1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_purchase);

        product_purchase_tab1 = findViewById(R.id.product_purchase_tab1);

        //========= 탭 기능 ==============

        product_purchase_tab1.addTab(product_purchase_tab1.newTab().setText("기본 주소"));
        product_purchase_tab1.addTab(product_purchase_tab1.newTab().setText("주소 선택"));

        changeFragment(new Address_Defualt_Fragment(Product_Purchase_Activity.this));

        product_purchase_tab1.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                if(position == 0){
                    Toast.makeText(Product_Purchase_Activity.this, "기본 주소", Toast.LENGTH_SHORT).show();
                    changeFragment(new Address_Defualt_Fragment(Product_Purchase_Activity.this));
                } else {
                    Toast.makeText(Product_Purchase_Activity.this, "다른주소", Toast.LENGTH_SHORT).show();
                    changeFragment(new Address_Repogitory_Fragment(Product_Purchase_Activity.this));
                }
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }

    public void changeFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.shop_container2 , fragment).commit();
    }
}