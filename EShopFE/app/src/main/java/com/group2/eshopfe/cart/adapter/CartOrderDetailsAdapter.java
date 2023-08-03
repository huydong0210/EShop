package com.group2.eshopfe.cart.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.group2.eshopfe.DTO.OrderDetailsDTO;
import com.group2.eshopfe.R;
import com.group2.eshopfe.common.Utils;

public class CartOrderDetailsAdapter extends ArrayAdapter<OrderDetailsDTO> {
    private Activity context;
    private int resource;


    public CartOrderDetailsAdapter(@NonNull Activity context, int resource) {
        super(context, resource);
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater layoutInflater = this.context.getLayoutInflater();
        View cartOrderDetailsItemView = layoutInflater.inflate(this.resource, null);
        ImageView imageViewCartOrderDetailsImage = cartOrderDetailsItemView.findViewById(R.id.imageViewCartOrderDetailsImage);
        TextView textViewCartOrderDetailsPrice = cartOrderDetailsItemView.findViewById(R.id.textViewCartOrderDetailsPrice);
        TextView textViewCartOrderDetailsName = cartOrderDetailsItemView.findViewById(R.id.textViewCartOrdeDetailsName);
        TextView textViewCartOrderDetailsAmount = cartOrderDetailsItemView.findViewById(R.id.textViewCartOrderDetailsAmount);

        Button buttonCartOrderDetailsDeleteItem = cartOrderDetailsItemView.findViewById(R.id.buttonCartOrderDetailsDeleteItem);

        OrderDetailsDTO orderDetailsDTO = getItem(position);
        imageViewCartOrderDetailsImage.setImageBitmap(Utils.convertBytesToBitMap(orderDetailsDTO.getProductDTO().getImage()));
        textViewCartOrderDetailsPrice.setText(Float.toString(orderDetailsDTO.getProductDTO().getPrice()));
        textViewCartOrderDetailsName.setText(orderDetailsDTO.getProductDTO().getProductName().substring(0, 18) + "...");
        textViewCartOrderDetailsAmount.setText("Số lượng: " + orderDetailsDTO.getAmount());





        return cartOrderDetailsItemView;
    }


}
