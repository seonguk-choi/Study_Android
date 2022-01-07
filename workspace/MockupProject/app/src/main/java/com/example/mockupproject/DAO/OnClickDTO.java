package com.example.mockupproject.DAO;

public class OnClickDTO {
    private int clickId;
    private String msg;

    public OnClickDTO(int clickId, String msg) {
        this.clickId = clickId;
        this.msg = msg;
    }

    public int getClickId() {
        return clickId;
    }

    public void setClickId(int clickId) {
        this.clickId = clickId;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
