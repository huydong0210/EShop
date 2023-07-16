package com.group2.eshopfe.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.group2.eshopfe.R;
import com.group2.eshopfe.modul.Product;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class ClothesAdapter extends BaseAdapter {
    Context context;
    ArrayList<Product> arrayListClothes;

    public ClothesAdapter(Context context, ArrayList<Product> arrayListClothes) {
        this.context = context;
        this.arrayListClothes = arrayListClothes;
    }

    @Override
    public int getCount() {
        return arrayListClothes.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayListClothes.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
    public class ViewHolder{
        public TextView txtNameClothes, txtPriceClothes, txtDetailClothes;
        public ImageView imageClothes;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ClothesAdapter.ViewHolder viewHolder = null;
        if (view == null){
            viewHolder = new ClothesAdapter.ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.clothing_directory,null);
            viewHolder.txtNameClothes = view.findViewById(R.id.textviewtenao);
            viewHolder.imageClothes = view.findViewById(R.id.imageao);
            viewHolder.txtDetailClothes = view.findViewById(R.id.textviewmotaao);
            viewHolder.txtPriceClothes = view.findViewById((R.id.textviewgiaao));
            view.setTag(viewHolder);
        } else {
            viewHolder = (ClothesAdapter.ViewHolder) view.getTag();
        }
        Product product = (Product) getItem(i);
        viewHolder.txtNameClothes.setText(product.getNameProduct());
        DecimalFormat decimalFormat =new DecimalFormat("###,###,###");
        viewHolder.txtPriceClothes.setText("Giá: "+ decimalFormat.format(product.getPriceProduct())+"Đ");
        viewHolder.txtDetailClothes.setMaxLines(2);
        viewHolder.txtDetailClothes.setEllipsize(TextUtils.TruncateAt.END);
        viewHolder.txtDetailClothes.setText(product.getDetailsProduct());
        return null;
    }
}
