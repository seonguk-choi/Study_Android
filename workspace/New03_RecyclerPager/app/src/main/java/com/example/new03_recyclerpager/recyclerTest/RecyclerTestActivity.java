package com.example.new03_recyclerpager.recyclerTest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.new03_recyclerpager.R;

import java.util.ArrayList;

public class RecyclerTestActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<RecTestDTO> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_test);

        recyclerView = findViewById(R.id.rectestview);

        list = new ArrayList<>();
        list.add(new RecTestDTO(R.drawable.user,R.drawable.image1,"최성욱", "#1박2일"));
        list.add(new RecTestDTO(R.drawable.music,R.drawable.image2,"기근태", "#감성캠핑"));
        list.add(new RecTestDTO(R.drawable.user,R.drawable.image3,"이지강", "#나홀로"));
        list.add(new RecTestDTO(R.drawable.music,R.drawable.image5,"노명운", "#힐링캠핑"));
        list.add(new RecTestDTO(R.drawable.user,R.drawable.image8,"김시원", "#등산"));

        RecTestAdapter adapter = new RecTestAdapter(RecyclerTestActivity.this, list);

        LinearLayoutManager layoutManager = new LinearLayoutManager(
                RecyclerTestActivity.this, RecyclerView.HORIZONTAL, false
        );

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

    }
}