package com.group2.eshopfe.cart.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.group2.eshopfe.DTO.OrderDetailsDTO;
import com.group2.eshopfe.R;
import com.group2.eshopfe.cart.activity.OrderActivity;
import com.group2.eshopfe.common.Constant;
import com.group2.eshopfe.common.Utils;
import com.group2.eshopfe.home.service.impl.ApiHomeServiceImpl;
import com.group2.eshopfe.payload.ResponseObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        Button buttonCartOrderDetailsOrder = cartOrderDetailsItemView.findViewById(R.id.buttonCartOrderDetailsOrder);

        OrderDetailsDTO orderDetailsDTO = getItem(position);
        imageViewCartOrderDetailsImage.setImageBitmap(Utils.convertBytesToBitMap(orderDetailsDTO.getProductDTO().getImage()));
        textViewCartOrderDetailsPrice.setText(Float.toString(orderDetailsDTO.getProductDTO().getPrice()));
        textViewCartOrderDetailsName.setText(orderDetailsDTO.getProductDTO().getProductName().substring(0, 18) + "...");
        textViewCartOrderDetailsAmount.setText("Số lượng: " + orderDetailsDTO.getAmount());

        buttonCartOrderDetailsDeleteItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonCartOrderDetailsDeleteItemHandler(orderDetailsDTO);
            }
        });
        buttonCartOrderDetailsOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, OrderActivity.class);
                intent.putExtra("orderDetailsID", orderDetailsDTO.getId());
                context.startActivity(intent);
            }
        });

        return cartOrderDetailsItemView;
    }
    private void buttonCartOrderDetailsDeleteItemHandler(OrderDetailsDTO orderDetailsDTO){
        ApiHomeServiceImpl.getInstances().setSharedPreferences(this.context.getSharedPreferences(Constant.PREFERENCES, Context.MODE_PRIVATE));
        ApiHomeServiceImpl.getInstances().deleteOrderDetails(orderDetailsDTO.getProductDTO().getId()).enqueue(new Callback<ResponseObject>() {
            @Override
            public void onResponse(Call<ResponseObject> call, Response<ResponseObject> response) {
                if (response.errorBody()==null){
                    toast("Xoá khỏi giỏ hàng thành công");
                } else {
                    toast("Xoá khỏi giỏ hàng thất bại");
                }
            }

            @Override
            public void onFailure(Call<ResponseObject> call, Throwable t) {

            }
        });
        this.remove(orderDetailsDTO);
        this.notifyDataSetChanged();


    }
    public void toast(String message){
        Toast.makeText(this.context, message, Toast.LENGTH_SHORT).show();
    }
}
