package com.example.my15_touchevent;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    // 찾아야할것 선언
    View view1, view2;
    ScrollView scrollView;
    TextView textView;

    GestureDetector detector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 선언한것 찾거나 이벤트 걸기
        view1 = findViewById(R.id.view1);
        view2 = findViewById(R.id.view2);
        scrollView = findViewById(R.id.scrollView);
        textView = findViewById(R.id.textView);

        // 그 부분을 터치했을때 이벤트
        view1.setOnTouchListener(new View.OnTouchListener() {
            @Override   //               , 내가 행한 모션 이벤트
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction(); // 어떤 모션을 했는가 : 이미 정의가 되어 있음
                float curX = event.getX(); // x축 위치
                float curY = event.getY(); // y축 위치

                if(action == MotionEvent.ACTION_DOWN){
                    printString("손꾸락 눌림 " + curX + ", " + curY);
                }else if(action == MotionEvent.ACTION_MOVE){
                    printString("손꾸락 움직임 " + curX + ", " + curY);
                }else if(action == MotionEvent.ACTION_UP){
                    printString("손꾸락 뗌 " + curX + ", " + curY);
                }

                return true;
            }
        });

        // view2에 대한 터치 이벤트 : 터치이벤트를 모션디텍션에 넘겨서
        // 어떤 모션을 했는지 알려준다
        view2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                detector.onTouchEvent(event);

                return true;
            }
        });

        detector = new GestureDetector(this, new GestureDetector.OnGestureListener() {
            // 화면이 눌렸을때
            @Override
            public boolean onDown(MotionEvent e) {
                printString("onDown() 호출됨");
                return true;
            }

            // 화면이 눌렸다 떼어지는 경우
            @Override
            public void onShowPress(MotionEvent e) {
                printString("onShowPress() 호출됨");
            }

            // 화면이 한손가락으로 눌렸다 떼어지는 경우
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                printString("onSingleTapUp() 호출됨");
                return true;
            }

            // 화면이 눌린채 일정한 속도와 방향으로 움직였다 떼는 경우
            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                printString("onScroll() 호출됨 => " + distanceX + ", " + distanceY);
                return true;
            }

            // 화면을 손가락으로 오랫동안 눌렀을 경우
            @Override
            public void onLongPress(MotionEvent e) {
                printString("onLongPress() 호출됨");
            }

            // 화면이 눌린채 가속도를 붙여 손가락을 움직였다 떼는 경우
            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                printString("onFling() 호출됨 => " + velocityX + ", " + velocityY);
                return true;
            }
        });

    }

    private void printString(String str) {
        textView.append(str + "\n");

        scrollView.fullScroll(View.FOCUS_DOWN);
    }

    // 키가 눌렸을 때 : 재정의해서 사용할수 있다
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            printString("시스템에서 [BACK]버튼이 눌렸습니다");
            return true;
        }else if(keyCode == KeyEvent.KEYCODE_VOLUME_UP){
            printString("시스템에서 [VOLUME_UP]버튼이 눌렸습니다");
            return true;
        }else if(keyCode == KeyEvent.KEYCODE_VOLUME_DOWN){
            printString("시스템에서 [VOLUME_DOWN]버튼이 눌렸습니다");
            return true;
        }

        return false;
    }
}



