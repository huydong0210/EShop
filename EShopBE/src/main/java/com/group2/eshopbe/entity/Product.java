package com.group2.eshopbe.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

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
    private float price;
    @Column(name = "unit")
    private String unit;

    @OneToOne(mappedBy = "product")
    private OrderDetails orderDetails;

    @OneToOne(mappedBy = "product")
    private Inventory inventory;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "image_id", referencedColumnName = "id")
    private Image image;

    public Product(){

    }


}
