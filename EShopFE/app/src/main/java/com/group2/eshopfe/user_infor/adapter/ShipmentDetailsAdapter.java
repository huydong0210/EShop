package com.group2.eshopfe.user_infor.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.group2.eshopfe.DTO.ShipmentDetailsDTO;
import com.group2.eshopfe.R;

public class ShipmentDetailsAdapter extends ArrayAdapter<ShipmentDetailsDTO> {
    private Activity context;
    private int resource;

    public ShipmentDetailsAdapter(@NonNull Activity context, int resource) {
        super(context, resource);
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = this.context.getLayoutInflater();
        View shipmentDetailsItemView = layoutInflater.inflate(this.resource,null);

        TextView textViewShipmentDetailsItemName = shipmentDetailsItemView.findViewById(R.id.textViewShipmentDetailsItemName);
        TextView textViewShipmentDetailsItemAddress = shipmentDetailsItemView.findViewById(R.id.textViewShipmentDetailsItemAddress);
        TextView textViewShipmentDetailsItemPhone = shipmentDetailsItemView.findViewById(R.id.textViewShipmentDetailsItemPhone);

        Button buttonShipmentDetailsItemDelete = shipmentDetailsItemView.findViewById(R.id.buttonShipmentDetailsItemDelete);

        ShipmentDetailsDTO shipmentDetails = getItem(position);
        textViewShipmentDetailsItemName.setText(shipmentDetails.getFullName());
        textViewShipmentDetailsItemPhone.setText(shipmentDetails.getPhone());
        textViewShipmentDetailsItemAddress.setText(shipmentDetails.getAddress());



        return shipmentDetailsItemView;
    }
}
