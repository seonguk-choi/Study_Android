package com.example.assignment.pager;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.example.assignment.R;
import com.example.assignment.recycler.RecAdapter;
import com.example.assignment.recycler.RecDTO;
import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator;

import java.util.ArrayList;
import java.util.List;

public class pager2Activity extends AppCompatActivity {
ViewPager2 pager2;
WormDotsIndicator indicator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pager2);

        pager2 = findViewById(R.id.pager2);
        indicator = findViewById(R.id.dots_indicator);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        ArrayList<RecDTO> list = new ArrayList<>();
        RecAdapter adapter =new RecAdapter(pager2Activity.this,list);
        adapter.addDto(new RecDTO(R.drawable.img1, "귀여운"));
        adapter.addDto(new RecDTO(R.drawable.img2, "하프"));
        adapter.addDto(new RecDTO(R.drawable.img3, "물범"));
        adapter.addDto(new RecDTO(R.drawable.img4, "출현"));
        adapter.addDto(new RecDTO(R.drawable.img5, "GOOD"));

        pager2.setAdapter(adapter);
        indicator.setViewPager2(pager2);

    }
}