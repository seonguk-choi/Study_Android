package com.example.safing.async;

import android.view.View;

import com.example.safing.adapter.Shop_Package_Apdater;
import com.example.safing.adapter.Shop_Rec_Adapter;

public interface OnItemClick_product_Listener {
    public void onItemClick_product(Shop_Rec_Adapter.ViewHolder holderm,
                                    View view, int position);
}
