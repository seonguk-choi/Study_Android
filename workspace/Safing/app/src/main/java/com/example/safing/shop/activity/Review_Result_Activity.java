package com.example.safing.shop.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.safing.MainActivity;
import com.example.safing.R;
import com.example.safing.async.CommonVal;
import com.example.safing.shop.VO.PurchaseHistoryVO;
import com.example.safing.shop.VO.ReviewVO;

public class Review_Result_Activity extends AppCompatActivity {
    TextView review_result_tv1;
    Button review_result_btn1, review_result_btn2;
    PurchaseHistoryVO vo = new PurchaseHistoryVO();
    ReviewVO reviewVO = new ReviewVO();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_result);

        Intent intent = getIntent();
        vo = (PurchaseHistoryVO) intent.getSerializableExtra("vo");


        review_result_tv1 = findViewById(R.id.review_result_tv1);
        review_result_btn1 = findViewById(R.id.review_result_btn1);
        review_result_btn2 = findViewById(R.id.review_result_btn2);

        review_result_btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Review_Result_Activity.this, MainActivity.class);
                intent.putExtra("fragment", "Product_PurchaseHistory_Fragment");
                startActivity(intent);
            }
        });

        review_result_btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Review_Result_Activity.this, MainActivity.class);
                intent.putExtra("fragment", "review");
                intent.putExtra("vo", vo);
                startActivity(intent);
            }
        });
    }
}