package com.example.safing.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;

import com.example.safing.DTO.Product_ReviewlDTO;
import com.example.safing.R;
import com.example.safing.adapter.Review_Image_Rec_Adapter;

import java.util.ArrayList;

public class Review_Image_Activity extends AppCompatActivity {

    RecyclerView Review_Image_Rec;
    ImageButton review_image_btn;
    LinearLayoutManager manager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_review_image);

        Review_Image_Rec = findViewById(R.id.Review_Image_Rec);
        review_image_btn = findViewById(R.id.review_image_btn);

        setRec1();

        review_image_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    public void setRec1(){
        manager = new LinearLayoutManager(Review_Image_Activity.this, RecyclerView.HORIZONTAL, false);
        ArrayList<Product_ReviewlDTO> list = new ArrayList<>();

        Review_Image_Rec.setLayoutManager(manager);
        Review_Image_Rec_Adapter adapter_rec1 = new Review_Image_Rec_Adapter(Review_Image_Activity.this);
        Review_Image_Rec.setAdapter(adapter_rec1);
    }
}