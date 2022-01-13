package com.example.assignment.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.assignment.OnItemClick;
import com.example.assignment.R;
import com.example.assignment.recycler.RecAdapter;
import com.example.assignment.recycler.RecDTO;
import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator;

public class Fragment3 extends Fragment {
    ViewPager2 pager2;
    WormDotsIndicator indicator;
    RecyclerView recyclerView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment3,
                container, false);
        pager2 = rootView.findViewById(R.id.magazine_pager2);
        indicator = rootView.findViewById(R.id.magazine_indi);

        RecAdapter adapter =new RecAdapter(getContext());
        adapter.addDto(new RecDTO(R.drawable.img1, "귀여운"));
        adapter.addDto(new RecDTO(R.drawable.img2, "하프"));
        adapter.addDto(new RecDTO(R.drawable.img3, "물범"));
        adapter.addDto(new RecDTO(R.drawable.img4, "출현"));
        adapter.addDto(new RecDTO(R.drawable.img5, "GOOD"));



        recyclerView = rootView.findViewById(R.id.magazine_recview);
        RecAdapter adapter2 =new RecAdapter(getContext());
        adapter2.addDto(new RecDTO(R.drawable.img1, "귀여운"));
        adapter2.addDto(new RecDTO(R.drawable.img2, "하프"));
        adapter2.addDto(new RecDTO(R.drawable.img3, "물범"));
        adapter2.addDto(new RecDTO(R.drawable.img4, "출현"));
        adapter2.addDto(new RecDTO(R.drawable.img5, "GOOD"));

        LinearLayoutManager layoutManager = new LinearLayoutManager(
                getContext(), RecyclerView.VERTICAL , false
        );
        pager2.setOrientation(pager2.ORIENTATION_HORIZONTAL);
        pager2.setAdapter(adapter);
        indicator.setViewPager2(pager2);
        recyclerView.setLayoutManager(layoutManager);//리사이클러뷰가 세로 모드로 붙게끔 설정.
        recyclerView.setAdapter(adapter2);
        return rootView;


    }
}
