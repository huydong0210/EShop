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

public class HousewearAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Product> arrayListHousewear;

    public HousewearAdapter(Context context, ArrayList<Product> arrayListHousewear) {
        this.context = context;
        this.arrayListHousewear = arrayListHousewear;
    }

    @Override
    public int getCount() {
        return arrayListHousewear.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayListHousewear.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
    public class ViewHolder{
        public TextView txtNameHousewear, txtPriceHousewear, txtDetailHousewear;
        public ImageView imageHousewear;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        HousewearAdapter.ViewHolder viewHolder = null;
        if (view == null){
            viewHolder = new HousewearAdapter.ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.housewear_directory,null);
            viewHolder.txtNameHousewear = view.findViewById(R.id.textviewtendgdung);
            viewHolder.imageHousewear = view.findViewById(R.id.imagedgd);
            viewHolder.txtDetailHousewear = view.findViewById(R.id.textviewmotadgdung);
            viewHolder.txtPriceHousewear = view.findViewById((R.id.textviewgiadgdung));
            view.setTag(viewHolder);
        } else {
            viewHolder = (HousewearAdapter.ViewHolder) view.getTag();
        }
        Product product = (Product) getItem(i);
        viewHolder.txtNameHousewear.setText(product.getNameProduct());
        DecimalFormat decimalFormat =new DecimalFormat("###,###,###");
        viewHolder.txtPriceHousewear.setText("Giá: "+ decimalFormat.format(product.getPriceProduct())+"Đ");
        viewHolder.txtDetailHousewear.setMaxLines(2);
        viewHolder.txtDetailHousewear.setEllipsize(TextUtils.TruncateAt.END);
        viewHolder.txtDetailHousewear.setText(product.getDetailsProduct());
        return null;
    }
}
