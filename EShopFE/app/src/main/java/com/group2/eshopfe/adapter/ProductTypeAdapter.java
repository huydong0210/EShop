package com.group2.eshopfe.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.group2.eshopfe.R;
import com.group2.eshopfe.modul.ProductType;

import java.util.ArrayList;

public class ProductTypeAdapter extends BaseAdapter {
 private ArrayList<ProductType> arrayList;
 private Context context;

    public ProductTypeAdapter(ArrayList<ProductType> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }


    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
    public class ViewHolder{
        TextView txttenLoaiSanPham;
        ImageView imgLoaiSp;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if(view == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.product_type_list,null);
            viewHolder.txttenLoaiSanPham = view.findViewById(R.id.textviewLoaisp);
            viewHolder.imgLoaiSp = view.findViewById(R.id.imageLoaisp);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        ProductType productType = (ProductType) getItem(i);
        viewHolder.txttenLoaiSanPham.setText(productType.getNameProductType());
        return view;
    }
}
