package com.example.new03_recyclerpager.recycler2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.new03_recyclerpager.R;

//2. extends <- RecyclerView.Adapter<> 상속받기
public class RecAdapter2 extends RecyclerView.Adapter<RecAdapter2.ViewHolder>{


    //3.Context, ArrayList<>, LayoutInflater 생성자메소드 만들기
    //Layout을 붙이기 위한(Inflater)
    //Inflater, Context를 가지고 있는 화면단에서 받아올 수가 있음(현재 클래스)
    Context context;
    LayoutInflater inflater;

    public RecAdapter2(Context context) {
        this.context = context;
        this.inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    //4. ※ 리사이클러뷰 한칸마다 보여줄 xml을 연결시켜서 붙여주는 부분 (ViewHolder라는 ViewGroup)
    //ImageView, TextView.. 묶어 놓는 단계
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.recycleritem2, parent, false);

        return new ViewHolder(itemView);
    }

    //5. 위에 단계가 끝나고 ViewHolder라는 그룹을 만든 후 Bind라는 작업(list에 있는 내용 <-> ItemView)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    //6.끝(Adapter 완성) -> Activity가서 4번 작업 시작
    @Override
    public int getItemCount() {
        return 20;
    }

    //1. Viewholder 클래스(widget들을 한번에 묶는다) 만든다
    public class ViewHolder extends RecyclerView.ViewHolder{

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
