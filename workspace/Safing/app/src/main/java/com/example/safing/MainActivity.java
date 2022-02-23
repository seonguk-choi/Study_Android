package com.example.safing;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.MenuItem;

import com.example.safing.iot.fragment.IoTFragment;
import com.example.safing.shop.VO.PurchaseHistoryVO;
import com.example.safing.shop.fragment.Product_Fragment;
import com.example.safing.shop.fragment.Product_Package_Fragment;
import com.example.safing.shop.fragment.Product_PurchaseHistory_Fragment;
import com.example.safing.shop.fragment.ShopFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottom_nav;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build(); StrictMode.setThreadPolicy(policy);}

        bottom_nav = findViewById(R.id.bottom_nav);

        Intent intent = getIntent();
        String chageFrag = intent.getStringExtra("fragment");
        PurchaseHistoryVO vo = (PurchaseHistoryVO) intent.getSerializableExtra("vo");

        bottom_nav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId() == R.id.tab1){

                    return true;
                }else if(item.getItemId() == R.id.tab2){

                    return true;
                }else if(item.getItemId() == R.id.tab3){
                    changeFragment(new IoTFragment(MainActivity.this));
                    return true;
                }else if(item.getItemId() == R.id.tab4){
                    changeFragment(new ShopFragment(MainActivity.this));
                    return true;
                }else if(item.getItemId() == R.id.tab5){

                    return true;
                }
                return false;
            }
        });

        if(("Product_PurchaseHistory_Fragment").equals(chageFrag)){
            changeFragment(new Product_PurchaseHistory_Fragment(MainActivity.this));
        } else if(("review").equals(chageFrag)){
            if(vo.getProduct_num()> 0){
                changeFragment(new Product_Fragment(MainActivity.this, vo.getProduct_num(), "review"));
            } else {
                changeFragment(new Product_Package_Fragment(MainActivity.this, vo.getPackage_num(), "review"));
            }
        }
    }
    public void changeFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.container , fragment).commit();
    }

}