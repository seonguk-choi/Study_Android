package com.example.new03_recyclerpager.pager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.example.new03_recyclerpager.R;
import com.example.new03_recyclerpager.pager.fragments.Fragment1;
import com.example.new03_recyclerpager.pager.fragments.Fragment2;
import com.example.new03_recyclerpager.pager.fragments.Fragment3;
import com.example.new03_recyclerpager.pager.fragments.Fragment4;
import com.example.new03_recyclerpager.pager.fragments.Fragment5;
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator;

public class PagerActivity extends AppCompatActivity {
    //0.pager2를 액티비티에 추가( 기존 API 형식으로 gradle에 넣지않아도 기본제공)
    //1.xml 아이템준비(RecyclerView) -> Fragment준비 (각각의 화면을 다르게 컨트롤하기 위해서)
    //2.xml <-> java Fragment를 사용할 수 있게 연결
    //3.Adapter만들기 (RecyclerAdapter랑 비슷함)
    ViewPager2 pager2;
    DotsIndicator indicator;
    LinearLayoutManager layoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pager);

        pager2 = findViewById(R.id.pager2);
        indicator = findViewById(R.id.dots_indicator);
        Pager2Adapter adapter = new Pager2Adapter(PagerActivity.this);

        pager2.setAdapter(adapter);
        indicator.setViewPager2(pager2);

    }
}