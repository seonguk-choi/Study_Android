package com.example.new03_recyclerpager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.new03_recyclerpager.pager.PagerActivity;
import com.example.new03_recyclerpager.recycler.RecyclerActivity;
import com.example.new03_recyclerpager.recycler2.Recycler2Activity;
import com.example.new03_recyclerpager.recyclerTest.RecyclerTestActivity;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "main";
    Button btn1, btn2, btn3, btn4;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: bnt1");
                //리사이클러뷰는 어댑터의 커스터마이징을 강제한다
                //기본 형태의 어댑터를 제공안함
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: bnt2");
                intent = new Intent(MainActivity.this, Recycler2Activity.class);
                startActivity(intent);
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: bnt3");
                intent = new Intent(MainActivity.this, RecyclerTestActivity.class);
                startActivity(intent);
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: bnt4");
                intent = new Intent(MainActivity.this, PagerActivity.class);
                startActivity(intent);
            }
        });
    }
}