package com.group2.eshopbe.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
    private long id;
    private String username;
    private String email;
    private String phone;
    private byte[] image;
    public UserDTO(){

    }

//    public UserDTO(long id, String username, String email, String phone) {
//        this.id = id;
//        this.username = username;
//        this.email = email;
//        this.phone = phone;
//    }

    public UserDTO(long id, String username, String email, String phone, byte[] image) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.image = image;
    }
}
