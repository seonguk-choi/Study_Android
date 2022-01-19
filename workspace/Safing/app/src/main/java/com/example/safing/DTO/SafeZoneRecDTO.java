package com.example.safing.DTO;

public class SafeZoneRecDTO {
    private int resId ;
    private String textStr;

    public SafeZoneRecDTO(int resId, String textStr) {
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
