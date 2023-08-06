package com.group2.eshopfe.user_infor.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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
        ImageView imageViewOrderDetailsInProcessingImage = orderDetailInProcessingItemView.findViewById(R.id.imageViewOrderDetailsInProcessingImage);


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
        Button buttonOrderDetailsInProcessingDetails = orderDetailInProcessingItemView.findViewById(R.id.buttonOrderDetailsInProcessingDetails);

        buttonOrderDetailsInProcessingDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setIcon(android.R.drawable.ic_dialog_info);
                builder.setMessage(
                        "Tên Sản Phẩm: " + orderDetailsDTO.getProductDTO().getProductName() + "\n"
                                + "- Đơn giá: " + orderDetailsDTO.getProductDTO().getPrice() + "\n"
                                + "- Số lượng: " + orderDetailsDTO.getAmount() + "\n"
                                + "- Thành tiền:" + orderDetailsDTO.getAmount() * orderDetailsDTO.getProductDTO().getPrice() + "\n"
                                + "Thông tin giao hàng : \n"
                                + "- Tên người nhận: " + orderDetailsDTO.getShipmentDetailsDTO().getFullName() + "\n"
                                + "- Số điện thoại: " + orderDetailsDTO.getShipmentDetailsDTO().getPhone() + "\n"
                                + "- Địa chỉ: " + orderDetailsDTO.getShipmentDetailsDTO().getAddress() + "\n"
                );
                builder.setTitle("Thông tin đơn hàng");
                builder.create().show();
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
            }
        });

        return orderDetailInProcessingItemView;
    }
}
