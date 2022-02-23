package com.example.safing.shop.fragment;

import static com.example.safing.async.CommonAsk.FILE_PATH;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.safing.R;
import com.example.safing.MainActivity;
import com.example.safing.async.CommonVal;
import com.example.safing.shop.DAO.ShopDAO;
import com.example.safing.shop.VO.AddressVO;
import com.example.safing.shop.VO.CartVO;
import com.example.safing.shop.VO.Order_Detail_CntVO;
import com.example.safing.shop.VO.Product_DetailVO;
import com.example.safing.shop.activity.Purchase_Result_Activity;
import com.example.safing.shop.adapter.Product_Cart_Rec_Adapter;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import java.text.NumberFormat;
import java.util.ArrayList;


public class Product_Purchase_Fragment extends Fragment {

    public boolean isClick1 = false;
    public boolean isClick2 = false;
    public boolean isClick3 = false;
    public  boolean isClick4 = false;

    Context context;
    Product_Cart_Rec_Adapter adapter_rec1;
    TabLayout product_purchase_tab1;
    MainActivity mainActivity = new MainActivity();
    LinearLayoutManager manager;
    Toolbar toolbar;
    NavigationView product_purchase_view;
    RecyclerView product_purchase_rec1;
    ImageView product_purchase_toggle1, product_purchase_toggle2, product_purchase_toggle3, product_purchase_toggle4;
    LinearLayout product_purchase_updown1, product_purchase_updown2, product_purchase_updown3, product_purchase_updown4,product_purchase_updown5, product_purchase_updown6;
    CheckBox product_purchase_box1, product_purchase_box2, product_purchase_box3, product_purchase_box4, product_purchase_box5, product_purchase_box6;
    EditText product_purchase_orderer_tv1, product_purchase_orderer_tv2, product_purchase_orderer_tv3, product_purchase_orderer_tv4;
    TextView product_purchase_tv1, product_purchase_tv2, product_purchase_tv3;
    Button product_purchase_btn1;

    ShopDAO dao = new ShopDAO();
    Product_DetailVO vo = new Product_DetailVO();
    ArrayList<CartVO> cartList = new ArrayList<>();
    ArrayList<Order_Detail_CntVO> packCntList = new ArrayList<>();
    public AddressVO addressVo = new AddressVO();
    boolean purchase_chece = false;

    public Product_Purchase_Fragment(Context context, ArrayList<CartVO> cartList) {
        this.context = context;
        this.cartList = cartList;
    }

