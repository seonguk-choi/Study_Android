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
import com.example.safing.async.OnItemClick_Product_Package_Detail_Listener;
import com.example.safing.shop.DAO.ShopDAO;
import com.example.safing.shop.VO.CartVO;
import com.example.safing.shop.VO.Order_Detail_CntVO;
import com.example.safing.shop.VO.Product_DetailVO;
import com.example.safing.shop.adapter.Product_Pakcage_Detail_Apdater;

import java.text.NumberFormat;
import java.util.ArrayList;


public class Product_Pacakage_Detail_Fragment extends Fragment {
    Context context;
    RecyclerView product_detail_rec;
    LinearLayoutManager manager;
    ImageView product_detail_img;
    Button product_detail_btn1, product_detail_btn2;
    TextView product_detail_tv1, product_detail_tv2, product_detail_tv3;
    ShopDAO dao = new ShopDAO();
    ArrayList<Product_DetailVO> list = new ArrayList<>();
    MainActivity mainActivity = new MainActivity();
    ArrayList<String> imageList = new ArrayList<>();

    int sendPriceSum = 0;
    int packge_num = 0;
    String packge_name = "";

    public Product_Pacakage_Detail_Fragment(Context context, int packge_num, String packge_name, ArrayList<String> imageList){
        this.context = context;
        this.packge_num = packge_num;
        this.packge_name = packge_name;
        this.imageList = imageList;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_product_pacakage_detail, container, false);

        product_detail_rec = rootView.findViewById(R.id.product_detail_rec);
        product_detail_btn1 = rootView.findViewById(R.id.product_detail_btn1);
        product_detail_btn2 = rootView.findViewById(R.id.product_detail_btn2);
        product_detail_img = rootView.findViewById(R.id.product_detail_img);
        product_detail_tv1 = rootView.findViewById(R.id.product_detail_tv1);
        product_detail_tv2 = rootView.findViewById(R.id.product_detail_tv2);
        product_detail_tv3 = rootView.findViewById(R.id.product_detail_tv3);

        mainActivity = (MainActivity) getActivity();


        list = dao.product_details_page_pack(packge_num);
        Glide.with(context).load(FILE_PATH + list.get(0).getFile_path_info()).into(product_detail_img);
        setPrice(list);
        setRec(list);

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
                            //   mainActivity.changeFragment(new LoginFragment(context));
                        }
                    });
                    builder.setPositiveButton("취소", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(context, "취소", Toast.LENGTH_SHORT).show();

                        }
                    });
                } else {
                    int result = dao.insert_cart_pack(list, packge_num, sendPriceSum);
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
                    ArrayList<Order_Detail_CntVO> packCntList = new ArrayList<>();

                    cartList.add(new CartVO());
                    cartList.get(0).setPackage_num(packge_num);
                    cartList.get(0).setPackage_name(packge_name);
                    cartList.get(0).setProduct_price(sendPriceSum);
                    cartList.get(0).setOrder_count(1);
                    cartList.get(0).setFile_path(imageList.get(0));

                    for(int i = 0 ; i < list.size() ; i ++) {
                        packCntList.add(new Order_Detail_CntVO());
                        packCntList.get(i).setPackage_num(packge_num);
                        packCntList.get(i).setProduct_num(list.get(i).getProduct_num());
                        packCntList.get(i).setOrder_count(list.get(i).getOrder_count());
                    }

                    mainActivity.changeFragment(new Product_Purchase_Fragment(context, cartList, packCntList));
                }
            }
        });

        return rootView;
    }

    public void setRec(ArrayList<Product_DetailVO> list){
        manager = new LinearLayoutManager(context, RecyclerView.VERTICAL, false);
        product_detail_rec.setLayoutManager(manager);
        Product_Pakcage_Detail_Apdater adapter_rec = new Product_Pakcage_Detail_Apdater(context, list, Product_Pacakage_Detail_Fragment.this);
        product_detail_rec.setAdapter(adapter_rec);

        adapter_rec.setOnItemClickListener(new OnItemClick_Product_Package_Detail_Listener() {
            @Override
            public void onItemClick_detail(Product_Pakcage_Detail_Apdater.ViewHolder holderm, View view, int position) {
                mainActivity.changeFragment(new Product_Fragment(context, list.get(position).getProduct_num()));
            }
        });

    }

    public void changePrice(int cnt, int position){
        int priceSum = 0;
        int courier = 0;

        list.get(position).setOrder_count(cnt);

        for (Product_DetailVO vo: list) {
            priceSum += (vo.getProduct_price()* vo.getOrder_count());
        }

        if (priceSum == 0 ){
            courier = 0;
        } else if(priceSum < 100000){
            courier = 5000;
        }

        sendPriceSum = priceSum;
        int priceSum2 = priceSum + courier;

        product_detail_tv1.setText(NumberFormat.getInstance().format(priceSum)+"원");
        product_detail_tv2.setText(NumberFormat.getInstance().format(courier)+"원");
        product_detail_tv3.setText(NumberFormat.getInstance().format(priceSum2)+"원");
    }

    public void setPrice(ArrayList<Product_DetailVO> list){
        int priceSum = 0;
        int courier = 0;

        for (Product_DetailVO vo: list) {
            vo.setOrder_count(1);
            priceSum += (vo.getProduct_price()* vo.getOrder_count());
        }

        if (priceSum == 0 ){
            courier = 0;
        } else if(priceSum < 100000){
            courier = 5000;
        }

        sendPriceSum = priceSum;
        int priceSum2 = priceSum + courier;

        product_detail_tv1.setText(NumberFormat.getInstance().format(priceSum)+"원");
        product_detail_tv2.setText(NumberFormat.getInstance().format(courier)+"원");
        product_detail_tv3.setText(NumberFormat.getInstance().format(priceSum2)+"원");
    }

}