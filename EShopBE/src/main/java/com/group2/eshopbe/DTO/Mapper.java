package com.group2.eshopbe.DTO;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.group2.eshopbe.entity.EUser;
import com.group2.eshopbe.entity.OrderDetails;
import com.group2.eshopbe.entity.Product;
import com.group2.eshopbe.entity.ShipmentDetails;

import java.util.Stack;

public class Mapper {
    private static ObjectMapper objectMapper = new ObjectMapper();

    private Mapper() {

    }

    public static UserDTO buildUserDTO(EUser user) {
        return new UserDTO(user.getId(), user.getUsername(), user.getEmail(), user.getPhone(), user.getImage().getImageData());
    }

    public static ProductDTO buildProductDTO(Product product) {
        return new ProductDTO(product.getId(), product.getPrice(), product.getProductName(), product.getDescription()
                , product.getUnit(), product.getInventory().getQuantity(), product.getImage().getImageData());
    }

    public static OrderDetailsDTO buildOrderDetailsDTO(OrderDetails orderDetails) {
        return new OrderDetailsDTO(orderDetails.getId(), orderDetails.getAmount(), orderDetails.getStatus(), buildUserDTO(orderDetails.getUser())
                , buildProductDTO(orderDetails.getProduct()), buildShipmentDetailsDTO(orderDetails.getShipmentDetails()));
    }

    public static ShipmentDetailsDTO buildShipmentDetailsDTO(ShipmentDetails shipmentDetails) {
        return new ShipmentDetailsDTO(shipmentDetails.getId(), shipmentDetails.getAddress(), shipmentDetails.getPhone(), shipmentDetails.getFullName());
    }

    public static String convertObjectToJson(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (Exception e) {
            return e.getMessage();
        }

    }
}
