package com.example.safing.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.safing.R;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;

public class Moive_Adapter2 extends RecyclerView.Adapter<Moive_Adapter2.ViewHolder> {
    Context context;
    LayoutInflater inflater;

    DataSource.Factory factory;
    ProgressiveMediaSource.Factory mediaFactory;

    public Moive_Adapter2(Context context) {
        this.context = context;
        this.inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        factory = new DefaultDataSourceFactory(context, "Ex90ExoPlayer"); // 매개 두번째는 임의로 그냥 적음
        mediaFactory = new ProgressiveMediaSource.Factory(factory);
    }

    @NonNull
    @Override
    public Moive_Adapter2.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_pager_movie, parent, false);
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
        public  void bind(Moive_Adapter1.ViewHolder holder, int postion){

        }
    }

}
