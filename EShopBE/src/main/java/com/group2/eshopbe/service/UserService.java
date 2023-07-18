package com.group2.eshopbe.service;

import com.group2.eshopbe.entity.EUser;
import com.group2.eshopbe.payload.request.SignUpRequest;

public interface UserService {
    EUser signUp(SignUpRequest signUpRequest);
}
