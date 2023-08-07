package com.group2.eshopfe.user_infor.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.group2.eshopfe.DTO.ShipmentDetailsDTO;
import com.group2.eshopfe.R;
import com.group2.eshopfe.common.Constant;
import com.group2.eshopfe.home.service.impl.ApiHomeServiceImpl;
import com.group2.eshopfe.payload.ResponseObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewShipmentDetailsActivity extends AppCompatActivity {
    View buttonNewShipmentDetailsSave, buttonNewShipmentDetailsBack;
    EditText editTextNewShipmentDetailsName, editTextNewShipmentDetailsPhone, editTextNewShipmentDetailsAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_shipment_details);
        addControls();
        addEvents();

    }

    public void addControls() {
        editTextNewShipmentDetailsName = findViewById(R.id.editTextNewShipmentDetailsName);
        editTextNewShipmentDetailsAddress = findViewById(R.id.editTextNewShipmentDetailsAddress);
        editTextNewShipmentDetailsPhone = findViewById(R.id.editTextNewShipmentDetailsPhone);

        buttonNewShipmentDetailsSave = findViewById(R.id.buttonNewShipmentDetailsSave);
        buttonNewShipmentDetailsBack = findViewById(R.id.buttonNewShipmentDetailsBack);


    }

    public void addEvents() {
        buttonNewShipmentDetailsBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(NewShipmentDetailsActivity.this, ShipmentDetailsActivity.class);
                startActivity(intent);
            }
        });
        buttonNewShipmentDetailsSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonNewShipmentDetailsSaveHandler();
            }
        });

    }
    public void buttonNewShipmentDetailsSaveHandler(){
        ShipmentDetailsDTO shipmentDetailsDTO = new ShipmentDetailsDTO();

        shipmentDetailsDTO.setAddress(editTextNewShipmentDetailsAddress.getText().toString());
        shipmentDetailsDTO.setFullName(editTextNewShipmentDetailsName.getText().toString());
        shipmentDetailsDTO.setPhone(editTextNewShipmentDetailsPhone.getText().toString());

        ApiHomeServiceImpl.getInstances().setSharedPreferences(getSharedPreferences(Constant.PREFERENCES, Context.MODE_PRIVATE));
        ApiHomeServiceImpl.getInstances().addNewShipmentDetails(shipmentDetailsDTO).enqueue(new Callback<ResponseObject>() {
            @Override
            public void onResponse(Call<ResponseObject> call, Response<ResponseObject> response) {
                if (response.errorBody() == null){
                    Toast.makeText(NewShipmentDetailsActivity.this, "Thêm địa chỉ giao hàng thành công", Toast.LENGTH_SHORT).show();
                    Intent intent =new Intent(NewShipmentDetailsActivity.this, ShipmentDetailsActivity.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<ResponseObject> call, Throwable t) {

            }
        });
    }
}