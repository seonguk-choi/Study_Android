package com.example.my29_recyclerview1;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SingerAdapter extends
        RecyclerView.Adapter<SingerAdapter.ViewHoler> {
    private static final String TAG = "main:SingerAdapter";

    // 메인에게 넘겨 받는것
    Context context;
    ArrayList<SingerDTO> dtos;

    LayoutInflater inflater;

    // 생성자로 메인에게 넘겨받은것을 연결
    public SingerAdapter(Context context, ArrayList<SingerDTO> dtos) {
        this.context = context;
        this.dtos = dtos;

        inflater = LayoutInflater.from(this.context);
    }

    ////////////////////////////////////////////////////////

    // 매소드는 여기에 만든다
    // dtos에 dto 추가하는 매소드
    public void addDto(SingerDTO dto){
        dtos.add(dto);
    }

    // dtos의 특정위치에 dto를 삭제할수 있는 매소드
    public void delDto(int position){
        dtos.remove(position);
    }

    ////////////////////////////////////////////////////////


    // 화면을 인플레이트 시킨다
    @NonNull
    @Override
    public ViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.singerview,
                parent, false);

        return new ViewHoler(itemView);
    }

    // 인플레이트 시킨 화면에 데이터를 셋팅시킨다
    @Override
    public void onBindViewHolder(@NonNull ViewHoler holder, @SuppressLint("RecyclerView") int position) {
        Log.d(TAG, "onBindViewHolder: " + position);

        // dtos에 있는 데이터를 각각 불러온다
        SingerDTO dto = dtos.get(position);
        // 불러온 데이터를 ViewHolder에 만들어 놓은 setDto를 사용하여 데이터 셋팅시킨다
        holder.setDto(dto);

        // 리싸이클러뷰에 항목을 선택했을때 그 항목을 가져오는 리스너
        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SingerDTO dto1 = dtos.get(position);
                Toast.makeText(context,
                        "이름 : " + dto1.getName(), Toast.LENGTH_SHORT).show();
            }
        });

        // 쓰레기통 클릭하여 항목 삭제 리스너
        holder.ivTrash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //dtos.remove(position);
                delDto(position);
                notifyDataSetChanged();
            }
        });

    }

    // dtos에 저장된 dto 갯수
    @Override
    public int getItemCount() {
        return dtos.size();
    }

    // 3. xml 파일에서 사용된 모든 변수를 adapter에서 클래스로 선언한다
    public class ViewHoler extends RecyclerView.ViewHolder{
        // singerview.xml 에 사용된 모든 위젯을 정의한다
        TextView tvName, tvMobile;
        ImageView ivImage, ivTrash;
        LinearLayout parentLayout;

        // singerview에 정의한 아이디를 찾아 연결시킨다(생성자)
        public ViewHoler(@NonNull View itemView) {
            super(itemView);

            parentLayout = itemView.findViewById(R.id.parentLayout);
            tvName = itemView.findViewById(R.id.tvName);
            tvMobile = itemView.findViewById(R.id.tvMobile);
            ivImage = itemView.findViewById(R.id.ivImage);
            ivTrash = itemView.findViewById(R.id.ivTrash);
        }

        // 함수를 만들어서 singerview에 데이터를 연결시킨다.
        public void setDto(SingerDTO dto){
            tvName.setText(dto.getName());
            tvMobile.setText(dto.getMobile());
            ivImage.setImageResource(dto.getResId());
        }

    }

}
