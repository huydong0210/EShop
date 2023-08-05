package com.group2.eshopfe.user_infor.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.group2.eshopfe.DTO.ShipmentDetailsDTO;
import com.group2.eshopfe.R;
import com.group2.eshopfe.common.Constant;
import com.group2.eshopfe.home.service.impl.ApiHomeServiceImpl;
import com.group2.eshopfe.payload.ResponseObject;
import com.group2.eshopfe.user_infor.adapter.ShipmentDetailsAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShipmentDetailsActivity extends AppCompatActivity {
    ObjectMapper objectMapper = new ObjectMapper();
    ListView listViewShipmentDetailsList;

    Button buttonShipmentDetailsAdd, buttonShipmentDetailsBack;
    ShipmentDetailsAdapter shipmentDetailsAdapter;
    List<ShipmentDetailsDTO> shipmentDetailsDTOList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shipment_details);
        ApiHomeServiceImpl.getInstances().setSharedPreferences(getSharedPreferences(Constant.PREFERENCES, Context.MODE_PRIVATE));
        ApiHomeServiceImpl.getInstances().getAllShipmentDetails().enqueue(new Callback<ResponseObject>() {
            @Override
            public void onResponse(Call<ResponseObject> call, Response<ResponseObject> response) {
                if (response.errorBody()==null){
                    try{
                        shipmentDetailsDTOList = objectMapper.readValue(response.body().getData().toString(), new TypeReference<List<ShipmentDetailsDTO>>() {
                        });
                        shipmentDetailsDTOList.stream().forEach(shipmentDetailsDTO -> {
                            shipmentDetailsAdapter.add(shipmentDetailsDTO);
                        });
                    }
                    catch (Exception e){

                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseObject> call, Throwable t) {

            }
        });


        addControls();
        addEvent();
    }
    public void addControls(){
        buttonShipmentDetailsBack = findViewById(R.id.buttonShipmentDetailsBack);
        buttonShipmentDetailsAdd = findViewById(R.id.buttonShipmentDetailsAdd);

        listViewShipmentDetailsList = findViewById(R.id.listViewShipmentDetailsList);
        shipmentDetailsAdapter = new ShipmentDetailsAdapter(ShipmentDetailsActivity.this, R.layout.activity_shipment_details_item);
        listViewShipmentDetailsList.setAdapter(shipmentDetailsAdapter);


    }
    public void addEvent(){
        buttonShipmentDetailsBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShipmentDetailsActivity.this, UserInformation.class);
                startActivity(intent);
            }
        });
        buttonShipmentDetailsAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(ShipmentDetailsActivity.this, NewShipmentDetailsActivity.class);
                startActivity(intent);
            }
        });

    }
}