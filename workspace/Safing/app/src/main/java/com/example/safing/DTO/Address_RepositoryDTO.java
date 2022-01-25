package com.example.safing.DTO;

public class Address_RepositoryDTO {
  private int  addr_post ;
  private String  addr_basic, addr_detail;

    public int getAddr_post() {
        return addr_post;
    }

    public void setAddr_post(int addr_post) {
        this.addr_post = addr_post;
    }

    public String getAddr_basic() {
        return addr_basic;
    }

    public void setAddr_basic(String addr_basic) {
        this.addr_basic = addr_basic;
    }

    public String getAddr_detail() {
        return addr_detail;
    }

    public void setAddr_detail(String addr_detail) {
        this.addr_detail = addr_detail;
    }
}
