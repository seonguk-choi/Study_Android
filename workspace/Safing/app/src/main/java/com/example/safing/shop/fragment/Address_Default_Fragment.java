package com.example.safing.shop.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.example.safing.MainActivity;
import com.example.safing.R;
import com.example.safing.async.CommonVal;
import com.example.safing.shop.DAO.ShopDAO;
import com.example.safing.shop.VO.AddressVO;
import com.example.safing.shop.activity.SearchAddrActivity;


public class Address_Default_Fragment extends Fragment {


    Context context;
    EditText address_defualt_tv1, address_defualt_tv2, address_defualt_tv3, address_defualt_tv4, address_defualt_tv5, address_defualt_tv6, address_defualt_tv7;
    Button address_defualt_btn1;
    CheckBox address_defualt_box1, address_defualt_box2;

    AddressVO vo = new AddressVO();
    AddressVO newVo = new AddressVO();
    ShopDAO dao = new ShopDAO();
    Product_Purchase_Fragment fragment;
    private final int SEARCH_ADDR_CODE = 1001;

    public Address_Default_Fragment(Context context, Product_Purchase_Fragment fragment){
        this.context = context;
        this.fragment = fragment;
    }

    public Address_Default_Fragment(Context context, AddressVO vo, Product_Purchase_Fragment fragment){
        this.context = context;
        this.vo = vo;
        this.fragment = fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_address_default, container, false);

        address_defualt_tv1 = rootView.findViewById(R.id.address_defualt_tv1);
        address_defualt_tv2 = rootView.findViewById(R.id.address_defualt_tv2);
        address_defualt_tv3 = rootView.findViewById(R.id.address_defualt_tv3);
        address_defualt_tv4 = rootView.findViewById(R.id.address_defualt_tv4);
        address_defualt_tv5 = rootView.findViewById(R.id.address_defualt_tv5);
        address_defualt_tv6 = rootView.findViewById(R.id.address_defualt_tv6);
        address_defualt_tv7 = rootView.findViewById(R.id.address_defualt_tv7);
        address_defualt_btn1 = rootView.findViewById(R.id.address_defualt_btn1);
        address_defualt_box1 = rootView.findViewById(R.id.address_defualt_box1);
        address_defualt_box2 = rootView.findViewById(R.id.address_defualt_box2);


        //=============== 주소찾기 ===============

        address_defualt_btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        //=============== layout ===============
        if(vo.getAddr_basic() == null){
            vo = dao.default_addrss(CommonVal.loginInfo.getMember_id());
        }

        if(!("null").equals(vo.getReceiver_name())){
            address_defualt_tv1.setText(vo.getReceiver_name());

            String[] phone = vo.getReceiver_phone().split("-");

            address_defualt_tv2.setText(phone[0]);
            address_defualt_tv3.setText(phone[1]);
            address_defualt_tv4.setText(phone[2]);
            address_defualt_tv5.setText(vo.getAddr_post()+"");
            address_defualt_tv6.setText(vo.getAddr_basic());
            address_defualt_tv7.setText(vo.getAddr_detail());

            if(("y").equals(vo.getAddr_setting())){
                address_defualt_box1.setChecked(true);
            } else {
                address_defualt_box1.setChecked(false);
            }
        }

        address_defualt_btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, SearchAddrActivity.class);
                startActivityForResult(intent,SEARCH_ADDR_CODE);
            }
        });

        address_defualt_box1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(!("y").equals(vo.getAddr_setting()) && isChecked ==false){
                    dao.update_address(vo.getAddr_num());
                    address_defualt_box1.setChecked(true);
                } else if((newVo.getAddr_post() > 0 && address_defualt_box2.isChecked()) && isChecked ==false ){
                    dao.update_address(newVo.getAddr_num());
                    address_defualt_box1.setChecked(true);
                } else {
                    address_defualt_box1.setChecked(false);
                }
            }
        });

        address_defualt_box2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(!vo.getAddr_basic().equals(address_defualt_tv6.getText()+"") && isChecked ==false){
                    setAddress();
                    newVo.setAddr_num(dao.insert_address(newVo));
                    address_defualt_box2.setChecked(true);
                } else {
                    address_defualt_box2.setChecked(false);
                }
            }
        });

        if(newVo.getAddr_num() == 0){
            fragment.addressVo = vo;
        } else {
            fragment.addressVo = newVo;
        }

        return rootView;
    }

    public AddressVO setAddress(){
        newVo.setReceiver_name(address_defualt_tv1.getText()+"");
        newVo.setReceiver_phone(address_defualt_tv2.getText()+"-"+address_defualt_tv3.getText()+"-"+address_defualt_tv4.getText());
        newVo.setAddr_post(Integer.parseInt(address_defualt_tv5.getText()+""));
        newVo.setAddr_basic(address_defualt_tv6.getText()+"");
        newVo.setAddr_detail(address_defualt_tv7.getText()+"");

        return newVo;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == SEARCH_ADDR_CODE){
            String addr = data.getExtras().getString("addr");
            String post = data.getExtras().getString("post");
            if(data != null){
                address_defualt_tv5.setText(post);
                address_defualt_tv6.setText(addr);
            }
        }
    }
}