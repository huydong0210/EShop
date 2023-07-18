package com.group2.eshopbe.DTO;

import com.group2.eshopbe.entity.EUser;

public class Mapper {
    private Mapper(){

    }
    public static UserDTO buildUserDTO(EUser user){
        return new UserDTO(user.getId(), user.getUsername(), user.getEmail(), user.getPhone());
    }
}
