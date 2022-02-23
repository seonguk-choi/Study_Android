package com.example.safing.iot.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.safing.R;
import com.example.safing.MainActivity;

public class IoTFragment extends Fragment {
    Context context;
    LinearLayoutManager manager;
    MainActivity mainActivity = new MainActivity();


    public IoTFragment(Context context){
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_iot, container, false);

        return rootView;
    }
}