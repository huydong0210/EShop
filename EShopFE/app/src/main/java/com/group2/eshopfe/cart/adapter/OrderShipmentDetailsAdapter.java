package com.group2.eshopfe.cart.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.group2.eshopfe.DTO.OrderDetailsDTO;
import com.group2.eshopfe.DTO.ShipmentDetailsDTO;
import com.group2.eshopfe.R;
import com.group2.eshopfe.common.Constant;

import org.w3c.dom.Text;

public class OrderShipmentDetailsAdapter extends ArrayAdapter<ShipmentDetailsDTO> {
    private final String KEY_SHIPMENT_DETAILS_ID_IN_USE = "KEY_SHIPMENT_DETAILS_ID_IN_USE";
    Activity context;
    int resource;

    public OrderShipmentDetailsAdapter(@NonNull Activity context, int resource) {
        super(context, resource);
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = this.context.getLayoutInflater();
        View orderShipmentDetailsView = layoutInflater.inflate(this.resource, null);

        TextView textViewOrderShipmentDetailsItemName = orderShipmentDetailsView.findViewById(R.id.textViewOrderShipmentDetailsItemName);
        TextView textViewOrderShipmentDetailsItemPhone = orderShipmentDetailsView.findViewById(R.id.textViewOrderShipmentDetailsItemPhone);
        TextView textViewOrderShipmentDetailsItemAddress = orderShipmentDetailsView.findViewById(R.id.textViewOrderShipmentDetailsItemAddress);
        TextView textViewOrderShipmentDetailsIsUse = orderShipmentDetailsView.findViewById(R.id.textViewOrderShipmentDetailsItemIsUse);

        Button buttonOrderShipmentDetailsItemSelect = orderShipmentDetailsView.findViewById(R.id.buttonOrderShipmentDetailsItemSelect);

        ShipmentDetailsDTO shipmentDetailsDTO = getItem(position);

        textViewOrderShipmentDetailsItemName.setText(shipmentDetailsDTO.getFullName());
        textViewOrderShipmentDetailsItemPhone.setText(shipmentDetailsDTO.getPhone());
        textViewOrderShipmentDetailsItemAddress.setText(shipmentDetailsDTO.getAddress());

        SharedPreferences sharedPreferences = this.context.getSharedPreferences(Constant.ORDER_PREFERENCES, Context.MODE_PRIVATE);
        String shipmentDetailsIDInUse = sharedPreferences.getString(KEY_SHIPMENT_DETAILS_ID_IN_USE, "");
        if (shipmentDetailsIDInUse.equals(shipmentDetailsDTO.getId().toString())) {
            textViewOrderShipmentDetailsIsUse.setText("Tình trạng: Đang được sử dụng");
        } else {
            textViewOrderShipmentDetailsIsUse.setText("Tình trạng: Không được sử dụng");
        }

        buttonOrderShipmentDetailsItemSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(KEY_SHIPMENT_DETAILS_ID_IN_USE, shipmentDetailsDTO.getId().toString());
                editor.apply();
                notifyDataSetChanged();
            }
        });

        return orderShipmentDetailsView;
    }
}
