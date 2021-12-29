package com.example.my16_progress;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "main:MainActivity";

    ProgressBar progressBar;
    EditText editText;
    Button btnInput;
    ProgressDialog dialog;
    SeekBar seekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.progressBar);
        editText = findViewById(R.id.editText);
        btnInput = findViewById(R.id.btnInput);
        seekBar = findViewById(R.id.seekBar);

        // progressBar 설정
        progressBar.setIndeterminate(false);  // 정해진 값을 사용하는가?
        progressBar.setMax(100);
        progressBar.setProgress(20);

        // 입력하기
        btnInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editText.getText().toString().length() > 0) {
                    int value = Integer.parseInt(editText.getText().toString());
                    if(value > 100){
                        value = 100;
                    }
                    progressBar.setProgress(value);
                }else {
                    Toast.makeText(MainActivity.this,
                            "숫자를 입력하세요!!!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // 보여주기
        findViewById(R.id.btnShow).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = new ProgressDialog(MainActivity.this);
                dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                dialog.setMessage("데이터를 확인하는 중입니다 ...");
                dialog.setCanceledOnTouchOutside(false);
                dialog.show();
            }
        });

        // 닫기
        findViewById(R.id.btnClose).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(dialog != null){
                    dialog.dismiss(); // 해산
                }
            }
        });

        // 씨크바
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override  //                                  내가 변경한 값, 유저가 변경했는가
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                editText.setText(String.valueOf(progress));
                Log.d(TAG, "onProgressChanged: " + fromUser);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }
}