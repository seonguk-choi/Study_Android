package com.example.assignment;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.assignment.recycler.RecAdapter;
import com.example.assignment.recycler.RecAdapter_video;

public class VideoActivity extends AppCompatActivity {

    ViewPager2 pager2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        pager2 = findViewById(R.id.video_pager2);
        RecAdapter_video adapter = new  RecAdapter_video(VideoActivity.this);

        pager2.setAdapter(adapter);



    }

}