package com.example.project01;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Process;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        //사용자는 앱(어플)이 멈춘상태가 되면 당황한다. 어떤 상태인지 인지를 못함(=사용자입장에서 만드는 것이 매우 중요)
        //로딩이 오래걸릴 것 같은 경우에는 Progress를 이용해서 진행상태를 알려주는 것이 중요하다.
        //spinner (동그라미) ProgressDialog(진행률 x) = 얼마나 시간이 걸리는지 모를 때
        //bar(막대형) progress bar (진행률 O) = 얼마나 시간이 걸리는지 알 때
        ProgressDialog pgdilog = new ProgressDialog(SplashActivity.this);
        //context 실제 화면에 떠있거나 또는 컴포넌트를 의미함
        //background는 context를 가지고 있어도 widget을 보여줄 수 없음.
        pgdilog.setTitle("지금은 프로텍트1을 로딩하는 중입니다.");//dilog글씨
        pgdilog.show();//실제보이게 하는 처리


        // 몇 초정도 로고 또는 어떤 앱에 관련 된 이미지를 보여주고 나서 (몇 초 정도)
        // MainActivity로 이동 Intent -> 를 이용한다.
        //Handler를 이용해서 딜레이를 준다.(외울 필요 없음 X 복붙해서 사용)
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //딜레이를 주고나서 run을 실행함.
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                pgdilog.dismiss(); //안보이게 처리
                finish();//뒤로가기 했을때 액티비티가 없게 현재 액티비티를 종료시킴.
            }
        }, 3000);
    }
}