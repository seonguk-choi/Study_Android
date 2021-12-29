package com.example.newlastproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edt_id = findViewById(R.id.edt_id);
        edt_pw = findViewById(R.id.edt_pw);
        btn_login = findViewById(R.id.btn1);
        kakaoimg = findViewById(R.id.kakaobtn);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = edt_id.getText() + "";
                String pw = edt_pw.getText() + "";

                if (id.equals("aaa") && pw.equals("aaa")) {
                    Toast.makeText(LoginActivity.this, "로그인되었습니다.", Toast.LENGTH_SHORT).show();
                    intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.putExtra("id", id);
                    intent.putExtra("pw", pw);
                    startActivity(intent);
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
                    intent = new Intent(LoginActivity.this, MainActivity.class);

                    startActivity(intent);
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