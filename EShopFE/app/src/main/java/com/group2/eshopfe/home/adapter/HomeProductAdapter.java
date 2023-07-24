package com.group2.eshopfe.home.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.group2.eshopfe.R;
import com.group2.eshopfe.home.model.HomeProduct;

public class HomeProductAdapter extends ArrayAdapter<HomeProduct> {
    private Activity context;
    private int resource;

    public HomeProductAdapter(@NonNull Activity context, int resource) {
        super(context, resource);
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = this.context.getLayoutInflater();
        View homeProductItemView = layoutInflater.inflate(this.resource, null);

//        ImageView imageViewHomeProduct= homeProductItemView.findViewById(R.id.imageViewHomeProduct);
        TextView textViewHomeProductPrice= homeProductItemView.findViewById(R.id.textViewHomeProductPrice);
        TextView textViewHomeProductProductName = homeProductItemView.findViewById(R.id.textViewHomeProductProductName);
        TextView textViewHomeProductDescription = homeProductItemView.findViewById(R.id.textViewHomeProductDescription);
        Button buttonHomeProductAddToCart  = homeProductItemView.findViewById(R.id.buttonHomeProductAddToCart);


        HomeProduct sp = getItem(position);
//        imageViewHomeProduct.setImageResource(sp.getImage());
        textViewHomeProductProductName.setText(sp.getProductName());
        textViewHomeProductPrice.setText(sp.getPrice() + "");
        textViewHomeProductDescription.setText(sp.getDescription());


        return homeProductItemView;
    }
}