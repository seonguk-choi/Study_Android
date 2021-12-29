package com.example.new03_recyclerpager.recycler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;

import com.example.new03_recyclerpager.R;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class RecyclerActivity extends AppCompatActivity {
    //1.xml에 리사이클러뷰 추가
    //2.java <-> 연결 id로 찾아서
    //리사이클러뷰에 붙일 디자인 xml만들기 -> ex)표현할 정보가 TextView, ImageView... 아이템만들기
    //한칸에 보여줄 정보
    //DB에서 가져온 정보를 칸마다 어떻게 표현할지 정한 것 (item) == Collection.size()
    //ex 카카오톡 채팅목록(DB) select * from kakaoMember where id = 'kakakooid' == 100 == item
    //3. 어댑터 만들기
    RecyclerView recyclerView;
    ArrayList<RecDTO> list;
    LayoutInflater inflater;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);
        //메소드 정의 : 받을 데이터 타입이나 데이터 갯수, 순서, 등을 정의해 놓음
        //메소드 호출 : 메소드의 정의 된 데이터 타입과 갯수, 순서 등을 맞춰서 값을 보내주는 것

        recyclerView = findViewById(R.id.recyclerview);

        list = new ArrayList<>();
        list.add(new RecDTO(R.drawable.ic_launcher_background,"text1"));
        list.add(new RecDTO(R.drawable.ic_launcher_foreground,"text2"));
        list.add(new RecDTO(R.drawable.ic_launcher_foreground,"text3"));
        list.add(new RecDTO(R.drawable.ic_launcher_foreground,"text4"));

        //inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        //LayoutInflater inflater1 = LayoutInflater.from(RecyclerActivity.this);
        //RecAdapter adapter = new RecAdapter(RecyclerActivity.this, list, inflater1);

        RecAdapter adapter = new RecAdapter(RecyclerActivity.this, list);

        //ListView 가로 <-> RecyclerView 가로, 세로, 지그재그
        //Adapter에 Manager를 넘겨주면 가로 세로를 선택하는 기능 구현
        LinearLayoutManager layoutManager = new LinearLayoutManager(
                RecyclerActivity.this, RecyclerView.HORIZONTAL, false
        );

        recyclerView.setLayoutManager(layoutManager);
        //리사이클러뷰를 세로모드로 설정

        recyclerView.setAdapter(adapter);
    }
}