package com.group2.eshopfe.login.service;

import android.util.Log;
import android.widget.Toast;

import com.group2.eshopfe.api.ApiService;
import com.group2.eshopfe.login.activity.LoginActivity;
import com.group2.eshopfe.login.model.LoginRequest;
import com.group2.eshopfe.payload.ResponseObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginService {
    private LoginService(){

    }
    public static ResponseObject login(LoginRequest loginRequest){
        if (loginRequest.getUsername() == null || loginRequest.getUsername().equals("")){
            return new ResponseObject(ResponseObject.FAIL, "Username không được để trống", null);
        }
        if (loginRequest.getPassword() == null || loginRequest.getPassword().equals("")){
            return new ResponseObject(ResponseObject.FAIL, "Password không được để trống", null);
        }
        return new ResponseObject(ResponseObject.SUCCESS, "Full form is filled",null);

    }
}
