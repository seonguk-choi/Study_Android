package com.example.assignment.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.assignment.MainActivity;
import com.example.assignment.OnItemClick;
import com.example.assignment.R;
import com.example.assignment.pager.pager2Activity;
import com.example.assignment.recycler.RecAdapter;
import com.example.assignment.recycler.RecDTO;
import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator;

import java.util.ArrayList;

public class Fragment1 extends Fragment {

    ViewPager2 pager2;
    WormDotsIndicator indicator;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment1,
                container, false);


        pager2 = rootView.findViewById(R.id.pager2_frag);
        indicator = rootView.findViewById(R.id.dots_indicator_frag);

        RecAdapter adapter =new RecAdapter(getContext());
        adapter.addDto(new RecDTO(R.drawable.img1, "귀여운"));
        adapter.addDto(new RecDTO(R.drawable.img2, "하프"));
        adapter.addDto(new RecDTO(R.drawable.img3, "물범"));
        adapter.addDto(new RecDTO(R.drawable.img4, "출현"));
        adapter.addDto(new RecDTO(R.drawable.img5, "GOOD"));

        pager2.setAdapter(adapter);
        indicator.setViewPager2(pager2);

        return rootView;
    }
}
