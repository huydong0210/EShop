package com.group2.eshopfe.cart.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.group2.eshopfe.DTO.OrderDetailsDTO;

public class CartOrderDetailsAdapter extends ArrayAdapter<OrderDetailsDTO> {
    private Activity context;
    private int resource;
    ImageView imageViewCartOrderDetailsImage;
    TextView textViewCartOrderDetailsPrice, textViewCartOrderDetailsName, textViewCartOrderDetailsAmount, textViewCartOrderDetailsTotalPrice;

    public CartOrderDetailsAdapter(@NonNull Activity context, int resource) {
        super(context, resource);
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater layoutInflater = this.context.getLayoutInflater();
        View cartOrderDetailsItemView = layoutInflater.inflate(this.resource, null);
        addControls();
        addEvents();

        return null;
    }
    private void addControls(){


    }
    private void addEvents(){

    }
}
