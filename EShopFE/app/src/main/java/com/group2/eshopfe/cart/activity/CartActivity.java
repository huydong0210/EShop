package com.group2.eshopfe.cart.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.group2.eshopfe.DTO.OrderDetailsDTO;
import com.group2.eshopfe.DTO.ProductDTO;
import com.group2.eshopfe.R;
import com.group2.eshopfe.cart.adapter.CartOrderDetailsAdapter;
import com.group2.eshopfe.common.Constant;
import com.group2.eshopfe.home.activity.HomeActivity;
import com.group2.eshopfe.home.service.impl.ApiHomeServiceImpl;
import com.group2.eshopfe.login.activity.LoginActivity;
import com.group2.eshopfe.payload.ResponseObject;
import com.group2.eshopfe.user_infor.activity.UserInformation;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartActivity extends AppCompatActivity {
    ImageButton imageButtonCartHome, imageButtonCartCart, imageButtonCartUser;
    ListView listViewCartOrderDetails;
    CartOrderDetailsAdapter cartOrderDetailsAdapter;
    List<OrderDetailsDTO> orderDetailsDTOS = new ArrayList<>();
    ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        ApiHomeServiceImpl.getInstances().setSharedPreferences(getSharedPreferences(Constant.PREFERENCES, Context.MODE_PRIVATE));
        ApiHomeServiceImpl.getInstances().getCurrentUserDTO().enqueue(new Callback<ResponseObject>() {
            @Override
            public void onResponse(Call<ResponseObject> call, Response<ResponseObject> response) {
                Log.i("getCurrentUserDTO", "success");
                if (response.errorBody()!=null){
                    Intent intent =new Intent(CartActivity.this, LoginActivity.class);
                    startActivity(intent);
                }

            }
            @Override
            public void onFailure(Call<ResponseObject> call, Throwable t) {
                Log.i("getCurrentUserDTO", t.toString());

            }
        });
        ApiHomeServiceImpl.getInstances().getAllOrderDetails().enqueue(new Callback<ResponseObject>() {
            @Override
            public void onResponse(Call<ResponseObject> call, Response<ResponseObject> response) {
                Log.i("getAllOrderDetails","success");
                if (response.errorBody() == null){
                    try {
                        orderDetailsDTOS = objectMapper.readValue(response.body().getData().toString(), new TypeReference<List<OrderDetailsDTO>>() {
                        });
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                    orderDetailsDTOS.stream().forEach(orderDetailsDTO -> {
                        cartOrderDetailsAdapter.add(orderDetailsDTO);
                    });
                }
            }

            @Override
            public void onFailure(Call<ResponseObject> call, Throwable t) {

            }
        });

        addControls();
        addEvents();
    }

    private void addControls() {
        imageButtonCartHome = findViewById(R.id.imageButtonHomeCart);
        imageButtonCartCart = findViewById(R.id.imageButtonCartCart);
        imageButtonCartUser = findViewById(R.id.imageButtonCartUser);

        listViewCartOrderDetails= findViewById(R.id.listViewCartOrderDetails);
        cartOrderDetailsAdapter = new CartOrderDetailsAdapter(CartActivity.this, R.layout.cart_order_details_item);
        listViewCartOrderDetails.setAdapter(cartOrderDetailsAdapter);

    }

    private void addEvents() {
        imageButtonCartHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CartActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
        imageButtonCartUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CartActivity.this, UserInformation.class);
                startActivity(intent);
            }
        });
    }
}