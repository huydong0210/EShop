package com.group2.eshopfe.home.model;

import java.io.Serializable;

public class HomeProduct implements Serializable {
    private float price;
    private String productName;
    private String description;
    private int image;
    public HomeProduct(){

    }

    public HomeProduct(float price, String productName, String description) {
        this.price = price;
        this.productName = productName;
        this.description = description;
    }

    public HomeProduct(float price, String productName, String description, int image) {
        this.price = price;
        this.productName = productName;
        this.description = description;
        this.image = image;
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

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
