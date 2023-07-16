package com.group2.eshopfe.modul;

public class Cart extends Product{
    private int quantity;

    public Cart(int idType, String nameProduct,
                String imageProduct, String detailsProduct, int idProduct, Integer priceProduct,int quantity) {
        super(idType, nameProduct, imageProduct,
                detailsProduct, idProduct, priceProduct);
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
