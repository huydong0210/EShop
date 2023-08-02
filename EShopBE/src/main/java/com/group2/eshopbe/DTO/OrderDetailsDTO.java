package com.group2.eshopbe.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDetailsDTO {
    private Long id;
    private int amount;
    private String status;
    private UserDTO userDTO;
    private ProductDTO productDTO;

    public OrderDetailsDTO() {
    }

    public OrderDetailsDTO(Long id, int amount, String status, UserDTO userDTO, ProductDTO productDTO) {
        this.id = id;
        this.amount = amount;
        this.status = status;
        this.userDTO = userDTO;
        this.productDTO = productDTO;
    }
}
