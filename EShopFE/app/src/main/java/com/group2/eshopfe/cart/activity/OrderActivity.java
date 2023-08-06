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
import android.widget.Toast;

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
import java.util.Optional;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderActivity extends AppCompatActivity {
    private final String KEY_SHIPMENT_DETAILS_ID_IN_USE = "KEY_SHIPMENT_DETAILS_ID_IN_USE";
    Button buttonOrderConfirm, buttonOrderBack;
    ObjectMapper objectMapper = new ObjectMapper();
    TextView textViewOrderProductName, textViewOrderProductPrice, textViewOrderProductAmount, textViewOrderTotalPrice;
    ListView listViewOrderShipmentDetails;
    OrderShipmentDetailsAdapter orderShipmentDetailsAdapter;
    List<ShipmentDetailsDTO> shipmentDetailsDTOList = new ArrayList<>();
    TextView textViewOrderName, textViewOrderPhone, textViewOrderAddress;
    Long orderDetailsID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        Intent intent = getIntent();
        orderDetailsID = intent.getLongExtra("orderDetailsID", 0);

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
                SharedPreferences sharedPreferences = getSharedPreferences(Constant.ORDER_PREFERENCES, MODE_PRIVATE);
                String shipmentDetailsID = sharedPreferences.getString(KEY_SHIPMENT_DETAILS_ID_IN_USE, "-1");
                if (shipmentDetailsID.equals("-1")){
                    Toast.makeText(OrderActivity.this, "Bạn chưa chọn địa chỉ giao hàng", Toast.LENGTH_SHORT).show();
                } else {
                    Optional<ShipmentDetailsDTO> shipmentDetailsDTO = shipmentDetailsDTOList.stream().filter(detailsDTO ->
                            detailsDTO.getId().toString().equals(shipmentDetailsID)).findAny();
                    if (shipmentDetailsDTO.isPresent()){
                        buttonOrderConfirmHandler(orderDetailsID, shipmentDetailsDTO.get());
                    }
                }

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
    public void buttonOrderConfirmHandler(Long id, ShipmentDetailsDTO shipmentDetailsDTO){

        ApiHomeServiceImpl.getInstances().setSharedPreferences(getSharedPreferences(Constant.PREFERENCES, Context.MODE_PRIVATE));
        ApiHomeServiceImpl.getInstances().updateStatusOrderDetailsToPendingPickUp(id, shipmentDetailsDTO).enqueue(new Callback<ResponseObject>() {
            @Override
            public void onResponse(Call<ResponseObject> call, Response<ResponseObject> response) {
                if (response.errorBody() == null) {
                    Toast.makeText(OrderActivity.this, "Đặt hàng thành công", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(OrderActivity.this, CartActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(OrderActivity.this, "Đặt hàng thất bại", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseObject> call, Throwable t) {

            }
        });

    }
}