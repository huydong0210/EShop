package com.group2.eshopbe.controller;

import com.group2.eshopbe.entity.EUser;
import com.group2.eshopbe.entity.Image;
import com.group2.eshopbe.entity.Role;
import com.group2.eshopbe.payload.request.LoginRequest;
import com.group2.eshopbe.payload.request.SignUpRequest;
import com.group2.eshopbe.payload.response.ResponseObject;
import com.group2.eshopbe.repository.ImageRepository;
import com.group2.eshopbe.repository.RoleRepository;
import com.group2.eshopbe.repository.UserRepository;
import com.group2.eshopbe.security.AuthorityConstants;
import com.group2.eshopbe.security.jwt.TokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api")
public class AuthController {
    @Autowired
    private AuthenticationManagerBuilder authenticationManagerBuilder;
    @Autowired
    private TokenProvider tokenProvider;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    ImageRepository imageRepository;
    @PostMapping("/authenticate")
    public ResponseEntity<ResponseObject> authenticate(@RequestBody LoginRequest loginRequest) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername(),
                loginRequest.getPassword()
        );
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = tokenProvider.createToken(authentication);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("success", "token", token));
    }
    @PostMapping("/sign-up")
    public ResponseEntity<ResponseObject> register (@RequestBody SignUpRequest signUpRequest){
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseObject(ResponseObject.FAIL, "Username is existed", signUpRequest));
        }
        EUser user= new EUser();

        Image image = new Image();
        Image defauftImage = imageRepository.findById(0L).get();
        image.setImageData(defauftImage.getImageData());
        image.setType(defauftImage.getType());
        image.setName(signUpRequest.getUsername());
        imageRepository.save(image);

        user.setUsername(signUpRequest.getUsername());
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        user.setEmail(signUpRequest.getEmail());
        user.setPhone(signUpRequest.getPhone());
        user.setImage(imageRepository.findByName(signUpRequest.getUsername()).get());
        List<Role> roles = new ArrayList<>();
        roles.add(roleRepository.findByName(AuthorityConstants.ROLE_USER).get());
        user.setRoles(roles);
        userRepository.save(user);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(ResponseObject.SUCCESS, "Sign up success", null));
    }
}
