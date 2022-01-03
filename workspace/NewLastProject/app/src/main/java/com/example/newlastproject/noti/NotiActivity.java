package com.example.newlastproject.noti;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.newlastproject.LoginActivity;
import com.example.newlastproject.R;
import com.example.newlastproject.asktask.AskTask;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.ExecutionException;

public class NotiActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noti);
        //공유자원 LoginDTO가 null인지 체크 로그인 안되었으면
        //로그인 페이지로
        //로그인이 되었다면 화면을 보여줌

        SharedPreferences preferences = getPreferences(LoginActivity.MODE_PRIVATE);
        String id = preferences.getString("id",null);
        String pw = preferences.getString("pw",null);
        if(id == null && pw == null){
            Intent intent = new Intent(NotiActivity.this, LoginActivity.class);
            startActivity(intent);
        }


    }
}