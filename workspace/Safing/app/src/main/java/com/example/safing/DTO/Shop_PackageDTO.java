package com.example.safing.DTO;

public class Shop_PackageDTO {
    private int resId ;
    private String textStr;

    public Shop_PackageDTO(int resId, String textStr) {
        this.resId = resId;
        this.textStr = textStr;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    public String getTextStr() {
        return textStr;
    }

    public void setTextStr(String textStr) {
        this.textStr = textStr;
    }
}
