package com.group2.eshopfe.modul;
public class ProductType {
    private int id;
    private String nameProductType;
    private String imageProductType;

    public ProductType(int id, String nameProductType, String imageProductType) {
        this.id = id;
        this.nameProductType = nameProductType;
        this.imageProductType = imageProductType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameProductType() {
        return nameProductType;
    }

    public void setNameProductType(String nameProductType) {
        this.nameProductType = nameProductType;
    }

    public String getImageProductType() {
        return imageProductType;
    }

    public void setImageProductType(String imageProductType) {
        this.imageProductType = imageProductType;
    }
}
