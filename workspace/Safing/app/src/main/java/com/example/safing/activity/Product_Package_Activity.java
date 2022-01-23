package com.example.safing.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager2.widget.ViewPager2;

import com.example.safing.R;
import com.example.safing.adapter.Shop_Product_Pager_Adapter;
import com.example.safing.fragment.Product_Cart_Fragment;
import com.example.safing.fragment.Product_Detail_Fragment;
import com.example.safing.fragment.Product_PurchaseHistory_Fragment;
import com.example.safing.fragment.Product_Review_Fragment;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

public class Product_Package_Activity extends AppCompatActivity {
    ViewPager2 pager;
    TabLayout shop_product_tab1;
    Toolbar toolbar;
    SwipeRefreshLayout swipe;
    ImageView shop_product_img1;
    Button shop_product_btn1;
    NavigationView shop_product_view;
    MainActivity mainActivity = new MainActivity();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_package_product);

        shop_product_tab1 = findViewById(R.id.shop_product_tab1);

        pager = findViewById(R.id.shop_product_Pager);
        toolbar = findViewById(R.id.shop_product_toolbar);
        swipe = findViewById(R.id.shop_product_swipe);
        shop_product_img1 = findViewById(R.id.shop_product_img1);
        shop_product_btn1 = findViewById(R.id.shop_product_btn1);
        shop_product_view = findViewById(R.id.shop_product_view);

        //========= 햄버커 기능 ==============

        DrawerLayout drawer = findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                Product_Package_Activity.this, drawer, toolbar,
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

        //========= 탭 기능 ==============

        shop_product_tab1.addTab(shop_product_tab1.newTab().setText("상세정보"));
        shop_product_tab1.addTab(shop_product_tab1.newTab().setText("리뷰"));


        Shop_Product_Pager_Adapter adapter_pager1 = new Shop_Product_Pager_Adapter(Product_Package_Activity.this);
        pager.setAdapter(adapter_pager1);

        shop_product_img1.setImageResource(R.drawable.camimg4);

        changeFragment(new Product_Detail_Fragment(Product_Package_Activity.this));

        shop_product_tab1.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                if(position == 0){
                    Toast.makeText(Product_Package_Activity.this, "상세정보", Toast.LENGTH_SHORT).show();
                    changeFragment(new Product_Detail_Fragment(Product_Package_Activity.this));
                } else {
                    Toast.makeText(Product_Package_Activity.this, "리뷰", Toast.LENGTH_SHORT).show();
                    changeFragment(new Product_Review_Fragment(Product_Package_Activity.this));
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
                Intent intent = new Intent(Product_Package_Activity.this, Product_Purchase_Activity.class);
                startActivity(intent);
            }
        });

        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipe.setRefreshing(false);
            }
        });

        //============= navigation view 기능=====

        shop_product_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId() == R.id.menu_cart){
                    Intent intent = new Intent(Product_Package_Activity.this, MainActivity.class);
                    startActivity(intent);
                    mainActivity.changeFragment(new Product_Cart_Fragment(Product_Package_Activity.this));
                }else if(item.getItemId() == R.id.menu_purchasehistory){
                    Intent intent = new Intent(Product_Package_Activity.this, MainActivity.class);
                    startActivity(intent);
                    mainActivity.changeFragment(new Product_PurchaseHistory_Fragment(Product_Package_Activity.this));
                }else if(item.getItemId() == R.id.menu_customerservice){
                }
                return false;
            }
        });


    }
    public void changeFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.shop_container1 , fragment).commit();
    }
}