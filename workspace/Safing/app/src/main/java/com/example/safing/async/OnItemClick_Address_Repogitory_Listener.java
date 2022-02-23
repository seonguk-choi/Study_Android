package com.example.safing.async;

import android.view.View;

import com.example.safing.shop.adapter.Address_Repository_Rec_Adapter;



public interface OnItemClick_Address_Repogitory_Listener {
    void onItemClick_address_repository(Address_Repository_Rec_Adapter.ViewHolder holderm,
                                   View view, int position);
}
