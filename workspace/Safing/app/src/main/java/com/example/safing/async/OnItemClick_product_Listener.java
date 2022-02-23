package com.example.safing.async;

import android.view.View;

import com.example.safing.shop.adapter.Shop_Rec_Adapter;

public interface OnItemClick_Product_Listener {
    void onItemClick_product(Shop_Rec_Adapter.ViewHolder holderm,
                                    View view, int position);
}
