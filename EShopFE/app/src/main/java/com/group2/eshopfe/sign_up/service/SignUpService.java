package com.group2.eshopfe.sign_up.service;

import com.group2.eshopfe.payload.ResponseObject;
import com.group2.eshopfe.sign_up.model.SignUpRequest;

public class SignUpService {
    private SignUpService(){}
    public static ResponseObject signUp(SignUpRequest signUpRequest){
        if (signUpRequest.getUsername().equals("")){
            return new ResponseObject(ResponseObject.FAIL, "Tên đăng nhập không được để trống", signUpRequest);
        }
        if (signUpRequest.getPassword().equals("")){
            return new ResponseObject(ResponseObject.FAIL, "Mật khẩu không được để trống", signUpRequest);
        }
        if (signUpRequest.getEmail().equals("")){
            return new ResponseObject(ResponseObject.FAIL, "Email không được để trống", signUpRequest);
        }
        if (signUpRequest.getPhone().equals("")){
            return new ResponseObject(ResponseObject.FAIL, "Số điện thoại không được để trống", signUpRequest);
        }
        return new ResponseObject(ResponseObject.SUCCESS, "", signUpRequest);
    }
}
