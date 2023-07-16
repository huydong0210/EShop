package com.group2.eshopfe.modul;

import java.io.Serializable;

public class Product implements Serializable {
    private int idType;
    private String nameProduct;
    private String ImageProduct;
    private String detailsProduct;
    private int idProduct;
    private Integer priceProduct;

    public Product(int idType, String nameProduct, String imageProduct,
                   String detailsProduct, int idProduct, Integer priceProduct) {
        this.idType = idType;
        this.nameProduct = nameProduct;
        ImageProduct = imageProduct;
        this.detailsProduct = detailsProduct;
        this.idProduct = idProduct;
        this.priceProduct = priceProduct;
    }

    public int getIdType() {
        return idType;
    }

    public void setIdType(int idType) {
        this.idType = idType;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public String getImageProduct() {
        return ImageProduct;
    }

    public void setImageProduct(String imageProduct) {
        ImageProduct = imageProduct;
    }

    public String getDetailsProduct() {
        return detailsProduct;
    }

    public void setDetailsProduct(String detailsProduct) {
        this.detailsProduct = detailsProduct;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public Integer getPriceProduct() {
        return priceProduct;
    }

    public void setPriceProduct(Integer priceProduct) {
        this.priceProduct = priceProduct;
    }
}