    public Product_Purchase_Fragment(Context context, ArrayList<CartVO> cartList, ArrayList<Order_Detail_CntVO> packCntList) {
        this.context = context;
        this.cartList = cartList;
        this.packCntList = packCntList;
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
        product_purchase_box1 = rootView.findViewById(R.id.product_purchase_box1);
        product_purchase_box2 = rootView.findViewById(R.id.product_purchase_box2);
        product_purchase_box3 = rootView.findViewById(R.id.product_purchase_box3);
        product_purchase_box4 = rootView.findViewById(R.id.product_purchase_box4);
        product_purchase_box5 = rootView.findViewById(R.id.product_purchase_box5);
        product_purchase_box6 = rootView.findViewById(R.id.product_purchase_box6);
        product_purchase_btn1 = rootView.findViewById(R.id.product_purchase_btn1);
        product_purchase_orderer_tv1 = rootView.findViewById(R.id.product_purchase_orderer_tv1);
        product_purchase_orderer_tv2 = rootView.findViewById(R.id.product_purchase_orderer_tv2);
        product_purchase_orderer_tv3 = rootView.findViewById(R.id.product_purchase_orderer_tv3);
        product_purchase_orderer_tv4 = rootView.findViewById(R.id.product_purchase_orderer_tv4);
        product_purchase_tv1 = rootView.findViewById(R.id.product_purchase_tv1);
        product_purchase_tv2 = rootView.findViewById(R.id.product_purchase_tv2);
        product_purchase_tv3 = rootView.findViewById(R.id.product_purchase_tv3);

        mainActivity = (MainActivity) getActivity();

        //========= 정보입력 체크 리스트 =============

        ArrayList<EditText> orderers = new ArrayList<>();
        orderers.add(product_purchase_orderer_tv1);
        orderers.add(product_purchase_orderer_tv2);
        orderers.add(product_purchase_orderer_tv3);
        orderers.add(product_purchase_orderer_tv4);

        //========= 토글 기능 =============

        ArrayList<Boolean> clickList = new ArrayList<>();
        clickList.add(new Boolean(false));
        clickList.add(new Boolean(false));
        clickList.add(new Boolean(false));
        clickList.add(new Boolean(false));

        toggleBtn(product_purchase_toggle1, product_purchase_updown1, clickList, 0);
        toggleBtn(product_purchase_toggle2, product_purchase_updown2, clickList, 1);
        toggleBtn(product_purchase_toggle3, product_purchase_updown3, clickList, 2);
        toggleBtn(product_purchase_toggle4, product_purchase_updown4, clickList, 3);


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


        if(CommonVal.loginInfo != null){
            Glide.with(context).load(FILE_PATH + CommonVal.loginInfo.getMember_filepath()).into(header_imge);
            header_text.setText(CommonVal.loginInfo.getMember_id());
        }


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


        //=============== 주소 ======================

        product_purchase_tab1.addTab(product_purchase_tab1.newTab().setText("기본 주소"));
        product_purchase_tab1.addTab(product_purchase_tab1.newTab().setText("주소 선택"));

        if(addressVo.getAddr_basic() == null){
            changeFragment(new Address_Default_Fragment(context, Product_Purchase_Fragment.this));
        } else {
            changeFragment(new Address_Default_Fragment(context, addressVo, Product_Purchase_Fragment.this));
            product_purchase_tab1.selectTab(product_purchase_tab1.getTabAt(0));

        }

        product_purchase_tab1.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                if(position == 0){
                    if(addressVo.getAddr_basic() == null){
                        changeFragment(new Address_Default_Fragment(context, Product_Purchase_Fragment.this));
                    } else {
                        changeFragment(new Address_Default_Fragment(context, addressVo, Product_Purchase_Fragment.this));
                    }
                } else {
                    changeFragment(new Address_Repogitory_Fragment(context , Product_Purchase_Fragment.this));
                }
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

        //=============== 주문자 ======================

        /*if(CommonVal.loginInfo.getMember_id() != null){
            product_purchase_orderer_tv1.setText(CommonVal.loginInfo.getMember_name());
            String[] phone = CommonVal.loginInfo.getMember_phone().split("-");

            product_purchase_orderer_tv2.setText(phone[0]);
            product_purchase_orderer_tv3.setText(phone[1]);
            product_purchase_orderer_tv3.setText(phone[2]);
        }*/


        //=============== 주문상품 ======================

        setRec1();

        //=============== 결제수단 ======================

        product_purchase_box1.setChecked(true);
        product_purchase_updown5.setVisibility(View.VISIBLE);

        product_purchase_box1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked == true) {
                    product_purchase_updown5.setVisibility(View.VISIBLE);
                    product_purchase_updown6.setVisibility(View.GONE);
                    product_purchase_box2.setChecked(false);
                }
            }
        });

        product_purchase_box2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked == true){
                    product_purchase_updown6.setVisibility(View.VISIBLE);
                    product_purchase_updown5.setVisibility(View.GONE);
                    product_purchase_box1.setChecked(false);
                }
            }
        });

        //=============== 결제 동의 =====================

        product_purchase_box6.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    purchase_chece = true;
                } else {
                    purchase_chece = false;
                }
            }
        });

        //=============== 결제하기 =====================
        product_purchase_btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(("0원").equals(product_purchase_tv1.getText()+"")){
                    Toast.makeText(context, "주문상품을 체크해주세요.", Toast.LENGTH_SHORT).show();
                    product_purchase_toggle3.requestFocus();
                }else if(product_purchase_box2.isChecked()) {
                    for(EditText edt : orderers){
                        if((edt.getText()+"").trim().length()<1){
                            Toast.makeText(context, "주문자 정보를 입력해주세요", Toast.LENGTH_SHORT).show();
                            edt.requestFocus();
                        }
                    }
                    product_purchase_toggle4.requestFocus();
                }else if(!purchase_chece){
                    Toast.makeText(context, "결제동의를 해주세요.", Toast.LENGTH_SHORT).show();
                    product_purchase_box6.requestFocus();
                } else {
                    int result = 0;
                    if(cartList.get(0).getCart_num()> 0){
                        result = dao.insert_order_ing_cart(cartList, addressVo);
                    } else if(packCntList.size() > 0){
                        result = dao.insert_order_ing_pack(cartList.get(0), packCntList, addressVo);
                    } else {
                        result = dao.insert_order_ing_pro(cartList.get(0), addressVo);
                    }
                    if(result >0){
                        Intent intent = new Intent(context, Purchase_Result_Activity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(context, "구매실패", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });

        setRec1();
        setPrice();

        return rootView;
    }


    public void changeFragment(Fragment fragment){
        mainActivity.getSupportFragmentManager().beginTransaction().replace(R.id.product_purchase_container , fragment).commit();
    }

    public void setRec1(){
        manager = new LinearLayoutManager(context, RecyclerView.VERTICAL, false);

        product_purchase_rec1.setLayoutManager(manager);
        adapter_rec1 = new Product_Cart_Rec_Adapter(context, cartList, Product_Purchase_Fragment.this);
        product_purchase_rec1.setAdapter(adapter_rec1);
    }

    public void toggleBtn(ImageView toggle, LinearLayout linear, ArrayList<Boolean> clickList, int postion){
        toggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(clickList.get(postion) == true){
                    toggle.setImageResource(R.drawable.down1);
                    linear.setVisibility(View.VISIBLE);
                    clickList.set(postion, false);
                } else {
                    toggle.setImageResource(R.drawable.up1);
                    linear.setVisibility(View.GONE);
                    clickList.set(postion, true);
                }
            }
        });
    }

    public void changePrice(){
        int courier = 0;
        int priceSum = 0;

        for (int i = 0 ; i < cartList.size() ; i++) {
            if(adapter_rec1.viewHolders.get(i).item_product_cart_rec_box.isChecked()){
                priceSum += (cartList.get(i).getProduct_price());
            }
        }

        if (priceSum == 0 ){
            courier = 0;
        } else if(priceSum < 100000){
            courier = 5000;
        }

        int priceSum2 = priceSum + courier;

        product_purchase_tv1.setText(NumberFormat.getInstance().format(priceSum)+"원");
        product_purchase_tv2.setText(NumberFormat.getInstance().format(courier)+"원");
        product_purchase_tv3.setText(NumberFormat.getInstance().format(priceSum2)+"원");
    }

    public void setPrice(){
        int courier = 0;
        int priceSum = 0;

        for (CartVO vo : cartList) {
            priceSum += vo.getProduct_price();
        }

        if (priceSum == 0 ){
            courier = 0;
        } else if(priceSum < 100000){
            courier = 5000;
        }

        int priceSum2 = priceSum + courier;

        product_purchase_tv1.setText(NumberFormat.getInstance().format(priceSum)+"원");
        product_purchase_tv2.setText(NumberFormat.getInstance().format(courier)+"원");
        product_purchase_tv3.setText(NumberFormat.getInstance().format(priceSum2)+"원");
    }

}