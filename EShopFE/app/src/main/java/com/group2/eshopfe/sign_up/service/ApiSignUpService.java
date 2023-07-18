package com.group2.eshopfe.sign_up.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.group2.eshopfe.common.Constant;
import com.group2.eshopfe.login.model.LoginRequest;
import com.group2.eshopfe.login.service.ApiLoginService;
import com.group2.eshopfe.payload.ResponseObject;
import com.group2.eshopfe.sign_up.model.SignUpRequest;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiSignUpService {

    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create();

    ApiSignUpService apiSignUpService = new Retrofit.Builder()
            .baseUrl(Constant.SERVER_API_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build().create(ApiSignUpService.class);

    @POST("api/sign-up")
    Call<ResponseObject> register(@Body SignUpRequest signUpRequest);
}
