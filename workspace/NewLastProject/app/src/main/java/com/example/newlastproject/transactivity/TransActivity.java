package com.example.newlastproject.transactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.newlastproject.R;

public class TransActivity extends AppCompatActivity {
    //TransActivity 액티비티 위에 액티비티가 떴을 때
    //투명색으로 배경이 있어서 뒤에 앱이 실제 돌아가는 것처럼
    //보이는 디자인 (Guide, Slash 등등 에서 사용)
    //theme <- 테마에 미리 스타일을 지정 후 Manifest에서 연결
    Button btn1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trans);
        btn1 = findViewById(R.id.btn1);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               finish();
            }
        });
    }
}