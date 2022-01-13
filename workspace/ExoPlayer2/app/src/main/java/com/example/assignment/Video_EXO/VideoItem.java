package com.example.assignment.Video_EXO;

public class VideoItem {

    String title;
    String subTitle;
    String videoUrl;

    public VideoItem(String title, String subTitle, String videoUrl) {
        this.title = title;
        this.subTitle = subTitle;
        this.videoUrl = videoUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }
}
