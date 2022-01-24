package com.example.safing.async;

import android.view.View;

import com.example.safing.adapter.Shop_Package_Apdater;
import com.example.safing.adapter.Shop_Rec_Adapter;

public interface OnItemClickListener {
    public void onItemClick_package(Shop_Package_Apdater.ViewHolder holderm,
                            View view, int position);
    public void onItemClick_product(Shop_Rec_Adapter.ViewHolder holderm,
                                    View view, int position);
}
