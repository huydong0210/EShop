package com.group2.eshopfe.login.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.group2.eshopfe.common.Constant;
import com.group2.eshopfe.login.model.LoginRequest;
import com.group2.eshopfe.payload.ResponseObject;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiLoginService {

    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create();
    ApiLoginService apiLoginService = new Retrofit.Builder()
            .baseUrl(Constant.SERVER_API_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build().create(ApiLoginService.class);
    @POST("api/authenticate")
    Call<ResponseObject> authenticate(@Body LoginRequest loginRequest);
}
