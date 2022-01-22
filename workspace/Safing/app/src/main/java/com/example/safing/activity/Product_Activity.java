package com.example.safing.activity;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.safing.R;
import com.example.safing.adapter.Shop_Product_Pager_Adapter;
import com.example.safing.async.CommonVal;
import com.example.safing.fragment.Product_Detail_Fragment;
import com.example.safing.fragment.Product_Review_Fragment;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

public class Product_Activity extends AppCompatActivity {
    ViewPager2 pager;
    TabLayout tab_layout;
    Toolbar toolbar;
    SwipeRefreshLayout swipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        tab_layout = findViewById(R.id.shop_product_tab1);
        pager = findViewById(R.id.shop_product_Pager);
        toolbar = findViewById(R.id.shop_product_toolbar);
        swipe = findViewById(R.id.shop_product_swipe);

        //========= 햄버커 기능 ==============

        DrawerLayout drawer = findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                Product_Activity.this, drawer, toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_open
        );
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView tab_view = findViewById(R.id.shop_view);

        View nav_headerview = tab_view.getHeaderView(0);
        ImageView header_imge = nav_headerview.findViewById(R.id.header_imge);
        TextView header_text= nav_headerview.findViewById(R.id.header_text);

        //  Glide.with(context).load(CommonVal.loginInfo.getMember_filepath()).into(header_imge);
        //  header_text.setText(CommonVal.loginInfo.getMember_id());

        //========= 탭 기능 ==============

        tab_layout.addTab(tab_layout.newTab().setText("상세정보"));
        tab_layout.addTab(tab_layout.newTab().setText("리뷰"));

        Shop_Product_Pager_Adapter adapter_pager1 = new Shop_Product_Pager_Adapter(Product_Activity.this);
        pager.setAdapter(adapter_pager1);

        changeFragment(new Product_Detail_Fragment(Product_Activity.this));

        tab_layout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                if(position == 0){
                    Toast.makeText(Product_Activity.this, "상세정보", Toast.LENGTH_SHORT).show();
                    changeFragment(new Product_Detail_Fragment(Product_Activity.this));
                } else {
                    Toast.makeText(Product_Activity.this, "리뷰", Toast.LENGTH_SHORT).show();
                    changeFragment(new Product_Review_Fragment(Product_Activity.this));
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipe.setRefreshing(false);
            }
        });
    }
    public void changeFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.shop_container2 , fragment).commit();
    }
}