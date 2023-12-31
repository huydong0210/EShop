package com.group2.eshopfe.home.service;

import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.group2.eshopfe.DTO.ShipmentDetailsDTO;
import com.group2.eshopfe.common.Constant;
import com.group2.eshopfe.login.service.ApiLoginService;
import com.group2.eshopfe.payload.ResponseObject;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiHomeService {
    public void setSharedPreferences(SharedPreferences sharedPreferences);

    @GET("/api/account")
    Call<ResponseObject> getCurrentUserDTO();

    @GET("/api/products")
    Call<ResponseObject> getAllProducts();

    @GET("/api/order-details")
    Call<ResponseObject> getAllOrderDetails();

    @POST("/api/order-details/products/{productID}")
    Call<ResponseObject> addNewOrderDetailsInCart(@Path("productID") Long id);

    @DELETE("/api/order-details/{productID}")
    Call<ResponseObject> deleteOrderDetails(@Path("productID") Long id);

    @GET("api/products/{productID}")
    Call<ResponseObject> getProductDTOByID(@Path("productID") Long id);

    @GET("/api/shipment-details")
    Call<ResponseObject> getAllShipmentDetails();

    @POST("/api/shipment-details")
    Call<ResponseObject> addNewShipmentDetails(@Body ShipmentDetailsDTO shipmentDetailsDTO);
    @GET("/api/order-details/{id}")
    Call<ResponseObject> getOrderDetailsByID(@Path("id") Long id);
    @PUT("api/order-details/{id}")
    Call<ResponseObject> updateStatusOrderDetailsToPendingPickUp(@Path("id") Long id, @Body ShipmentDetailsDTO shipmentDetailsDTO);
    @GET("/api/order-details/in-processing")
    Call<ResponseObject> getAllOrderDetailsInProcessing();

}
