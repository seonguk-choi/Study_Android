package com.example.safing.shop.fragment;

import static com.example.safing.async.CommonAsk.FILE_PATH;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;


import androidx.fragment.app.Fragment;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.safing.MainActivity;
import com.example.safing.R;
import com.example.safing.async.CommonVal;
import com.example.safing.async.OnItemClick_Product_Detail_Listener;
import com.example.safing.shop.DAO.ShopDAO;
import com.example.safing.shop.VO.CartVO;
import com.example.safing.shop.VO.Product_DetailVO;
import com.example.safing.shop.adapter.Product_Detail_Apdater;

import java.text.NumberFormat;
import java.util.ArrayList;

public class Product_Detail_Fragment extends Fragment {
    Context context;
    RecyclerView product_detail_rec;
    LinearLayoutManager manager;
    ImageView product_detail_img;
    Button product_detail_btn1, product_detail_btn2;
    TextView product_detail_tv1, product_detail_tv2, product_detail_tv3;
    ShopDAO dao = new ShopDAO();
    Product_DetailVO vo = new Product_DetailVO();
    MainActivity mainActivity = new MainActivity();
    int product_num = 0;

    public Product_Detail_Fragment(Context context, Product_DetailVO vo){
        this.context = context;
        this.vo = vo;
        this.product_num = vo.getProduct_num();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_detail_product, container, false);

        product_detail_rec = rootView.findViewById(R.id.product_detail_rec);
        product_detail_btn1 = rootView.findViewById(R.id.product_detail_btn1);
        product_detail_btn2 = rootView.findViewById(R.id.product_detail_btn2);
        product_detail_img = rootView.findViewById(R.id.product_detail_img);
        product_detail_tv1 = rootView.findViewById(R.id.product_detail_tv1);
        product_detail_tv2 = rootView.findViewById(R.id.product_detail_tv2);
        product_detail_tv3 = rootView.findViewById(R.id.product_detail_tv3);

        mainActivity = (MainActivity) getActivity();

        Glide.with(context).load(FILE_PATH + vo.getFile_path_info()).into( product_detail_img);
        setRec(vo);

        //============= 장바구니 버튼 =====
        product_detail_btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(CommonVal.loginInfo.getMember_id().isEmpty()){
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle("로그인 확인");
                    builder.setMessage("로그인이 필요합니다.");
                    builder.setIcon(R.drawable.question1);
                    builder.setPositiveButton("로그인하기", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                         //mainActivity.changeFragment(new LoginFragment(context));
                        }
                    });
                    builder.setPositiveButton("취소", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
                } else {
                    int result = dao.insert_cart_pro(vo);
                    if(result > 0){
                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        builder.setTitle("장바구니 담기");
                        builder.setMessage("장바구니에 담았습니다.\n확인하시겠습니까?");
                        builder.setIcon(R.drawable.question1);

                        builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                mainActivity.changeFragment(new Product_Cart_Fragment(context));
                            }
                        });

                        builder.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });

                        AlertDialog dialog  = builder.create();
                        dialog.show();

                    } else {
                        Toast.makeText(context, "장바구니 담기 실패", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        //============= 구매하기 버튼 ========
        product_detail_btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(CommonVal.loginInfo.getMember_id().isEmpty()){
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle("로그인 확인");
                    builder.setMessage("로그인이 필요합니다.");
                    builder.setIcon(R.drawable.question1);
                    builder.setPositiveButton("로그인하기", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                          //  mainActivity.changeFragment(new LoginFragment(context));
                        }
                    });
                    builder.setPositiveButton("쇼핑하기", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(context, "쇼핑하기", Toast.LENGTH_SHORT).show();

                        }
                    });
                } else {
                    ArrayList<CartVO> cartList = new ArrayList<>();
                    cartList.add(new CartVO());
                    cartList.get(0).setProduct_num(vo.getProduct_num());
                    cartList.get(0).setProduct_name(vo.getProduct_name());
                    cartList.get(0).setProduct_price(vo.getProduct_price()*vo.getOrder_count());
                    cartList.get(0).setOrder_count(vo.getOrder_count());
                    cartList.get(0).setFile_path(vo.getImagelist().get(0));

                    mainActivity.changeFragment(new Product_Purchase_Fragment(context, cartList));
                }
            }
        });


        return rootView;
    }
    public void setRec(Product_DetailVO vo){
        manager = new LinearLayoutManager(context, RecyclerView.VERTICAL, false);

        product_detail_rec.setLayoutManager(manager);
        Product_Detail_Apdater adapter_rec = new Product_Detail_Apdater(context, vo, Product_Detail_Fragment.this);
        product_detail_rec.setAdapter(adapter_rec);

        adapter_rec.setOnItemClickListener(new OnItemClick_Product_Detail_Listener() {
            @Override
            public void onItemClick_detail(Product_Detail_Apdater.ViewHolder holderm, View view, int position) {
                if(product_num == vo.getProduct_num()){
                    Toast.makeText(context, "현재 선택한 페이지입니다", Toast.LENGTH_SHORT).show();
                } else {
                    mainActivity.changeFragment(new Product_Fragment(context, vo.getProduct_num()));
                }
            }
        });
    }

    public void changePrice(int cnt){
        this.vo.setOrder_count(cnt);

        int price = vo.getProduct_price()* vo.getOrder_count();
        int courier = 0;

        if(price == 0) {
            courier = 0;
        } else if(price < 100000){
            courier = 5000;
        }

        int priceSum = price + courier;

        product_detail_tv1.setText(NumberFormat.getInstance().format(price)+"원");
        product_detail_tv2.setText(NumberFormat.getInstance().format(courier)+"원");
        product_detail_tv3.setText(NumberFormat.getInstance().format(priceSum)+"원");
    }

}

