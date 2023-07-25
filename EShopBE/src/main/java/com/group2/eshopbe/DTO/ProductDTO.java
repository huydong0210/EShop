package com.group2.eshopbe.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDTO {
    private Long id;
    private float price;
    private String productName;
    private String description;

    private String unit;
    private int inventory;
    public ProductDTO() {
    }

    public ProductDTO(Long id, float price, String productName, String description, String unit, int inventory) {
        this.id = id;
        this.price = price;
        this.productName = productName;
        this.description = description;
        this.unit = unit;
        this.inventory = inventory;
    }
}
