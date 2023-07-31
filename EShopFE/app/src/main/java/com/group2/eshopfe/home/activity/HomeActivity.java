package com.group2.eshopfe.home.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.group2.eshopfe.R;
import com.group2.eshopfe.cart.activity.CartActivity;
import com.group2.eshopfe.common.Constant;
import com.group2.eshopfe.home.adapter.HomeProductAdapter;
import com.group2.eshopfe.DTO.ProductDTO;
import com.group2.eshopfe.home.service.impl.ApiHomeServiceImpl;
import com.group2.eshopfe.payload.ResponseObject;
import com.group2.eshopfe.user_infor.activity.UserInformation;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {
    ListView listViewHomeAllProducts;
    HomeProductAdapter homeProductAdapter;
    List<ProductDTO> productDTOS = new ArrayList<>();
    ObjectMapper objectMapper = new ObjectMapper();
    ImageButton imageButtonHomeHome, imageButtonHomeCart, imageButtonHomeUser;

    SearchView searchViewHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        ApiHomeServiceImpl.getInstances().setSharedPreferences(getSharedPreferences(Constant.PREFERENCES, Context.MODE_PRIVATE));
        ApiHomeServiceImpl.getInstances().getCurrentUserDTO().enqueue(new Callback<ResponseObject>() {
            @Override
            public void onResponse(Call<ResponseObject> call, Response<ResponseObject> response) {
                Log.i("getCurrentUserDTO", "success");

            }

            @Override
            public void onFailure(Call<ResponseObject> call, Throwable t) {
                Log.i("getCurrentUserDTO", t.toString());

            }
        });
        ApiHomeServiceImpl.getInstances().getAllProducts().enqueue(new Callback<ResponseObject>() {
            @Override
            public void onResponse(Call<ResponseObject> call, Response<ResponseObject> response) {
                Log.i("products",response.toString());
                try {
                    productDTOS = objectMapper.readValue(response.body().getData().toString(), new TypeReference<List<ProductDTO>>() {
                    });
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
                productDTOS.stream().forEach(productDTO -> {
                    homeProductAdapter.add(productDTO);
                });

                Log.i("products",productDTOS.toString());
            }

            @Override
            public void onFailure(Call<ResponseObject> call, Throwable t) {

            }
        });
        addControls();
        addEvent();

    }

    public void addControls() {
        listViewHomeAllProducts = findViewById(R.id.listViewHomeAllProducts);
        homeProductAdapter = new HomeProductAdapter(HomeActivity.this, R.layout.home_product_item);
        listViewHomeAllProducts.setAdapter(homeProductAdapter);

        imageButtonHomeHome= findViewById(R.id.imageButtonHomeHome);
        imageButtonHomeCart = findViewById(R.id.imageButtonHomeCart);
        imageButtonHomeUser = findViewById(R.id.imageButtonHomeUser);

        searchViewHome = findViewById(R.id.searchViewHome);
    }

    public void addEvent() {
        imageButtonHomeUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(HomeActivity.this, UserInformation.class);
                startActivity(intent);
            }
        });
        imageButtonHomeCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(HomeActivity.this, CartActivity.class);
                startActivity(intent);
            }
        });
        searchViewHome.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                System.out.println("sdfsdf");
                homeProductAdapter.clear();
                productDTOS.stream().forEach(productDTO -> {
                    if (productDTO.getProductName().toLowerCase().contains(newText.toLowerCase())){
                        homeProductAdapter.add(productDTO);
                    }
                });
                return false;
            }
        });
    }
    public void searchViewHandler(){

    }

}