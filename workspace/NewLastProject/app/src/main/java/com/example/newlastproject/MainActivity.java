package com.example.newlastproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText edt1, edt2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edt1 = findViewById(R.id.main_id);
        edt2 = findViewById(R.id.main_pw);

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        String pw = intent.getStringExtra("pw");

        edt1.setText(id);
        edt2.setText(pw);
    }
}