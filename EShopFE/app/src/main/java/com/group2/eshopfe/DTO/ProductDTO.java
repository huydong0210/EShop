package com.group2.eshopfe.DTO;

import java.io.Serializable;

public class ProductDTO implements Serializable {

    private Long id;
    private float price;
    private String productName;
    private String description;

    private String unit;
    private int inventory;
    public ProductDTO(){

    }

    public ProductDTO(float price, String productName, String description) {
        this.price = price;
        this.productName = productName;
        this.description = description;
    }

    public ProductDTO(Long id, float price, String productName, String description, String unit, int inventory) {
        this.id = id;
        this.price = price;
        this.productName = productName;
        this.description = description;
        this.unit = unit;
        this.inventory = inventory;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public int getInventory() {
        return inventory;
    }

    public void setInventory(int inventory) {
        this.inventory = inventory;
    }
}
