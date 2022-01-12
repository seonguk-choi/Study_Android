package com.example.newlastproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.newlastproject.async.CommonVal;
import com.example.newlastproject.customer.CusFragment;
import com.example.newlastproject.noti.NotiFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView btm_view;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //========== 풀스크린 모드로 시계 영역 가리기
        View decoView = getWindow().getDecorView();
        int uiOption = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decoView.setSystemUiVisibility(uiOption);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);

        //햄버거 버튼 만들기 (버튼을 눌러서 반전시키는 효과를 만듬)
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_open
        );
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView nav_view = findViewById(R.id.nav_view);

        View nav_headerview = nav_view.getHeaderView(0);
        ImageView nav_img = nav_headerview.findViewById(R.id.nav_imge);
        TextView nav_textv1= nav_headerview.findViewById(R.id.nav_text1);
        TextView nav_textv2= nav_headerview.findViewById(R.id.nav_text2);

        Glide.with(MainActivity.this).load(CommonVal.loginInfo.getFilepath()).into(nav_img);
        nav_textv1.setText(CommonVal.loginInfo.getId());
        nav_textv2.setText(CommonVal.loginInfo.getTel());


        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build(); StrictMode.setThreadPolicy(policy); }
        getSupportFragmentManager().beginTransaction()


                .replace(R.id.container , new NotiFragment(this)).commit();
        btm_view = findViewById(R.id.btm_nav);
        btm_view.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId() == R.id.menu_noti){
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container , new NotiFragment(MainActivity.this)).commit();
                }else if(item.getItemId() == R.id.menu_cus){
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container , new CusFragment(MainActivity.this)).commit();
                }

                return true;
            }
        });

    }// oncreate()






}