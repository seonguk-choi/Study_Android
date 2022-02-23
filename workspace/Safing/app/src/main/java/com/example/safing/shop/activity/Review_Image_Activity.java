package com.example.safing.shop.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;

import com.example.safing.R;
import com.example.safing.shop.adapter.Review_Image_pager_Adapter;

import java.util.ArrayList;

public class Review_Image_Activity extends AppCompatActivity {

    ViewPager2 Review_Image_Rec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_review_image);
        Review_Image_Rec = findViewById(R.id.Review_Image_Rec);


        setRec();
    }

    public void setRec(){
        Intent intent = getIntent();
        ArrayList<String> list = (ArrayList<String>) intent.getSerializableExtra("imageList");

        Review_Image_pager_Adapter adapter_rec1 = new Review_Image_pager_Adapter(Review_Image_Activity.this, list);
        Review_Image_Rec.setAdapter(adapter_rec1);
    }
}