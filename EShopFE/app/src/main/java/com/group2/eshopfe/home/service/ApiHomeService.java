package com.group2.eshopfe.home.service;

import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiHomeService {
    public void setSharedPreferences(SharedPreferences sharedPreferences);
    @GET("/api/account")
    Call<ResponseObject> getCurrentUserDTO();
    @GET("/api/products")
    Call<ResponseObject> getAllProducts();

}
