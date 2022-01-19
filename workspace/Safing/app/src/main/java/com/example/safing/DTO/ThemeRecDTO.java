package com.example.safing.DTO;

public class ThemeRecDTO {
    private int resId ;
    private String textStr;

    public ThemeRecDTO(int rec_item_theme, String s) {
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
