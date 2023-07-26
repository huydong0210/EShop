package com.group2.eshopfe.home.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.group2.eshopfe.DTO.Mapper;
import com.group2.eshopfe.DTO.UserDTO;
import com.group2.eshopfe.R;
import com.group2.eshopfe.common.Constant;
import com.group2.eshopfe.common.Utils;
import com.group2.eshopfe.home.adapter.HomeProductAdapter;
import com.group2.eshopfe.DTO.ProductDTO;
import com.group2.eshopfe.home.service.impl.ApiHomeServiceImpl;
import com.group2.eshopfe.login.activity.LoginActivity;
import com.group2.eshopfe.payload.ResponseObject;
import com.group2.eshopfe.user_infor.activity.UserInformation;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {
    ImageView imageViewHomeAvatar;
    TextView textViewHomeName;
    TextView textViewHomeEmail;
    ListView listViewHomeAllProducts;
    HomeProductAdapter homeProductAdapter;
    List<ProductDTO> productDTOS = new ArrayList<>();
    ObjectMapper objectMapper = new ObjectMapper();
    Button buttonHomeHome, buttonHomeCart, buttonHomeUser;

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        ApiHomeServiceImpl.getInstances().setSharedPreferences(getSharedPreferences(Constant.PREFERENCES, Context.MODE_PRIVATE));
        ApiHomeServiceImpl.getInstances().getCurrentUserDTO().enqueue(new Callback<ResponseObject>() {
            @Override
            public void onResponse(Call<ResponseObject> call, Response<ResponseObject> response) {
                Log.i("getCurrentUserDTO", "success");
                try {
                    UserDTO userDTO = objectMapper.readValue(response.body().getData().toString(), UserDTO.class);
                    imageViewHomeAvatar.setImageBitmap(Utils.convertBytesToBitMap(userDTO.getImage()));
                    textViewHomeName.setText(userDTO.getUsername());
                    textViewHomeEmail.setText(userDTO.getEmail());
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }

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

        imageViewHomeAvatar =findViewById(R.id.imageViewHomeAvatar);
        textViewHomeName = findViewById(R.id.textViewHomeName);
        textViewHomeEmail = findViewById(R.id.textViewHomeEmail);

        buttonHomeHome= findViewById(R.id.buttonHomeHome);
        buttonHomeCart = findViewById(R.id.buttonHomeCart);
        buttonHomeUser = findViewById(R.id.buttonHomeUser);
    }

    public void addEvent() {
        buttonHomeUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, UserInformation.class);
                startActivity(intent);
            }
        });
    }
}