package com.group2.eshopfe.login.service;

import com.group2.eshopfe.login.model.LoginRequest;
import com.group2.eshopfe.payload.ResponseObject;

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
