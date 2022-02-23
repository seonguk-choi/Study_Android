package com.example.safing.shop.VO;

import java.util.ArrayList;

public class Product_DetailVO {
    private ArrayList<String> imagelist;
    private int product_num           ;
    private String product_name       ;
    private int product_price         ;
    private double	rating			  ;
    private int		re_count		  ;
    private int		product_stock	  ;
    private int     order_count       ;
    private String  product_kind      ;
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

    public String getProduct_kind() {
        return product_kind;
    }

    public void setProduct_kind(String product_kind) {
        this.product_kind = product_kind;
    }

    public int getOrder_count() {
        return order_count;
    }

    public void setOrder_count(int order_count) {
        this.order_count = order_count;
    }

    public int getProduct_stock() {
        return product_stock;
    }

    public void setProduct_stock(int product_stock) {
        this.product_stock = product_stock;
    }

    public ArrayList<String> getImagelist() {
        return imagelist;
    }

    public void setImagelist(ArrayList<String> imagelist) {
        this.imagelist = imagelist;
    }

    public int getProduct_num() {
        return product_num;
    }

    public void setProduct_num(int product_num) {
        this.product_num = product_num;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public int getProduct_price() {
        return product_price;
    }

    public void setProduct_price(int product_price) {
        this.product_price = product_price;
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
