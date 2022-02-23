package com.example.safing.shop.VO;

import android.widget.ImageView;

public class Review_ImageListVO {
    private String img_filepath;
    private ImageView imageView;

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public String getImg_filepath() {
        return img_filepath;
    }

    public void setImg_filepath(String img_filepath) {
        this.img_filepath = img_filepath;
    }

}
