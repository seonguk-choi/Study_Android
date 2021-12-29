package com.example.new03_recyclerpager.recyclerTest;

public class RecTestDTO {
    private int imgv1, imgv2;
    private String tv1, tv2;

    public RecTestDTO(int imgv1, int imgv2, String tv1, String tv2) {
        this.imgv1 = imgv1;
        this.imgv2 = imgv2;
        this.tv1 = tv1;
        this.tv2 = tv2;
    }

    public int getImgv1() {
        return imgv1;
    }

    public void setImgv1(int imgv1) {
        this.imgv1 = imgv1;
    }

    public int getImgv2() {
        return imgv2;
    }

    public void setImgv2(int imgv2) {
        this.imgv2 = imgv2;
    }

    public String getTv1() {
        return tv1;
    }

    public void setTv1(String tv1) {
        this.tv1 = tv1;
    }

    public String getTv2() {
        return tv2;
    }

    public void setTv2(String tv2) {
        this.tv2 = tv2;
    }
}
