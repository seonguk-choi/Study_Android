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

import com.example.assignment.R;
import com.example.assignment.Video_EXO.VideoItem;
import com.example.assignment.recycler.RecAdapter;
import com.example.assignment.recycler.RecAdapter_video;
import com.example.assignment.recycler.RecDTO;
import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator;

public class Fragment4 extends Fragment {
    ViewPager2 pager2;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment4,
                container, false);
        pager2 = rootView.findViewById(R.id.pager2_video);
        //"http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"
        //"http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4"
        //"http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerBlazes.mp4"

        RecAdapter_video adapter =new RecAdapter_video(getContext());

        adapter.addDto(new VideoItem("TEST1","태그1","http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"));
        adapter.addDto(new VideoItem("TEST2","태그2","http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4"));
        adapter.addDto(new VideoItem("TEST3","태그3","http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerBlazes.mp4"));
        adapter.addDto(new VideoItem("TEST4","태그4","http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"));
        adapter.addDto(new VideoItem("TEST5","태그5","http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4"));


        pager2.setOrientation(pager2.ORIENTATION_VERTICAL);
        pager2.setAdapter(adapter);
        return rootView;


    }
}
