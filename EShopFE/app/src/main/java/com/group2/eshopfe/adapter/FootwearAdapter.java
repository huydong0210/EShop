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

public class FootwearAdapter extends BaseAdapter {
    Context context;
    ArrayList<Product> arrayListFootwear;

    public FootwearAdapter(Context context, ArrayList<Product> arrayListFootwear) {
        this.context = context;
        this.arrayListFootwear = arrayListFootwear;
    }

    @Override
    public int getCount() {
        return arrayListFootwear.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayListFootwear.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
    public class ViewHolder{
        public TextView txtNameFootwear, txtPriceFootwear, txtDetailFootwear;
        public ImageView imageFootwear;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        FootwearAdapter.ViewHolder viewHolder = null;
        if (view == null){
            viewHolder = new FootwearAdapter.ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.footwear_directory,null);
            viewHolder.txtNameFootwear = view.findViewById(R.id.textviewtendep);
            viewHolder.imageFootwear = view.findViewById(R.id.imagedep);
            viewHolder.txtDetailFootwear = view.findViewById(R.id.textviewmotadep);
            viewHolder.txtPriceFootwear = view.findViewById((R.id.textviewgiadep));
            view.setTag(viewHolder);
        } else {
            viewHolder = (FootwearAdapter.ViewHolder) view.getTag();
        }
        Product product = (Product) getItem(i);
        viewHolder.txtNameFootwear.setText(product.getNameProduct());
        DecimalFormat decimalFormat =new DecimalFormat("###,###,###");
        viewHolder.txtPriceFootwear.setText("Giá: "+ decimalFormat.format(product.getPriceProduct())+"Đ");
        viewHolder.txtDetailFootwear.setMaxLines(2);
        viewHolder.txtDetailFootwear.setEllipsize(TextUtils.TruncateAt.END);
        viewHolder.txtDetailFootwear.setText(product.getDetailsProduct());
        return null;
    }
}
