package com.group2.eshopfe.modul;
public class ProductType {
    public int id;
    public String nameProductType;
    public String imageProductType;

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
