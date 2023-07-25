package com.group2.eshopfe.home.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.group2.eshopfe.R;
import com.group2.eshopfe.common.Constant;
import com.group2.eshopfe.home.adapter.HomeProductAdapter;
import com.group2.eshopfe.DTO.ProductDTO;
import com.group2.eshopfe.home.service.impl.ApiHomeServiceImpl;
import com.group2.eshopfe.payload.ResponseObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {
    ListView listViewHomeAllProducts;
    HomeProductAdapter homeProductAdapter;
    List<ProductDTO> productDTOS = new ArrayList<>();

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
                ObjectMapper objectMapper = new ObjectMapper();
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
    }

    public void addEvent() {

    }

    public void createFakeData() {
        homeProductAdapter.add(new ProductDTO(10000, "name", "description"));
    }
}