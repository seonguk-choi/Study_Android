package com.example.safing.activity;

import androidx.appcompat.app.AppCompatActivity;

import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.example.safing.R;
import com.example.safing.adapter.CaminfoPager2Adapter;


public class CamInfoActivity extends AppCompatActivity {
    ViewPager2 pager2 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cam_info);

        pager2 = findViewById(R.id.vpcaminfo);

        CaminfoPager2Adapter adapter = new CaminfoPager2Adapter(CamInfoActivity.this);
        pager2.setAdapter(adapter);

    }
}