package com.example.safing.shop.VO;

import java.io.Serializable;

public class Product_PackageVO {
    private int package_num           ;
    private String package_name       ;
    private int package_price         ;
    private String file_name          ;
    private String file_path          ;
    private String tag_key            ;

    public String getTag_key() {
        return tag_key;
    }

    public void setTag_key(String tag_key) {
        this.tag_key = tag_key;
    }

    public int getPackage_num() {
        return package_num;
    }

    public void setPackage_num(int package_num) {
        this.package_num = package_num;
    }

    public String getPackage_name() {
        return package_name;
    }

    public void setPackage_name(String package_name) {
        this.package_name = package_name;
    }

    public int getPackage_price() {
        return package_price;
    }

    public void setPackage_price(int package_price) {
        this.package_price = package_price;
    }

    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }

    public String getFile_path() {
        return file_path;
    }

    public void setFile_path(String file_path) {
        this.file_path = file_path;
    }
}
