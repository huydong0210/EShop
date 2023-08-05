package com.group2.eshopfe.cart.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.group2.eshopfe.DTO.OrderDetailsDTO;
import com.group2.eshopfe.R;
import com.group2.eshopfe.common.Constant;
import com.group2.eshopfe.home.service.impl.ApiHomeServiceImpl;
import com.group2.eshopfe.payload.ResponseObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderActivity extends AppCompatActivity {
    Button buttonOrderConfirm, buttonOrderBack;
    ObjectMapper objectMapper = new ObjectMapper();
    TextView textViewOrderProductName, textViewOrderProductPrice, textViewOrderProductAmount, textViewOrderTotalPrice;
    TextView textViewOrderName, textViewOrderPhone, textViewOrderAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        Intent intent = getIntent();
        Long orderDetailsID = intent.getLongExtra("orderDetailsID", 0);
        ApiHomeServiceImpl.getInstances().setSharedPreferences(getSharedPreferences(Constant.PREFERENCES, Context.MODE_PRIVATE));
        ApiHomeServiceImpl.getInstances().getOrderDetailsByID(orderDetailsID).enqueue(new Callback<ResponseObject>() {
            @Override
            public void onResponse(Call<ResponseObject> call, Response<ResponseObject> response) {
                if (response.errorBody() == null) {
                    try {
                        OrderDetailsDTO orderDetailsDTO = objectMapper.readValue(response.body().getData().toString(), OrderDetailsDTO.class);
                        textViewOrderProductName.setText(orderDetailsDTO.getProductDTO().getProductName());
                        textViewOrderProductPrice.setText("Đơn giá :" + Float.toString(orderDetailsDTO.getProductDTO().getPrice()));
                        textViewOrderProductAmount.setText("Số lượng: " + orderDetailsDTO.getAmount());
                        textViewOrderTotalPrice.setText("Thành tiền: " + Float.toString(orderDetailsDTO.getProductDTO().getPrice() * orderDetailsDTO.getAmount()));

                    } catch (Exception e) {

                    }

                }
            }

            @Override
            public void onFailure(Call<ResponseObject> call, Throwable t) {

            }
        });
        addControls();
        addEvents();
    }

    public void addControls() {
        buttonOrderConfirm = findViewById(R.id.buttonOrderConfirm);
        buttonOrderBack = findViewById(R.id.buttonOrderBack);

        textViewOrderProductName = findViewById(R.id.textViewOrderProductName);
        textViewOrderProductPrice = findViewById(R.id.textViewOrderProductPrice);
        textViewOrderProductAmount = findViewById(R.id.textViewOrderProductAmount);
        textViewOrderTotalPrice = findViewById(R.id.textViewOrderTotalPrice);

    }

    public void addEvents() {
        buttonOrderConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        buttonOrderBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OrderActivity.this, CartActivity.class);
                startActivity(intent);
            }
        });
    }
}