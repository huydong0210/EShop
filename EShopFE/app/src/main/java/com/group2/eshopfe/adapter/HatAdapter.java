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

public class HatAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Product> arrayListHat;

    public HatAdapter(Context context, ArrayList<Product> arrayListHat) {
        this.context = context;
        this.arrayListHat = arrayListHat;
    }

    @Override
    public int getCount() {
        return arrayListHat.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayListHat.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
    public class ViewHolder{
        public TextView txtNameHat, txtPriceHat, txtDetailHat;
        public ImageView imageHat;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        HatAdapter.ViewHolder viewHolder = null;
        if (view == null){
            viewHolder = new HatAdapter.ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.hat_directory,null);
            viewHolder.txtNameHat = view.findViewById(R.id.textviewtenmu);
            viewHolder.imageHat = view.findViewById(R.id.imagemu);
            viewHolder.txtDetailHat = view.findViewById(R.id.textviewmotamu);
            viewHolder.txtPriceHat = view.findViewById((R.id.textviewgiamu));
            view.setTag(viewHolder);
        } else {
            viewHolder = (HatAdapter.ViewHolder) view.getTag();
        }
        Product product = (Product) getItem(i);
        viewHolder.txtNameHat.setText(product.getNameProduct());
        DecimalFormat decimalFormat =new DecimalFormat("###,###,###");
        viewHolder.txtPriceHat.setText("Giá: "+ decimalFormat.format(product.getPriceProduct())+"Đ");
        viewHolder.txtDetailHat.setMaxLines(2);
        viewHolder.txtDetailHat.setEllipsize(TextUtils.TruncateAt.END);
        viewHolder.txtDetailHat.setText(product.getDetailsProduct());
        return null;
    }
}
