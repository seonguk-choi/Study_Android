package com.example.safing.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.example.safing.R;
import com.example.safing.activity.MainActivity;
import com.example.safing.async.CommonVal;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

public class ShopFragment extends Fragment {
    Context context;
    LinearLayoutManager manager;
    MainActivity mainActivity = new MainActivity();
    TabLayout tab_layout;
    Toolbar toolbar;
    RecyclerView shop_rec1, shop_rec2;

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
        //toolbar = rootView.findViewById(R.id.shop_menu_view);


        //========= 햄버커 기능 ==============

//        mainActivity.setSupportActionBar(toolbar);
//
//        DrawerLayout drawer = rootView.findViewById(R.id.drawer_layout);
//
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
//                (Activity) context, drawer, toolbar,
//                R.string.navigation_drawer_open,
//                R.string.navigation_drawer_open
//        );
//        drawer.addDrawerListener(toggle);
//        toggle.syncState();

        //NavigationView nav_view = rootView.findViewById(R.id.shop_menu_view);

        //View nav_headerview = nav_view.getHeaderView(0);
       // ImageView header_imge = nav_headerview.findViewById(R.id.header_imge);
       // TextView header_text= nav_headerview.findViewById(R.id.header_text);

        //Glide.with(context).load(CommonVal.loginInfo.getMember_filepath()).into(header_imge);
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

                }
                if(tab.getPosition() == 1){

                }
                if(tab.getPosition() == 2){

                }
                if(tab.getPosition() == 3){

                }
                if(tab.getPosition() == 4){

                }
                if(tab.getPosition() == 5){

                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        }); //tab_layout

        return rootView;
    }
}