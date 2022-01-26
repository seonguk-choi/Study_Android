package com.example.safing.fragment;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.safing.DTO.Product_Cart_RecDTO;
import com.example.safing.DTO.Product_DetailDTO;
import com.example.safing.R;
import com.example.safing.activity.MainActivity;
import com.example.safing.adapter.Producdt_Detail_Apdater;
import com.example.safing.adapter.Product_Cart_Rec_Adapter;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;


public class Product_Purchase_Fragment extends Fragment {
    boolean isClick1 = false;
    boolean isClick2 = false;
    boolean isClick3 = false;
    boolean isClick4 = false;
    Context context;
    TabLayout product_purchase_tab1;
    MainActivity mainActivity = new MainActivity();
    LinearLayoutManager manager;
    Toolbar toolbar;
    Animation rotate;
    NavigationView product_purchase_view;
    RecyclerView product_purchase_rec1;
    ImageView product_purchase_toggle1, product_purchase_toggle2, product_purchase_toggle3, product_purchase_toggle4;
    LinearLayout product_purchase_updown1, product_purchase_updown2, product_purchase_updown3, product_purchase_updown4,product_purchase_updown5, product_purchase_updown6;


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
        product_purchase_rec1 = rootView.findViewById(R.id.product_purchase_rec1);
        product_purchase_toggle1 = rootView.findViewById(R.id.product_purchase_toggle1);
        product_purchase_toggle2 = rootView.findViewById(R.id.product_purchase_toggle2);
        product_purchase_toggle3 = rootView.findViewById(R.id.product_purchase_toggle3);
        product_purchase_toggle4 = rootView.findViewById(R.id.product_purchase_toggle4);
        product_purchase_updown1 = rootView.findViewById(R.id.product_purchase_updown1);
        product_purchase_updown2 = rootView.findViewById(R.id.product_purchase_updown2);
        product_purchase_updown3 = rootView.findViewById(R.id.product_purchase_updown3);
        product_purchase_updown4 = rootView.findViewById(R.id.product_purchase_updown4);
        product_purchase_updown5 = rootView.findViewById(R.id.product_purchase_updown5);
        product_purchase_updown6 = rootView.findViewById(R.id.product_purchase_updown6);

        rotate = AnimationUtils.loadAnimation(context, R.anim.imge_rotate_deg180);
        mainActivity = (MainActivity) getActivity();

        //========= 토글 기능 =============
        toggleBtn(product_purchase_toggle1, rotate, product_purchase_updown1, isClick1);
        toggleBtn(product_purchase_toggle2, rotate, product_purchase_updown2, isClick2);
        toggleBtn(product_purchase_toggle3, rotate, product_purchase_updown3, isClick3);
        toggleBtn(product_purchase_toggle4, rotate, product_purchase_updown4, isClick4);


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



        setRec1();


        return rootView;
    }
    public void changeFragment(Fragment fragment){
        mainActivity.getSupportFragmentManager().beginTransaction().replace(R.id.product_purchase_container , fragment).commit();
    }

    public void setRec1(){
        manager = new LinearLayoutManager(context, RecyclerView.VERTICAL, false);
        ArrayList<Product_Cart_RecDTO> list = new ArrayList<>();

        product_purchase_rec1.setLayoutManager(manager);
        Product_Cart_Rec_Adapter adapter_rec1 = new Product_Cart_Rec_Adapter(context);
        product_purchase_rec1.setAdapter(adapter_rec1);
    }

    public void toggleBtn(ImageView toggle, Animation rotate, LinearLayout linear, boolean isClick){
        toggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isClick == true){
                    toggle.startAnimation(rotate);
                    linear.setVisibility(View.GONE);
                } else {
                    toggle.startAnimation(rotate);
                    linear.setVisibility(View.VISIBLE);
                }
            }
        });
    }


}