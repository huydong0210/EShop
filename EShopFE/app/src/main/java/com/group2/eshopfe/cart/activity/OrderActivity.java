package com.group2.eshopfe.cart.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.group2.eshopfe.DTO.OrderDetailsDTO;
import com.group2.eshopfe.DTO.ShipmentDetailsDTO;
import com.group2.eshopfe.R;
import com.group2.eshopfe.cart.adapter.OrderShipmentDetailsAdapter;
import com.group2.eshopfe.common.Constant;
import com.group2.eshopfe.home.service.impl.ApiHomeServiceImpl;
import com.group2.eshopfe.payload.ResponseObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderActivity extends AppCompatActivity {
    Button buttonOrderConfirm, buttonOrderBack;
    ObjectMapper objectMapper = new ObjectMapper();
    TextView textViewOrderProductName, textViewOrderProductPrice, textViewOrderProductAmount, textViewOrderTotalPrice;
    ListView listViewOrderShipmentDetails;
    OrderShipmentDetailsAdapter orderShipmentDetailsAdapter;
    List<ShipmentDetailsDTO> shipmentDetailsDTOList = new ArrayList<>();
    TextView textViewOrderName, textViewOrderPhone, textViewOrderAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        Intent intent = getIntent();
        Long orderDetailsID = intent.getLongExtra("orderDetailsID", 0);

        SharedPreferences sharedPreferences = getSharedPreferences(Constant.ORDER_PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();

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
        ApiHomeServiceImpl.getInstances().getAllShipmentDetails().enqueue(new Callback<ResponseObject>() {
            @Override
            public void onResponse(Call<ResponseObject> call, Response<ResponseObject> response) {
                if (response.errorBody() == null) {
                    try {
                        shipmentDetailsDTOList = objectMapper.readValue(response.body().getData().toString(), new TypeReference<List<ShipmentDetailsDTO>>() {
                        });
                        shipmentDetailsDTOList.stream().forEach(shipmentDetailsDTO -> {
                            orderShipmentDetailsAdapter.add(shipmentDetailsDTO);
                        });
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

        listViewOrderShipmentDetails = findViewById(R.id.listViewOrderShipmentDetails);
        orderShipmentDetailsAdapter = new OrderShipmentDetailsAdapter(OrderActivity.this, R.layout.order_shipment_details_item);
        listViewOrderShipmentDetails.setAdapter(orderShipmentDetailsAdapter);

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