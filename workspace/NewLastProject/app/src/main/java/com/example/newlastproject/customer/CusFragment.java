package com.example.newlastproject.customer;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager.widget.PagerAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.newlastproject.R;

import java.util.ArrayList;


public class CusFragment extends Fragment {

    RecyclerView cus_rc_view;
    Context context;
    SwipeRefreshLayout swipe;
    androidx.appcompat.widget.SearchView searchView;
    public CusFragment(Context context) {
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_cus, container, false);
        cus_rc_view = rootView.findViewById(R.id.cus_rcview);
        swipe = rootView.findViewById(R.id.cus_swipe);
        searchView = rootView.findViewById(R.id.cus_schview);
        dataSelect(null);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Toast.makeText(context, query, Toast.LENGTH_SHORT).show();
                dataSelect(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Toast.makeText(context, newText, Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //조회를 다시하는 처리.메소드
                dataSelect(null);
                swipe.setRefreshing(false);
            }
        });

        return rootView;
    }

    public void dataSelect(String query){
        CustomerDAO dao = new CustomerDAO();
        ArrayList<CustomerVO> list = null;
        if(query != null){
            list = dao.list(query);
        } else {
            list = dao.list();
        }
        LinearLayoutManager manager = new LinearLayoutManager(
                context , RecyclerView.VERTICAL , false
        );
        CusAdapter adapter = new CusAdapter(context, list);
        cus_rc_view.setLayoutManager(manager);
        cus_rc_view.setAdapter(adapter);
    }

}