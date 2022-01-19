package com.example.safing.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.viewpager2.widget.ViewPager2;

import com.example.safing.R;
import com.example.safing.VO.Board_FileVO;
import com.example.safing.activity.MainActivity;
import com.example.safing.adapter.Moive_Adapter1;
import com.example.safing.adapter.Moive_Adapter2;
import com.google.android.material.tabs.TabLayout;

public class MovieFragment extends Fragment {
    Context context;
    TabLayout tab_layout;
    ViewPager2 pager2;


    public MovieFragment(Context context){
        this.context = context;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_movie, container, false);
        tab_layout = rootView.findViewById(R.id.movie_tab);
        pager2 = rootView.findViewById(R.id.movie_pager);

        tab_layout.addTab(tab_layout.newTab().setText("감성"));
        tab_layout.addTab(tab_layout.newTab().setText("추천"));

        Moive_Adapter1 adapter_pager1 = new Moive_Adapter1(context);
        Moive_Adapter2 adapter_pager2 = new Moive_Adapter2(context);

        adapter_pager1.addDto(new Board_FileVO(1,"아이디1","#태그1", "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"));
        adapter_pager1.addDto(new Board_FileVO(2,"아이디2","#태그2","http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4"));
        adapter_pager1.addDto(new Board_FileVO(3,"아이디3","#태그3","http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerBlazes.mp4"));
        adapter_pager1.addDto(new Board_FileVO(4,"아이디4","#태그4","http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"));
        adapter_pager1.addDto(new Board_FileVO(5,"아이디5","#태그5","http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4"));

        adapter_pager2.addDto(new Board_FileVO(6,"아이디6","#태그6", "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"));
        adapter_pager2.addDto(new Board_FileVO(7,"아이디7","#태그7","http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4"));
        adapter_pager2.addDto(new Board_FileVO(8,"아이디8","#태그8","http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerBlazes.mp4"));
        adapter_pager2.addDto(new Board_FileVO(9,"아이디9","#태그9","http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"));
        adapter_pager2.addDto(new Board_FileVO(10,"아이디10","#태그10","http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4"));



        pager2.setOrientation(pager2.ORIENTATION_VERTICAL);
        pager2.setAdapter(adapter_pager1);

        tab_layout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                if(position == 0){
                    pager2.setOrientation(pager2.ORIENTATION_VERTICAL);
                    pager2.setAdapter(adapter_pager1);
                } else {
                    pager2.setOrientation(pager2.ORIENTATION_VERTICAL);
                    pager2.setAdapter(adapter_pager2);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        return rootView;
    }
}