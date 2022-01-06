package com.example.mockupproject.navigation;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.mockupproject.DAO.RecyclerViewDAO;
import com.example.mockupproject.R;

public class MagazineNavigation extends Fragment {
    Context context;


    public MagazineNavigation(Context context){
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.magazine_navigation, container, false);
        return  rootView;
    }
}