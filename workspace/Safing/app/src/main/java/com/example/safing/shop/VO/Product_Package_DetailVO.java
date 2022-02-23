package com.example.safing.shop.VO;

import java.util.ArrayList;
import java.util.List;

public class Product_Package_DetailVO {
    private ArrayList<String> imagelist;
    private ArrayList<String> kindlist;
    private int package_num           ;
    private String package_name       ;
    private double	rating			  ;
    private int		re_count		  ;
    private String  file_path_info	  ;
    private String  file_path	 	  ;

    public String getFile_path() {
        return file_path;
    }
    public void setFile_path(String file_path) {
        this.file_path = file_path;
    }

    public String getFile_path_info() {
        return file_path_info;
    }

    public void setFile_path_info(String file_path_info) {
        this.file_path_info = file_path_info;
    }

    public ArrayList<String> getImagelist() {
        return imagelist;
    }

    public void setImagelist(ArrayList<String> imagelist) {
        this.imagelist = imagelist;
    }

    public ArrayList<String> getKindlist() {
        return kindlist;
    }

    public void setKindlist(ArrayList<String> kindlist) {
        this.kindlist = kindlist;
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

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getRe_count() {
        return re_count;
    }

    public void setRe_count(int re_count) {
        this.re_count = re_count;
    }
}
