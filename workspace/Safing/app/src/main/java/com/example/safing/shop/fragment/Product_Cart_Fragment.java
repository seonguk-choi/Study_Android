package com.example.safing.shop.fragment;

import static com.example.safing.async.CommonAsk.FILE_PATH;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.safing.R;
import com.example.safing.MainActivity;
import com.example.safing.async.CommonVal;
import com.example.safing.async.OnItemClick_Cart_Listener;
import com.example.safing.shop.DAO.ShopDAO;
import com.example.safing.shop.VO.CartVO;
import com.example.safing.shop.adapter.Product_Cart_Rec_Adapter;
import com.google.android.material.navigation.NavigationView;

import java.text.NumberFormat;
import java.util.ArrayList;

public class Product_Cart_Fragment extends Fragment {
    Context context;
    Toolbar toolbar;
    CheckBox product_cart_box1;
    Button product_cart_btn1, product_cart_btn2;
    TextView product_cart_tv1, product_cart_tv2, product_cart_tv3;
    RecyclerView product_cart_rec1;
    LinearLayoutManager manager;
    NavigationView product_cart_view;


    MainActivity mainActivity = new MainActivity();
    Product_Cart_Rec_Adapter adapter_rec1;
    ShopDAO dao = new ShopDAO();
    ArrayList<CartVO> list = new ArrayList<>();
    int price = 0;

    public Product_Cart_Fragment(Context context){
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_product_cart, container, false);

        toolbar = rootView.findViewById(R.id.product_cart_toolbar);
        product_cart_view = rootView.findViewById(R.id.product_cart_view);
        product_cart_rec1 = rootView.findViewById(R.id.product_cart_rec1);
        product_cart_box1 = rootView.findViewById(R.id.product_cart_box1);
        product_cart_btn1 = rootView.findViewById(R.id.product_cart_btn1);
        product_cart_btn2 = rootView.findViewById(R.id.product_cart_btn2);
        product_cart_tv1 = rootView.findViewById(R.id.product_cart_tv1);
        product_cart_tv2 = rootView.findViewById(R.id.product_cart_tv2);
        product_cart_tv3 = rootView.findViewById(R.id.product_cart_tv3);


        mainActivity = (MainActivity) getActivity();

        //========= ????????? ?????? ==============

        DrawerLayout drawer = rootView.findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                (Activity) context, drawer, toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_open
        );
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        View nav_headerview = product_cart_view.getHeaderView(0);
        ImageView header_imge = nav_headerview.findViewById(R.id.header_imge);
        TextView header_text= nav_headerview.findViewById(R.id.header_text);

        if(CommonVal.loginInfo != null){
            Glide.with(context).load(FILE_PATH + CommonVal.loginInfo.getMember_filepath()).into(header_imge);
            header_text.setText(CommonVal.loginInfo.getMember_id());
        }

        //============= navigation view ??????=====

        product_cart_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
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

        //============= ???????????? ==================

        product_cart_box1.setChecked(true);

        product_cart_box1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                changeCheck(isChecked);
            }
        });

        //============= ???????????? ==================
        product_cart_btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("???????????? ??????");
                builder.setMessage("???????????? ?????????\n????????????????????????? ");
                builder.setIcon(R.drawable.question1);

                builder.setPositiveButton("???", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        deleteCheck();
                    }
                });

                builder.setNegativeButton("?????????", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });

                AlertDialog dialog  = builder.create();
                dialog.show();
            }
        });


        //============= ???????????? ?????? ========
        product_cart_btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(CommonVal.loginInfo.getMember_id().isEmpty()){
                    android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(context);
                    builder.setTitle("????????? ??????");
                    builder.setMessage("???????????? ???????????????.");
                    builder.setIcon(R.drawable.question1);
                    builder.setPositiveButton("???????????????", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //  mainActivity.changeFragment(new LoginFragment(context));
                        }
                    });
                    builder.setPositiveButton("????????????", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(context, "????????????", Toast.LENGTH_SHORT).show();

                        }
                    });
                } else {
                    mainActivity.changeFragment(new Product_Purchase_Fragment(context, list));
                }
            }
        });


        setRec();
        setPrice();



        return rootView;
    }

    public void setRec(){
        list = dao.cart_list(CommonVal.loginInfo.getMember_id());
        adapter_rec1 = new Product_Cart_Rec_Adapter(context, list,this);

        manager = new LinearLayoutManager(context, RecyclerView.VERTICAL, false);
        product_cart_rec1.setLayoutManager(manager);
        product_cart_rec1.setAdapter(adapter_rec1);

        adapter_rec1.setOnItemClickListener(new OnItemClick_Cart_Listener() {
            @Override
            public void onItemClick_Cart(Product_Cart_Rec_Adapter.ViewHolder holderm, View view, int position) {
                if(list.get(position).getProduct_num()> 0){
                    mainActivity.changeFragment(new Product_Fragment(context, list.get(position).getProduct_num()));
                } else {
                    mainActivity.changeFragment(new Product_Package_Fragment(context, list.get(position).getPackage_num()));
                }
            }
        });
    }

    public void changeCheck(boolean isChecked){
        for (int i = 0; i < adapter_rec1.viewHolders.size(); i++) {
            adapter_rec1.viewHolders.get(i).item_product_cart_rec_box.setChecked(isChecked);
        }
        changePrice();

    }

    public void deleteCheck(){
        for (int i = 0; i < adapter_rec1.viewHolders.size(); i++) {
            if(adapter_rec1.viewHolders.get(i).item_product_cart_rec_box.isChecked()){
                adapter_rec1.delDto(i);
                dao.delete_cart(list.get(i).getCart_num());
                adapter_rec1.notifyDataSetChanged();
            }
        }
    }

    public void changePrice(){
        int courier = 0;
        int priceSum = 0;

        for (int i = 0 ; i < list.size() ; i++) {
            if(adapter_rec1.viewHolders.get(i).item_product_cart_rec_box.isChecked()){
                priceSum += (list.get(i).getProduct_price());
            }
        }

        if (priceSum == 0 ){
            courier = 0;
        } else if(priceSum < 100000){
            courier = 5000;
        }

        int priceSum2 = priceSum + courier;

        product_cart_tv1.setText(NumberFormat.getInstance().format(priceSum)+"???");
        product_cart_tv2.setText(NumberFormat.getInstance().format(courier)+"???");
        product_cart_tv3.setText(NumberFormat.getInstance().format(priceSum2)+"???");
    }

    public void setPrice(){
        int courier = 0;
        int priceSum = 0;

        for (CartVO vo : list) {
            priceSum += vo.getProduct_price();
        }

        if (priceSum == 0 ){
            courier = 0;
        } else if(priceSum < 100000){
            courier = 5000;
        }

        int priceSum2 = priceSum + courier;

        product_cart_tv1.setText(NumberFormat.getInstance().format(priceSum)+"???");
        product_cart_tv2.setText(NumberFormat.getInstance().format(courier)+"???");
        product_cart_tv3.setText(NumberFormat.getInstance().format(priceSum2)+"???");
    }


}