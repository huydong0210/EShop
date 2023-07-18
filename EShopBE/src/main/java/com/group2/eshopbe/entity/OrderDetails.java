package com.group2.eshopbe.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.aspectj.weaver.ast.Or;

import javax.persistence.*;
@Getter
@Setter
@Entity
@Table(name = "orderDetails")
public class OrderDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "amount")
    private int amount;

    @ManyToOne
    @JoinColumn(name = "orders_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Order order;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;


    public OrderDetails(){

    }
}
