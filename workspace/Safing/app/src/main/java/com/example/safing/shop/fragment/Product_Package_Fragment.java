package com.example.safing.shop.fragment;

import static com.example.safing.async.CommonAsk.FILE_PATH;

import android.app.Activity;
import android.content.Context;
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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.safing.R;
import com.example.safing.MainActivity;
import com.example.safing.async.CommonVal;
import com.example.safing.shop.DAO.ShopDAO;
import com.example.safing.shop.VO.Product_PackageVO;
import com.example.safing.shop.VO.Product_Package_DetailVO;
import com.example.safing.shop.adapter.Shop_Product_Pager_Adapter;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class Product_Package_Fragment extends Fragment {
    Context context;
    ViewPager2 pager;
    TabLayout shop_product_tab1;
    Toolbar toolbar;
    SwipeRefreshLayout swipe;
    NavigationView shop_product_view;
    MainActivity mainActivity = new MainActivity();
    ShopDAO dao = new ShopDAO();
    String review;
    int package_num = 0;

    public Product_Package_Fragment(Context context, int package_num) {
        this.context = context;
        this.package_num = package_num;
    }

    public Product_Package_Fragment(Context context, int package_num, String review) {
        this.context = context;
        this.review = review;
        this.package_num = package_num;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = (ViewGroup) inflater.inflate(R.layout.fragment_product_package, container, false);

        shop_product_tab1 = rootView.findViewById(R.id.shop_product_tab1);
        pager = rootView.findViewById(R.id.shop_product_Pager);
        toolbar = rootView.findViewById(R.id.shop_product_toolbar);
        swipe = rootView.findViewById(R.id.shop_product_swipe);
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

        if(CommonVal.loginInfo != null){
            Glide.with(context).load(FILE_PATH + CommonVal.loginInfo.getMember_filepath()).into(header_imge);
            header_text.setText(CommonVal.loginInfo.getMember_id());
        }

        //=========패키지 이미지 페이저 ==============
        Product_Package_DetailVO vo = dao.package_detail(package_num);

        Shop_Product_Pager_Adapter adapter_pager1 = new Shop_Product_Pager_Adapter(context, vo.getImagelist());
        pager.setAdapter(adapter_pager1);

        setContent(vo, rootView);

        //========= 탭 기능 ==============

        shop_product_tab1.addTab(shop_product_tab1.newTab().setText("상세정보"));
        shop_product_tab1.addTab(shop_product_tab1.newTab().setText("리뷰"));


        if(("review").equals(review)){
            changeFragment(new Product_Review_Fragment(context, package_num, "package"));
            shop_product_tab1.selectTab(shop_product_tab1.getTabAt(1));
        } else {
            changeFragment(new Product_Pacakage_Detail_Fragment(context, package_num, vo.getPackage_name(), vo.getImagelist()));
        }

        shop_product_tab1.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                if(position == 0){
                    Toast.makeText(context, "상세정보", Toast.LENGTH_SHORT).show();
                    changeFragment(new Product_Pacakage_Detail_Fragment(context, package_num, vo.getPackage_name(), vo.getImagelist()));
                } else {
                    Toast.makeText(context, "리뷰", Toast.LENGTH_SHORT).show();
                    changeFragment(new Product_Review_Fragment(context, package_num, "package"));
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
        mainActivity.getSupportFragmentManager().beginTransaction().replace(R.id.shop_container1 , fragment).commit();
    }

    public void setContent(Product_Package_DetailVO vo, View rootView){
       TextView product_tv1, product_tv2, product_tv3, product_package_tv1, product_package_tv2, product_package_tv3,
               product_package_tv4, product_package_tv5, product_package_tv6;

        product_tv1 = rootView.findViewById(R.id.product_tv1);
        product_tv2 = rootView.findViewById(R.id.product_tv2);
        product_tv3 = rootView.findViewById(R.id.product_tv3);
        product_package_tv1 = rootView.findViewById(R.id.product_package_tv1);
        product_package_tv2 = rootView.findViewById(R.id.product_package_tv2);
        product_package_tv3 = rootView.findViewById(R.id.product_package_tv3);
        product_package_tv4 = rootView.findViewById(R.id.product_package_tv4);
        product_package_tv5 = rootView.findViewById(R.id.product_package_tv5);
        product_package_tv6 = rootView.findViewById(R.id.product_package_tv6);

        product_tv1.setText(vo.getPackage_name());
        product_tv2.setText(vo.getRating()+"/5");
        product_tv3.setText("("+vo.getRe_count()+")");
        product_package_tv1.setText(vo.getKindlist().get(0));
        product_package_tv2.setText(vo.getKindlist().get(1));
        product_package_tv3.setText(vo.getKindlist().get(2));
        product_package_tv4.setText(vo.getKindlist().get(3));
        product_package_tv5.setText(vo.getKindlist().get(4));
        product_package_tv6.setText(vo.getKindlist().get(5));


    }
}