package com.example.safing.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.safing.R;
import com.example.safing.adapter.Review_Image_Pager_Adapter;
import com.example.safing.adapter.Shop_Product_Pager_Adapter;

public class Review_Image_Activity extends AppCompatActivity {
    ViewPager2 pager;
    ImageButton review_image_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_image);

        pager = findViewById(R.id.Review_Image_Rec);
        review_image_btn = findViewById(R.id.review_image_btn);

        Review_Image_Pager_Adapter adapter_pager1 = new Review_Image_Pager_Adapter(Review_Image_Activity.this);
        pager.setAdapter(adapter_pager1);

        review_image_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}