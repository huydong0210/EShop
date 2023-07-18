package com.group2.eshopbe.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name= "productName")
    private String productName;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private String price;
    @Column(name = "unit")
    private String unit;

    @OneToOne(mappedBy = "product")
    private OrderDetails orderDetails;

    @OneToOne(mappedBy = "product")
    private Inventory inventory;
    public Product(){

    }


}
