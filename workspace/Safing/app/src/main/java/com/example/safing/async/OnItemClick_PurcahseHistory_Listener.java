package com.example.safing.async;

import android.view.View;

import com.example.safing.shop.adapter.Product_PurchaseHistory_Rec_Adapter;
public interface OnItemClick_PurcahseHistory_Listener {
    void onItemClick_PurchaseHistory(Product_PurchaseHistory_Rec_Adapter.ViewHolder holderm,
                             View view, int position);
    void onItemClick_PurchaseHistory_reivew(Product_PurchaseHistory_Rec_Adapter.ViewHolder holderm,
                                     View view, int position);
}
