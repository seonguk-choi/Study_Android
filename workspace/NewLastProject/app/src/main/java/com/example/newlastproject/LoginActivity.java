package com.example.newlastproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.newlastproject.transactivity.TransActivity;
import com.kakao.sdk.auth.model.OAuthToken;
import com.kakao.sdk.common.KakaoSdk;
import com.kakao.sdk.user.UserApiClient;
import com.kakao.sdk.user.model.Account;
import com.kakao.sdk.user.model.Profile;

import kotlin.Unit;
import kotlin.jvm.functions.Function2;

public class LoginActivity extends AppCompatActivity {
    EditText edt_id, edt_pw;
    Button btn_login;
    ImageView kakaoimg;
    CheckBox chk_auto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edt_id = findViewById(R.id.edt_id);
        edt_pw = findViewById(R.id.edt_pw);
        btn_login = findViewById(R.id.btn1);
        kakaoimg = findViewById(R.id.kakaobtn);
        chk_auto = findViewById(R.id.chk_auto);


        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = edt_id.getText() + "";
                String pw = edt_pw.getText() + "";

                if (id.equals("aaa") && pw.equals("aaa")) {
                    Toast.makeText(LoginActivity.this, "로그인되었습니다.", Toast.LENGTH_SHORT).show();
                    //intent = new Intent(LoginActivity.this, MainActivity.class);
                    //intent.putExtra("id", id);
                    //intent.putExtra("pw", pw);
                    //startActivity(intent);
                    goMain();
                } else {
                    Toast.makeText(LoginActivity.this, "아이디 또는 비밀번호를 확인하세요", Toast.LENGTH_SHORT).show();
                }
            }
        });
        //================ Kotlin ================================================
      /*  // 로그인 공통 callback 구성
        val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
        if (error != null) {
            Log.e(TAG, "로그인 실패", error)
        }
        else if (token != null) {
            Log.i(TAG, "로그인 성공 ${token.accessToken}")
        }*/

        Function2<OAuthToken, Throwable, Unit> kakaoCallback = new Function2<OAuthToken, Throwable, Unit>() {

            @Override
            public Unit invoke(OAuthToken oAuthToken, Throwable throwable) {
                if (oAuthToken != null) {
                    Toast.makeText(LoginActivity.this, "정보를 받아옴", Toast.LENGTH_SHORT).show();
                    getKakaoinfo();
                }
                if (throwable != null) {
                    Toast.makeText(LoginActivity.this, "뭔가 오류가 남", Toast.LENGTH_SHORT).show();
                }
                return null;
            }
        };

        kakaoimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 카카오톡이 설치되어 있으면 카카오톡으로 로그인, 아니면 카카오계정으로 로그인
                if(UserApiClient.getInstance().isKakaoTalkLoginAvailable(LoginActivity.this)){
                    Toast.makeText(LoginActivity.this, "카카오톡 설치 됨", Toast.LENGTH_SHORT).show();
                    UserApiClient.getInstance().loginWithKakaoTalk(LoginActivity.this, kakaoCallback);
                } else {
                    Toast.makeText(LoginActivity.this, "카카오톡 설치 안됨", Toast.LENGTH_SHORT).show();
                    UserApiClient.getInstance().loginWithKakaoAccount(LoginActivity.this, kakaoCallback);

                }

                //================ Kotlin ================================================
               /* if (UserApiClient.instance.isKakaoTalkLoginAvailable(context)) {
                    UserApiClient.instance.loginWithKakaoTalk(context, callback = callback)
                } else {
                    UserApiClient.instance.loginWithKakaoAccount(context, callback = callback)
                }*/
            }
        });

        KakaoSdk.init(this, "9b9731073b0beae9fe2ece90460f7fda");

        Intent intent = new Intent(LoginActivity.this, TransActivity.class);
        startActivity(intent);



        SharedPreferences preferences = getPreferences(LoginActivity.MODE_PRIVATE);
        String id = preferences.getString("id",null);
        String pw = preferences.getString("pw",null);
        if(id != null && pw != null){
            edt_id.setText(id);
            edt_pw.setText(pw);
            btn_login.performClick();
        }
    }

    public void getKakaoinfo(){
        UserApiClient.getInstance().me((user, throwable) -> {
            if(throwable != null){
                //오류가 났을 때 어떤 오류인지 알아 볼 수  있음 KOE + 숫자
            }else {
                //[{}] json 구조처럼 바로 데이터가 있는게 아니라 Account라는 키로 한칸을 들어가고
                //여기안에서 또 profile이라는 칸으로 이동.
                Account kakaoAcount = user.getKakaoAccount();
                if(kakaoAcount != null){
                    Profile profile = kakaoAcount.getProfile();
                    if(profile != null){
                        Toast.makeText(LoginActivity.this, profile.getNickname()+"님 환영합니다.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
            return null;
        });
    }

    //현재 상태 -> 일반 로그인 1, kakao 로그인 2, Google Login3, FaceBook Login 4..
    //Naver Login 5
    public void goMain(){
        //is : boolean을 리턴하는 메소드의 명명규칙
        //자동로그인이 체크 되었을 때는 아이디와 비밀번호를 공유자원에 넣어둔다.
        //SharPrefrences <- 라는 공유자원에 id, pw <-?
        //일반 로그인의 경우 Editext에 있는 정보, 소셜 로그인?
        //DB 일반 id, pw ..., kakao, naver
        if(chk_auto.isChecked()){
            Toast.makeText(LoginActivity.this, "체크가 되었습니다.", Toast.LENGTH_SHORT).show();
            SharedPreferences preferences = getPreferences(LoginActivity.MODE_PRIVATE);
            //String id = preferences.getString("id", null);
            //초기에 우리가 공유자원에 어떤 데이터를 key로 지정해서 넣어놓지 않은 상태에서 공유자원에 데이터가
            //있는지 먼저 확인 하는 작업
            SharedPreferences.Editor editor = preferences.edit(); //Editor 리턴하는 메소드.
            editor.putString("id", edt_id.getText()+"");
            editor.putString("pw", edt_pw.getText()+"");
            editor.apply(); //commit이랑 같은 개념 데이터를 넣고 반드시 써줘야함.
            //id = preferences.getString("id", null);

        }else {
            //SharedPreferences 삭제
            //1.key를 이용해서 특정 데이터만 삭제하는 방법
            //2.공유자원에 있는 모든 데이터 삭제하는 방법 - 내가 사용하는 어플이, 캐시, 데이터가 큰 경우 삭제해보고 다시 어플 사용
            SharedPreferences preferences = getPreferences(LoginActivity.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            //1. remove (key)
            editor.remove("id");
            editor.apply();

            //2. clear -> 모든 정보가 삭제 되기 때문에 주의
            //editor.clear();
            //editor.apply();

            Toast.makeText(LoginActivity.this, "체크가 안되었습니다.", Toast.LENGTH_SHORT).show();
        }
       Intent intent = new Intent(LoginActivity.this, MainActivity.class);
       startActivity(intent);
    }

    //일반 회원가입 -> 아이디 1개
    //소셜 로그인 -> 아이디 1개
    //=======소셜키 설정할지 말지

/*    UserApiClient.instance.loginWithKakaoTalk(context) { token, error ->
        if (error != null) {
            Log.e(TAG, "로그인 실패", error)
        }
        else if (token != null) {
            Log.i(TAG, "로그인 성공 ${token.accessToken}")
        }
    }*/

}