package com.example.new03_recyclerpager.recycler;

//Recycler Adapter 마스터 -> ViewPager2 (Recycler Adapter를 사용)
//viewPager 가로<-> viewPager2 가로 세로
//ViewHoler를 강제한다. (우리가 xml에 만들어 놓은 위젯의 묶음이 되는 클래스)
//ImageView, TextView <- 묶어 놓은 클래스를 반드시 만들고 이벤트 핸들링을 여기서 해라.

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.new03_recyclerpager.R;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class RecAdapter extends RecyclerView.Adapter<RecAdapter.ViewHolder> {
    //2. 아이템 마다 보여술 정보를 가지고 있는 DTO를 하나로 묶은 Collection 구조를 받을 수 있께
    //필드로 선언, LayoutInflater 사용을 위한 준비를 함.
    // (1) Activity로 받아옴 (Context) (2) static 으로 getApplicationContext 사용(지원안하는 기능도 있음)
    //Context<- Toast, LayoutInflage, 등 화면에 떠이는 객체에서
    //할 수 있는 기능들을 처리 할 수가 있다.
    //일반클래스 에도 Context를 넘기면 화면 제어를 할 수 있음
    //RecAdapter <- 일반클래스

    //메인에서 넘겨 받을 필드
    //생정자 메소드를 만듬
    //세개를 입력받거나 두개를 입력받아서 LayouInflater까지 null이 아니게
    Context context;
    ArrayList<RecDTO> list;
    LayoutInflater inflater;

    public RecAdapter(Context context, ArrayList<RecDTO> list) {
        this.context = context;
        this.list = list;
        this.inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //액티비티의 소스를 줄일 수 있음
        //context 자제를 받아왔기 때문에 context를 이용하는 것이 메모리에 더 효율적이다.
    }

    public RecAdapter(Context context, ArrayList<RecDTO> list, LayoutInflater inflater) {
        this.context = context;
        this.list = list;
        this.inflater = inflater;
    }

    //3. 화면을 인플레이터 시킨다. (리사이클러의 아이템 하나마다 붙을 xml을 연결시킨다.)
    //xml로 연결시킨 view를 ViewHolder에 넘겨줌 (== ViewHolder를 view를 통해서 find)
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.recycleritem, parent,false);
        //ViewHolder viewHolder = new ViewHolder(itemView);
        //return viewHolder;

        return new ViewHolder(itemView);
    }

    //4. 아이템이 세팅되고 나서의 처리를 의미함.
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //동영상 넘어가면 바로 재생되게 설정
        Toast.makeText(context, list.get(position).getTextStr(), Toast.LENGTH_SHORT).show();
    }

    //5. 총 아이템의 갯수 지정
    @Override
    public int getItemCount() {
        return list.size();
    }

    //1.***RecyclerView.ViewHolder 상속 받은 클래스 ViewHolder를 만들어 줌***
    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imgv1;
        TextView tv1;
        //xml에 있는 위젯을 전역변수로 선언.

        //이벤트 처리 클릭, 등....
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgv1 = itemView.findViewById(R.id.imgv1);
            tv1 = itemView.findViewById(R.id.tv1);
            tv1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }
}
