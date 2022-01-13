package com.example.assignment.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assignment.OnItemClick;
import com.example.assignment.R;
import com.example.assignment.recycler.RecAdapter;
import com.example.assignment.recycler.RecDTO;
import com.example.assignment.recycler.RecyclerActivity;

import java.util.ArrayList;

public class Fragment2 extends Fragment {
    RecyclerView recyclerView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment2,
                container, false);

        recyclerView = rootView.findViewById(R.id.recyclerview_frag);
        RecAdapter adapter =new RecAdapter(getContext());
        adapter.addDto(new RecDTO(R.drawable.img1, "귀여운"));
        adapter.addDto(new RecDTO(R.drawable.img2, "하프"));
        adapter.addDto(new RecDTO(R.drawable.img3, "물범"));
        adapter.addDto(new RecDTO(R.drawable.img4, "출현"));
        adapter.addDto(new RecDTO(R.drawable.img5, "GOOD"));
/*
        ArrayList<RecDTO> list = new ArrayList<>();
        list.add(new RecDTO(R.drawable.img1, "귀여운"));
        list.add(new RecDTO(R.drawable.img2, "하프"));
        list.add(new RecDTO(R.drawable.img3, "물범"));
        list.add(new RecDTO(R.drawable.img4, "출현"));
        list.add(new RecDTO(R.drawable.img5, "GOOD"));
*/


        LinearLayoutManager layoutManager = new LinearLayoutManager(
                getContext(), RecyclerView.VERTICAL , false
        );
        recyclerView.setLayoutManager(layoutManager);//리사이클러뷰가 세로 모드로 붙게끔 설정.
        recyclerView.setAdapter(adapter);
        return rootView;
    }
}
