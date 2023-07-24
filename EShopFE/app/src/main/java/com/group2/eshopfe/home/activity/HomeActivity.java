package com.group2.eshopfe.home.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.group2.eshopfe.R;
import com.group2.eshopfe.home.adapter.HomeProductAdapter;
import com.group2.eshopfe.home.model.HomeProduct;
import com.group2.eshopfe.home.service.ApiHomeService;
import com.group2.eshopfe.home.service.impl.ApiHomeServiceImpl;
import com.group2.eshopfe.payload.ResponseObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {
    ListView listViewHomeAllProducts;
    HomeProductAdapter homeProductAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ApiHomeServiceImpl.getInstances().setSharedPreferences(getSharedPreferences("MyPrefs", Context.MODE_PRIVATE));
//        ApiHomeServiceImpl.getInstances().getCurrentUserDTO().enqueue(new Callback<ResponseObject>() {
//            @Override
//            public void onResponse(Call<ResponseObject> call, Response<ResponseObject> response) {
//                Log.i("getCurrentUserDTO", "success");
//            }
//
//            @Override
//            public void onFailure(Call<ResponseObject> call, Throwable t) {
//                Log.i("getCurrentUserDTO", t.toString());
//
//            }
//        });
        addControls();
        addEvent();
        createFakeData();
    }

    public void addControls() {
        listViewHomeAllProducts = findViewById(R.id.listViewHomeAllProducts);
        homeProductAdapter = new HomeProductAdapter(HomeActivity.this, R.layout.home_product_item);
        listViewHomeAllProducts.setAdapter(homeProductAdapter);
    }

    public void addEvent() {

    }

    public void createFakeData() {
        homeProductAdapter.add(new HomeProduct(10000, "name", "description"));
    }
}