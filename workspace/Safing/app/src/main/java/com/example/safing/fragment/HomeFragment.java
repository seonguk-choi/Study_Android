package com.example.safing.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.safing.R;
import com.example.safing.activity.HomeSearchActivity;
import com.example.safing.activity.MainActivity;

public class HomeFragment extends Fragment {
    Context context;
    LinearLayoutManager manager;
    MainActivity mainActivity = new MainActivity();

    public HomeFragment(Context context){
        this.context = context;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_home, container, false);

        rootView.findViewById(R.id.home_search).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(context, HomeSearchActivity.class);
                startActivity(intent);
                mainActivity = (MainActivity) getActivity();
                mainActivity.overridePendingTransition(R.anim.slide_right_enter, R.anim.slide_left_exit);
            }
        });


        return  rootView;
    }
}