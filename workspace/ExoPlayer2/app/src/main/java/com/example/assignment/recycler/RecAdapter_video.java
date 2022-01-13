package com.example.assignment.recycler;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assignment.R;
import com.example.assignment.Video_EXO.VideoItem;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.source.hls.HlsMediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import java.util.ArrayList;

public class RecAdapter_video extends RecyclerView.Adapter<RecAdapter_video.VH> {
    Context context;
    ArrayList<VideoItem> videoItems;

    DataSource.Factory factory;
    ProgressiveMediaSource.Factory mediaFactory;

    public RecAdapter_video(Context context, ArrayList<VideoItem> videoItems) {
        this.context = context;
        this.videoItems = videoItems;

        factory = new DefaultDataSourceFactory(context, "Ex90ExoPlayer"); // 매개 두번째는 임의로 그냥 적음
        mediaFactory = new ProgressiveMediaSource.Factory(factory);
    }

    public RecAdapter_video(Context context) {
        this.context = context;
        this.videoItems = new ArrayList<>();

        factory = new DefaultDataSourceFactory(context, "Ex90ExoPlayer"); // 매개 두번째는 임의로 그냥 적음
        mediaFactory = new ProgressiveMediaSource.Factory(factory);
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.vdieoitem, parent, false);
        VH holder = new VH(itemView);
        return holder;
    }

    public void addDto(VideoItem VideoItem) {
        videoItems.add(VideoItem);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        VideoItem videoItem = videoItems.get(position);
        holder.setDto(videoItem);
        String sample = videoItem.getVideoUrl();

        MediaSource mediaSource = holder.buildMediaSource(Uri.parse(sample));

        //prepare
        holder.player.prepare(mediaSource, true, false);

    }

    @Override
    public int getItemCount() {
        return videoItems.size();
    }

    //inner class..
    class VH extends RecyclerView.ViewHolder {

        TextView tvTitle;
        TextView tvTag;

        private PlayerView exoPlayerView;
        private SimpleExoPlayer player;

        public VH(@NonNull View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.video_title);
            tvTag = itemView.findViewById(R.id.video_tag);
            exoPlayerView = itemView.findViewById(R.id.exoPlayerView_adapter);

            initializePlayer();

        }

        private void initializePlayer() {
            if (player == null) {

            /*DefaultRenderersFactory renderersFactory = new DefaultRenderersFactory(this.getApplicationContext());
            DefaultTrackSelector trackSelector = new DefaultTrackSelector();
            DefaultLoadControl loadControl = new DefaultLoadControl();

            player = ExoPlayerFactory.newSimpleInstance(
                    this.getApplicationContext(),
                    renderersFactory,
                    trackSelector,
                    loadControl);*/

                player = ExoPlayerFactory.newSimpleInstance(context);

                //플레이어 연결
                exoPlayerView.setPlayer(player);

                //컨트롤러 없애기
                //exoPlayerView.setUseController(false);

                //사이즈 조절
                //exoPlayerView.setResizeMode(AspectRatioFrameLayout.RESIZE_MODE_ZOOM); // or RESIZE_MODE_FILL

                //음량조절
                //player.setVolume(0);

                //프레임 포지션 설정
                //player.seekTo(currentWindow, playbackPosition);

            }

            String sample = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4";

            MediaSource mediaSource = buildMediaSource(Uri.parse(sample));

            //prepare
            player.prepare(mediaSource, true, false);

            //start,stop
            player.setPlayWhenReady(true);
        }
        private MediaSource buildMediaSource(Uri uri) {

            String userAgent = Util.getUserAgent(context, "blackJin");

            if (uri.getLastPathSegment().contains("mp3") || uri.getLastPathSegment().contains("mp4")) {

                return new ExtractorMediaSource.Factory(new DefaultHttpDataSourceFactory(userAgent))
                        .createMediaSource(uri);

            } else if (uri.getLastPathSegment().contains("m3u8")) {

                //com.google.android.exoplayer:exoplayer-hls 확장 라이브러리를 빌드 해야 합니다.
                return new HlsMediaSource.Factory(new DefaultHttpDataSourceFactory(userAgent))
                        .createMediaSource(uri);

            } else {

                return new ExtractorMediaSource.Factory(new DefaultDataSourceFactory(context, userAgent))
                        .createMediaSource(uri);
            }

        }




        public void setDto(VideoItem videoitem) {
            tvTitle.setText(videoitem.getTitle());
            tvTag.setText(videoitem.getSubTitle());
        }

    }

}