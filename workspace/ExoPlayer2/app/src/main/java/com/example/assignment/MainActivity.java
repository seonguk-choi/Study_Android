package com.example.assignment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.assignment.fragment.Fragment1;
import com.example.assignment.fragment.Fragment2;
import com.example.assignment.fragment.Fragment3;
import com.example.assignment.fragment.Fragment4;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity implements OnItemClick {
    Fragment1 fragment1;
    Fragment2 fragment2;
    Fragment3 fragment3;
    Fragment4 fragment4;
    TabLayout tabLayout;
    BottomNavigationView bottom_nav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        String lis = this + "";
        //tabLayout = findViewById(R.id.tabs);
        fragment1 = new Fragment1();
        fragment2 = new Fragment2();
        fragment3 = new Fragment3();
        fragment4 = new Fragment4();
        bottom_nav = findViewById(R.id.bottom_nav);

        getSupportFragmentManager().
                beginTransaction().
                replace(R.id.container, fragment1).commit();

/*        menu.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem item) {
                {
                    if (item.getItemId() == R.id.tab1) {
                        Toast.makeText(MainActivity.this, item.getItemId(), Toast.LENGTH_SHORT).show();
                    } else if (item.getItemId() == R.id.tab2) {
                        Toast.makeText(MainActivity.this, item.getItemId(), Toast.LENGTH_SHORT).show();
                        *//*getSupportFragmentManager().beginTransaction()
                                .replace(R.id.container, fragment2).commit();*//*
                    }
                }
            }
        });*/

/*        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                Fragment selected = null;
                if (position == 0) {
                    selected = fragment1;
                } else if (position == 1) {
                    selected = fragment3;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.contain, selected).commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });*/

        bottom_nav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.tab1:
                        Toast.makeText(MainActivity.this, "pager2 Fragment", Toast.LENGTH_SHORT).show();
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.container, fragment1).commit();
                        break;

                    case R.id.tab2:
                        Toast.makeText(MainActivity.this, "Rec Fragment", Toast.LENGTH_SHORT).show();
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.container, fragment2).commit();
                        break;

                    case R.id.tab3:
                        Toast.makeText(MainActivity.this, "pager2/rec Fragment", Toast.LENGTH_SHORT).show();
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.container, fragment3).commit();
                        break;

                    case R.id.tab4:
                        Toast.makeText(MainActivity.this, "동영상 pager2 TEST", Toast.LENGTH_SHORT).show();
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.container, fragment4).commit();
/*                        Toast.makeText(MainActivity.this, "Video Activity", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this,VideoActivity.class);
                        startActivity(intent);*/
                        break;

                    case R.id.tab5:
                        Toast.makeText(MainActivity.this, "EXO Activity", Toast.LENGTH_SHORT).show();
                        Intent intent2 = new Intent(MainActivity.this,EXOActivity.class);
                        startActivity(intent2);

                        break;


                }
                return true;
            }
        });


    }


    @Override
    public void onClick(String value) {
        if (value.equals("1")) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment1).commit();
        } else if (value.equals("2")) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment2).commit();
        }
    }
}
