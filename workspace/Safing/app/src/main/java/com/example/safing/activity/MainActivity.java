package com.example.safing.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.safing.R;
import com.example.safing.fragment.HomeFragment;
import com.example.safing.fragment.IoTFragment;
import com.example.safing.fragment.MovieFragment;
import com.example.safing.fragment.MypageFragment;
import com.example.safing.fragment.ShopFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottom_nav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottom_nav = findViewById(R.id.bottom_nav);

        changeFragment(new HomeFragment(MainActivity.this));

        bottom_nav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId() == R.id.tab1){
                    changeFragment(new HomeFragment(MainActivity.this));
                    return true;
                }else if(item.getItemId() == R.id.tab2){
                    changeFragment(new MovieFragment(MainActivity.this));
                    return true;
                }else if(item.getItemId() == R.id.tab3){
                    changeFragment(new IoTFragment(MainActivity.this));
                    return true;
                }else if(item.getItemId() == R.id.tab4){
                    changeFragment(new ShopFragment(MainActivity.this));
                    return true;
                }else if(item.getItemId() == R.id.tab5){
                    changeFragment(new MypageFragment(MainActivity.this));
                    return true;
                }
                return false;
            }
        });
    }
    public void changeFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.container , fragment).commit();
    }
}