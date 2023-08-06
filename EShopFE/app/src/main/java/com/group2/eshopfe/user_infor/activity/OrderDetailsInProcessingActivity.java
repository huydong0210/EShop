package com.group2.eshopfe.user_infor.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.group2.eshopfe.DTO.OrderDetailsDTO;
import com.group2.eshopfe.DTO.UserDTO;
import com.group2.eshopfe.R;
import com.group2.eshopfe.common.Constant;
import com.group2.eshopfe.home.activity.HomeActivity;
import com.group2.eshopfe.home.service.impl.ApiHomeServiceImpl;
import com.group2.eshopfe.login.activity.LoginActivity;
import com.group2.eshopfe.payload.ResponseObject;
import com.group2.eshopfe.user_infor.adapter.OrderDetailsInProcessingAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderDetailsInProcessingActivity extends AppCompatActivity {
    ObjectMapper objectMapper = new ObjectMapper();
    ListView listViewOrderDetailsInProcessing;
    Button buttonOrderDetailsInProcessing;
    List<OrderDetailsDTO> orderDetailsDTOInProcessingList;
    OrderDetailsInProcessingAdapter orderDetailsInProcessingAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details_in_processing);

        ApiHomeServiceImpl.getInstances().setSharedPreferences(getSharedPreferences(Constant.PREFERENCES, Context.MODE_PRIVATE));
        ApiHomeServiceImpl.getInstances().getCurrentUserDTO().enqueue(new Callback<ResponseObject>() {
            @Override
            public void onResponse(Call<ResponseObject> call, Response<ResponseObject> response) {
                Log.i("getCurrentUserDTO", "success");
                if (response.errorBody() != null) {
                    Intent intent = new Intent(OrderDetailsInProcessingActivity.this, LoginActivity.class);
                    startActivity(intent);
                }

            }

            @Override
            public void onFailure(Call<ResponseObject> call, Throwable t) {
                Log.i("getCurrentUserDTO", t.toString());

            }
        });

        ApiHomeServiceImpl.getInstances().getAllOrderDetailsInProcessing().enqueue(new Callback<ResponseObject>() {
            @Override
            public void onResponse(Call<ResponseObject> call, Response<ResponseObject> response) {
                if (response.errorBody()==null){
                    try {
                        orderDetailsDTOInProcessingList = objectMapper.readValue(response.body().getData().toString(), new TypeReference<List<OrderDetailsDTO>>() {
                        });
                        orderDetailsDTOInProcessingList.stream().forEach(orderDetailsDTO -> {
                            orderDetailsInProcessingAdapter.add(orderDetailsDTO);
                        });
                    } catch (Exception e){

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
        listViewOrderDetailsInProcessing = findViewById(R.id.listViewOrderDetailsInProcessing);
        orderDetailsInProcessingAdapter = new OrderDetailsInProcessingAdapter(OrderDetailsInProcessingActivity.this
                , R.layout.order_details_in_processing_item);
        listViewOrderDetailsInProcessing.setAdapter(orderDetailsInProcessingAdapter);


        buttonOrderDetailsInProcessing = findViewById(R.id.buttonOrderDetailsInPrecessingBack);


    }

    public void addEvents() {
        buttonOrderDetailsInProcessing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OrderDetailsInProcessingActivity.this, UserInformation.class);
                startActivity(intent);
            }
        });

    }

}