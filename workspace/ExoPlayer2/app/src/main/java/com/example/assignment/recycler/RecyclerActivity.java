package com.example.assignment.recycler;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.assignment.R;

import java.util.ArrayList;

public class RecyclerActivity extends AppCompatActivity {
RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);
        recyclerView = findViewById(R.id.recyclerview);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        ArrayList<RecDTO> list = new ArrayList<>();
        list.add(new RecDTO(R.drawable.img1, "귀여운"));
        list.add(new RecDTO(R.drawable.img2, "하프"));
        list.add(new RecDTO(R.drawable.img3, "물범"));
        list.add(new RecDTO(R.drawable.img4, "출현"));
        list.add(new RecDTO(R.drawable.img5, "GOOD"));

        RecAdapter adapter = new RecAdapter(RecyclerActivity.this,list);

        LinearLayoutManager layoutManager = new LinearLayoutManager(
                RecyclerActivity.this , RecyclerView.VERTICAL , false
        );
        recyclerView.setLayoutManager(layoutManager);//리사이클러뷰가 세로 모드로 붙게끔 설정.
        recyclerView.setAdapter(adapter);
    }
}