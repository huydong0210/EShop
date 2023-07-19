package com.group2.eshopbe.controller;

import com.group2.eshopbe.DTO.Mapper;
import com.group2.eshopbe.DTO.UserDTO;
import com.group2.eshopbe.entity.EUser;
import com.group2.eshopbe.payload.response.ResponseObject;
import com.group2.eshopbe.repository.UserRepository;
import com.group2.eshopbe.security.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class AccountController {
    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/account")
    public ResponseEntity<ResponseObject> getCurrentUserDTO() {
        Optional<EUser> user = userRepository.findByUsername(SecurityUtils.getCurrentUserLogin().get());
        if (user.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseObject(ResponseObject.SUCCESS, "current user login", Mapper.buildUserDTO(user.get())));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseObject(ResponseObject.FAIL, "", null));
        }
    }
}
