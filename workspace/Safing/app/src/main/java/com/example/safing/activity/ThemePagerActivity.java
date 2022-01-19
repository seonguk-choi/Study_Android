package com.example.safing.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;


import com.example.safing.R;
import com.example.safing.adapter.Theme_Pager_Adapter;

public class ThemePagerActivity extends AppCompatActivity {

    ViewPager2 pager2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pager);
        pager2 = findViewById(R.id.pager2);
        Theme_Pager_Adapter adapter = new Theme_Pager_Adapter(ThemePagerActivity.this);
        pager2.setAdapter(adapter);
    }
}