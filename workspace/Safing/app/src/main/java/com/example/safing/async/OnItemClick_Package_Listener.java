package com.example.safing.async;

import android.view.View;

import com.example.safing.adapter.Shop_Package_Apdater;
import com.example.safing.adapter.Shop_Rec_Adapter;

public interface OnItemClick_Package_Listener {
    public void onItemClick_package(Shop_Package_Apdater.ViewHolder holderm,
                            View view, int position);

}
