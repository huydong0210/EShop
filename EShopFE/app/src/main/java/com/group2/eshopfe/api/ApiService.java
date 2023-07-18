package com.group2.eshopfe.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.group2.eshopfe.login.model.LoginRequest;
import com.group2.eshopfe.payload.ResponseObject;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {
    public final static String SERVER_API_URL = "http://192.168.0.106:8080/";
    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create();
    ApiService apiService = new Retrofit.Builder()
            .baseUrl(SERVER_API_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build().create(ApiService.class);
    @POST("api/authenticate")
    Call<ResponseObject> authenticate(@Body LoginRequest loginRequest);
}
