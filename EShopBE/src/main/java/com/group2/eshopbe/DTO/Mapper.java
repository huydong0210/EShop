package com.group2.eshopbe.DTO;

import com.group2.eshopbe.entity.EUser;
import com.group2.eshopbe.entity.Product;

import java.util.Stack;

public class Mapper {
    private Mapper(){

    }
    public static UserDTO buildUserDTO(EUser user){
        return new UserDTO(user.getId(), user.getUsername(), user.getEmail(), user.getPhone());
    }
    public static ProductDTO buildProductDTO(Product product){
        return new ProductDTO(product.getId(), product.getPrice(), product.getProductName(), product.getDescription()
        ,product.getUnit(), product.getInventory().getQuantity());
    }
}
