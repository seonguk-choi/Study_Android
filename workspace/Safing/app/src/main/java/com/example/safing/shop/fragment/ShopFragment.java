package com.example.safing.shop.fragment;

import static com.example.safing.async.CommonAsk.FILE_PATH;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.bumptech.glide.Glide;
import com.example.safing.mypage.VO.MemberVO;
import com.example.safing.async.CommonVal;
import com.example.safing.shop.DAO.ShopDAO;
import com.example.safing.R;
import com.example.safing.MainActivity;
import com.example.safing.shop.VO.ProductVO;
import com.example.safing.shop.VO.Product_PackageVO;
import com.example.safing.shop.adapter.Shop_Rec_Adapter;
import com.example.safing.shop.adapter.Shop_Package_Apdater;
import com.example.safing.async.OnItemClick_Package_Listener;
import com.example.safing.async.OnItemClick_Product_Listener;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class ShopFragment extends Fragment{
    Context context;
    TabLayout tab_layout;
    Toolbar toolbar;
    TextView shop_tv1;
    RecyclerView shop_rec1, shop_rec2;
    LinearLayoutManager manager;
    SwipeRefreshLayout swipe;
    NavigationView shop_view;
    MainActivity mainActivity = new MainActivity();
    Shop_Package_Apdater adapter_rec1;
    Shop_Rec_Adapter adapter_rec2;
    String query = "감성용품";
    ShopDAO dao = new ShopDAO();

    public ShopFragment(Context context){
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_shop, container, false);

        MemberVO member = new MemberVO();
        member.setMember_id("in2thefree");
        CommonVal.loginInfo = member;

        tab_layout = rootView.findViewById(R.id.shop_tab);
        shop_rec1 = rootView.findViewById(R.id.shop_rec1);
        shop_rec2 = rootView.findViewById(R.id.shop_rec2);
        toolbar = rootView.findViewById(R.id.toolbar);
        swipe = rootView.findViewById(R.id.spot_swipe);
        shop_view = rootView.findViewById(R.id.shop_view);
        shop_tv1 = rootView.findViewById(R.id.shop_tv1);

        mainActivity = (MainActivity) getActivity();

        setRec1();
        setRec2(query);
        shop_tv1.setText("검색상품 #"+query);

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

        if(CommonVal.loginInfo != null){
            Glide.with(context).load(FILE_PATH + CommonVal.loginInfo.getMember_filepath()).into(header_imge);
            header_text.setText(CommonVal.loginInfo.getMember_id());
        }

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
                    query = tab.getText()+"";
                    shop_tv1.setText("검색상품 #"+query);
                    setRec2(query);
                }
                if(tab.getPosition() == 1){
                    query = tab.getText()+"";
                    shop_tv1.setText("검색상품 #"+query);
                    setRec2(query);
                }
                if(tab.getPosition() == 2){
                    query = tab.getText()+"";
                    shop_tv1.setText("검색상품 #"+query);
                    setRec2(query);
                }
                if(tab.getPosition() == 3){
                    query = tab.getText()+"";
                    shop_tv1.setText("검색상품 #"+query);
                    setRec2(query);
                }
                if(tab.getPosition() == 4){
                    query = tab.getText()+"";
                    shop_tv1.setText("검색상품 #"+query);
                    setRec2(query);
                }
                if(tab.getPosition() == 5){
                    query = tab.getText()+"";
                    shop_tv1.setText("검색상품 #"+query);
                    setRec2(query);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        }); //tab_layout

        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                setRec2(query);
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
        ArrayList<Product_PackageVO> list = dao.package_list();

        manager = new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false);

        shop_rec1.setLayoutManager(manager);
        adapter_rec1 = new Shop_Package_Apdater(context, list);
        shop_rec1.setAdapter(adapter_rec1);

        adapter_rec1.setOnItemClickListener(new OnItemClick_Package_Listener() {
            @Override
            public void onItemClick_package(Shop_Package_Apdater.ViewHolder holderm, View view, int position) {
                mainActivity.changeFragment(new Product_Package_Fragment(context, list.get(position).getPackage_num()));
            }
        });
    }

    public void setRec2(String query){
        ArrayList<ProductVO> list = dao.product_list(query);

        manager = new LinearLayoutManager(context, RecyclerView.VERTICAL, false);

        shop_rec2.setLayoutManager(manager);
        adapter_rec2 = new Shop_Rec_Adapter(context, list);
        shop_rec2.setAdapter(adapter_rec2);

        adapter_rec2.setOnItemClickListener(new OnItemClick_Product_Listener() {
            @Override
            public void onItemClick_product(Shop_Rec_Adapter.ViewHolder holderm, View view, int position) {

                mainActivity.changeFragment(new Product_Fragment(context, list.get(position).getProduct_num()));
            }
        });
    }
}