package com.example.newlastproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
<<<<<<< HEAD
import android.view.MenuItem;
import android.view.View;

import com.example.newlastproject.async.AskTest2;
import com.example.newlastproject.customer.CusFragment;
import com.example.newlastproject.noti.NotiActivity;
import com.example.newlastproject.noti.NotiFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
=======
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
>>>>>>> 9b6d72f32d8996b3f771726d73ffd51f54a019ec

import com.example.newlastproject.asktask.AskTask;
import com.example.newlastproject.noti.NotiActivity;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
<<<<<<< HEAD
    BottomNavigationView btm_view;
=======
    //어플 알람 방법
    //1. Notify .. API 알람띄우기 (기기 고유값을 알고 있는 상태에서 WEB->Android)
    //2. Background 서비스를 하나 만들고 계속 해서 어플이 종료되도
    //서버에 있는 데이터를 수신할 수 있게 만들고 나서 사용
    //3.버튼을 클릭했을때 알림이 나오게 처리

    //1.번 방법
    //오레오 버번(API 26) 이후에는 대부분의 안드로이드폰에서는
    //이 알람을 띄우기 위한 별도의 알람 채널, 채널이름이 필요함.
    final String CHANNEL_ID1 = "channel1";
    final String CHANNEL_NAME1 = "channel2";
    final String CHANNEL_ID2 = "channel1";
    final String CHANNEL_NAME2 = "channel2";
    final String CHANNEL_ID3 = "channel1";
    final String CHANNEL_NAME3 = "channel2";

    NotificationManager manager; //상단에 툴바(알람)
>>>>>>> 9b6d72f32d8996b3f771726d73ffd51f54a019ec
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
<<<<<<< HEAD
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






=======

        findViewById(R.id.btn1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNotiSimple();
            }
        });

        findViewById(R.id.btn2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNotiPending();
            }
        });

        findViewById(R.id.btn3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNotiBigText();
            }
        });
        findViewById(R.id.btn4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AskTask askTask = new AskTask("test");
                try {
                    InputStream in = askTask.execute().get();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

    }// oncreate()

    //단순 알람띄우기
    private void showNotiSimple(){
        //알람을 띄우기 위해 필요한 것 -> Manager 전역변수로 만들어 놓은 필드
        //Fragment를 사용하는 이유 Context(제어권) 기능을 담은 서비스를 갖고 있음
        //액티비티를 많이 만들면 Context가 많아져 느려짐

        //1. manager 객체 초기화
        manager = (NotificationManager) getSystemService(MainActivity.NOTIFICATION_SERVICE);

        //2. Noti알람 객체를 만들기 위한 Builder라는 객체가 필요함
        //   생성식이 오레오 이전과 이후가 다르다
        NotificationCompat.Builder builder = null;
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            //현재 버젼이 Oreo 이상인 상태에서 초기화식
            if(manager.getNotificationChannel(CHANNEL_ID1) == null){
                manager.createNotificationChannel(new NotificationChannel(
                        CHANNEL_ID1,
                        CHANNEL_NAME1,
                        NotificationManager.IMPORTANCE_DEFAULT
                ));
            }
            builder = new NotificationCompat.Builder(MainActivity.this, CHANNEL_ID1);
        } else {
            builder = new NotificationCompat.Builder(MainActivity.this);
        }
        builder.setContentTitle("간단한 알람 제목").setContentText("간단한 알람 내용").setSmallIcon(android.R.drawable.ic_menu_view);

        Notification noti = builder.build();
        manager.notify(1 ,noti);
    }

    //펜딩 인텐트 : Intent객체를 가진 객체
    //알람이나 OS에 붙에있는 화면(프로세스)이 아닌 상태에서 화면 전환 하기 위한 객체
    //펜딩 인텐트는 특정한 상황(알람을 클릭)에서 어떤 행동을 할지 미리 지정
    private void showNotiPending(){
        manager = (NotificationManager) getSystemService(MainActivity.NOTIFICATION_SERVICE);

        NotificationCompat.Builder builder = null;
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            if(manager.getNotificationChannel(CHANNEL_ID1) == null){
                manager.createNotificationChannel(new NotificationChannel(
                        CHANNEL_ID2,
                        CHANNEL_NAME2,
                        NotificationManager.IMPORTANCE_DEFAULT
                ));
            }
            builder = new NotificationCompat.Builder(MainActivity.this, CHANNEL_ID1);
        } else {
            builder = new NotificationCompat.Builder(MainActivity.this);
        }

        Intent intent = new Intent(this, NotiActivity.class);
        //FLAG = 기존의 팬딩인텐스가 있다면 현재 인탠트로 바꿀지 선택

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 1001, intent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentTitle("펜딩 인텐트 제목")
                .setContentText("펜딩 인텐트 내용")
                .setSmallIcon(android.R.drawable.ic_menu_view)
                .setAutoCancel(true) //알람은 클릭하면 자동으로 알람이 사라지는 것
                .setContentIntent(pendingIntent); // 클릭을 하면 발동할 PendingIntent
        Notification noti = builder.build();
        manager.notify(2, noti);
    }

    private void showNotiBigText(){
        manager = (NotificationManager) getSystemService(MainActivity.NOTIFICATION_SERVICE);

        NotificationCompat.Builder builder = null;
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            if(manager.getNotificationChannel(CHANNEL_ID1) == null){
                manager.createNotificationChannel(new NotificationChannel(
                        CHANNEL_ID3,
                        CHANNEL_NAME3,
                        NotificationManager.IMPORTANCE_DEFAULT
                ));
            }
            builder = new NotificationCompat.Builder(MainActivity.this, CHANNEL_ID1);
        } else {
            builder = new NotificationCompat.Builder(MainActivity.this);
        }

        Intent intent = new Intent(this, NotiActivity.class);
        //FLAG = 기존의 팬딩인텐스가 있다면 현재 인탠트로 바꿀지 선택

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 1001, intent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        //글자가 긴 알람은 스타일을 먼저 만든다.
        NotificationCompat.BigTextStyle style
                = new NotificationCompat.BigTextStyle();

        style.bigText("긴글긴글긴글긴글긴글긴글긴글긴글긴글긴글긴글긴글긴글긴글긴글긴글긴글긴글긴글긴글긴글긴글긴글긴글긴글긴글긴글긴글긴글긴글긴글긴글긴글긴글긴글긴글긴글긴글긴글긴글긴글긴글긴글긴글긴글");
        style.setBigContentTitle("긴글 알람 제목");
        style.setSummaryText("요약해서 보여줄 정보");

        builder = new NotificationCompat.Builder(this,CHANNEL_ID1)
                    .setSmallIcon(android.R.drawable.ic_menu_view)
                    .setContentIntent(pendingIntent)
                    .setStyle(style);

        Notification noti = builder.build();
        manager.notify(3, noti);
    }




>>>>>>> 9b6d72f32d8996b3f771726d73ffd51f54a019ec
}