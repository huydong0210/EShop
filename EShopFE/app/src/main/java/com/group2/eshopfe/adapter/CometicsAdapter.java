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

public class CometicsAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Product> arrayListCometics;

    public CometicsAdapter (Context context, ArrayList<Product> arrayListCometics) {
        this.context = context;
        this.arrayListCometics = arrayListCometics;
    }

    @Override
    public int getCount() {
        return arrayListCometics.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayListCometics.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
    public class ViewHolder{
        public TextView txtNameCometics, txtPriceCometics, txtDetailCometics;
        public ImageView imageCometics;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        CometicsAdapter.ViewHolder viewHolder = null;
        if (view == null){
            viewHolder = new CometicsAdapter.ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.cosmetics_directory,null);
            viewHolder.txtNameCometics = view.findViewById(R.id.textviewtenmypham);
            viewHolder.imageCometics = view.findViewById(R.id.imagemypham);
            viewHolder.txtDetailCometics = view.findViewById(R.id.textviewmotamypham);
            viewHolder.txtPriceCometics = view.findViewById((R.id.textviewgiamypham));
            view.setTag(viewHolder);
        } else {
            viewHolder = (CometicsAdapter.ViewHolder) view.getTag();
        }
        Product product = (Product) getItem(i);
        viewHolder.txtNameCometics.setText(product.getNameProduct());
        DecimalFormat decimalFormat =new DecimalFormat("###,###,###");
        viewHolder.txtPriceCometics.setText("Giá: "+ decimalFormat.format(product.getPriceProduct())+"Đ");
        viewHolder.txtDetailCometics.setMaxLines(2);
        viewHolder.txtDetailCometics.setEllipsize(TextUtils.TruncateAt.END);
        viewHolder.txtDetailCometics.setText(product.getDetailsProduct());
        return null;
    }
}
