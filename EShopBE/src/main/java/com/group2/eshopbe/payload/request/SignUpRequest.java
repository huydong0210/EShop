package com.group2.eshopbe.payload.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class SignUpRequest {
    private String username;
    private String password;
    private String email;
    private String phone;
    public SignUpRequest(){

    }

}
