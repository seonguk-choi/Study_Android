package com.example.safing.shop.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.example.safing.MainActivity;
import com.example.safing.R;
import com.example.safing.shop.VO.PurchaseHistoryVO;


public class Purchase_Result_Activity extends AppCompatActivity {
    TextView purchase_result_tv1;
    Button purchase_result_btn1, purchase_result_btn2;
    PurchaseHistoryVO vo = new PurchaseHistoryVO();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_result);

        purchase_result_tv1 = findViewById(R.id.purchase_result_tv1);
        purchase_result_btn1 = findViewById(R.id.purchase_result_btn1);
        purchase_result_btn2 = findViewById(R.id.purchase_result_btn2);

        purchase_result_btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Purchase_Result_Activity.this, MainActivity.class);
                intent.putExtra("fragment", "Product_PurchaseHistory_Fragment");
                startActivity(intent);
            }
        });

        purchase_result_btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Purchase_Result_Activity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}