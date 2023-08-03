package com.group2.eshopfe.home.service.impl;

import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.group2.eshopfe.common.Constant;
import com.group2.eshopfe.home.service.ApiHomeService;
import com.group2.eshopfe.payload.ResponseObject;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiHomeServiceImpl implements ApiHomeService {
    private static ApiHomeServiceImpl instances;
    private SharedPreferences sharedPreferences;
    private  ApiHomeService apiHomeService;
    private OkHttpClient okHttpClient;
    private ApiHomeServiceImpl(){

    }
    public static ApiHomeServiceImpl getInstances(){
        if (instances == null) {
            instances = new ApiHomeServiceImpl();
        }
        return instances;
    }

    @Override
    public void setSharedPreferences(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
        Interceptor interceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request originalRequest = chain.request();
                String token = sharedPreferences.getString("authToken",null);
                Request modifiedRequest = originalRequest.newBuilder()
                        .header("Authorization", "Bearer " + token)
                        .header("Content-Type", "application/json")
                        .build();
                return chain.proceed(modifiedRequest);
            }
        };
        okHttpClient = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss")
                .create();
        apiHomeService = new Retrofit.Builder()
                .baseUrl(Constant.SERVER_API_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .build().create(ApiHomeService.class);
    }

    @Override
    public Call<ResponseObject> getCurrentUserDTO() {
        return apiHomeService.getCurrentUserDTO();
    }

    @Override
    public Call<ResponseObject> getAllProducts() {
        return apiHomeService.getAllProducts();
    }

    @Override
    public Call<ResponseObject> getAllOrderDetails() {
        return apiHomeService.getAllOrderDetails();
    }

    @Override
    public Call<ResponseObject> addNewOrderDetailsInCart(Long id) {
        return apiHomeService.addNewOrderDetailsInCart(id);
    }

}
