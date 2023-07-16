package com.group2.eshopfe.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.group2.eshopfe.modul.ProductType;

import java.util.ArrayList;

public class ProductTypeAdapter extends BaseAdapter {
 ArrayList<ProductType> arrayList;
 Context context;

    public ProductTypeAdapter(ArrayList<ProductType> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }


    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return null;
    }
}
