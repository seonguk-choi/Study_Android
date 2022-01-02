package com.example.mockupproject.transactivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.mockupproject.R;
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator;

public class TransActivity extends AppCompatActivity {
    Button trans_btn;
    ViewPager2 trans_pager;
    DotsIndicator indicator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trans);

        trans_btn = findViewById(R.id.trans_btn);
        trans_pager = findViewById(R.id.trans_pager);
        indicator = findViewById(R.id.trans_indicator);

        TransPagerAdapter adapter = new TransPagerAdapter(TransActivity.this);

        trans_pager.setAdapter(adapter);
        indicator.setViewPager2(trans_pager);

        trans_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               finish();
            }
        });

    }
}