package com.example.safing.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.Window;

import com.example.safing.DTO.Product_ReviewlDTO;
import com.example.safing.R;
import com.example.safing.adapter.Review_Image_pager_Adapter;

import java.util.ArrayList;

public class Review_Image_Activity extends AppCompatActivity {

    ViewPager2 Review_Image_Rec;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_review_image);

        Review_Image_Rec = findViewById(R.id.Review_Image_Rec);

        setRec1();

    }

    public void setRec1(){

        ArrayList<Product_ReviewlDTO> list = new ArrayList<>();

        Review_Image_pager_Adapter adapter_rec1 = new Review_Image_pager_Adapter(Review_Image_Activity.this);
        Review_Image_Rec.setAdapter(adapter_rec1);
    }
}