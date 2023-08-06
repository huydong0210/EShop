package com.group2.eshopfe.user_infor.adapter;

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
import com.group2.eshopfe.R;
import com.group2.eshopfe.common.Constant;
import com.group2.eshopfe.common.Utils;

public class OrderDetailsInProcessingAdapter extends ArrayAdapter<OrderDetailsDTO> {
    Activity context;
    int resource;

    public OrderDetailsInProcessingAdapter(@NonNull Activity context, int resource) {
        super(context, resource);
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = this.context.getLayoutInflater();
        View orderDetailInProcessingItemView = layoutInflater.inflate(this.resource, null);

        TextView textViewOrderDetailsInProcessingProductName = orderDetailInProcessingItemView.findViewById(R.id.textViewOrderDetailsInProcessingProductName);
        TextView textViewOrderDetailsInProcessingTotalPrice = orderDetailInProcessingItemView.findViewById(R.id.textViewOrderDetailsInProcessingTotalPrice);
        TextView textViewOrderDetailsInProcessingStatus = orderDetailInProcessingItemView.findViewById(R.id.textViewOrderDetailsInProcessingStatus);
        ImageView imageViewOrderDetailsInProcessingImage= orderDetailInProcessingItemView.findViewById(R.id.imageViewOrderDetailsInProcessingImage);


        OrderDetailsDTO orderDetailsDTO = getItem(position);
        imageViewOrderDetailsInProcessingImage.setImageBitmap(Utils.convertBytesToBitMap(orderDetailsDTO.getProductDTO().getImage()));
        textViewOrderDetailsInProcessingProductName.setText(orderDetailsDTO.getProductDTO().getProductName());
        textViewOrderDetailsInProcessingTotalPrice.setText("Thành tiền: " + Float.toString(orderDetailsDTO.getAmount() * orderDetailsDTO.getProductDTO().getPrice()));
        String status = orderDetailsDTO.getStatus();
        switch (status) {
            case Constant.PENDING_PICKUP:
                textViewOrderDetailsInProcessingStatus.setText("Trạng thái : Đang chờ lấy hàng");
                break;
            case Constant.OUT_FOR_DELIVERY:
                textViewOrderDetailsInProcessingStatus.setText("Trạng thái : Đang giao");
                break;
            case Constant.DELIVERED:
                textViewOrderDetailsInProcessingStatus.setText("Trạng thái : Đã nhận được hàng");
                break;
            default:
                break;
        }


        return orderDetailInProcessingItemView;
    }
}
