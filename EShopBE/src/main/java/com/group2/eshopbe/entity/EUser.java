package com.group2.eshopbe.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "user")
public class EUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "username",unique = true)
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "firstName")
    private String firstname;

    @Column(name = "lastName")
    private String lastname;
    @Column(name = "email")
    private String email;
    @Column(name = "phone")
    private String phone;

    @Column(name = "address")
    private String address;

    @ManyToMany
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<OrderDetails> orderDetailsList;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<ShipmentDetails> shipmentDetailsList;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "image_id", referencedColumnName = "id")
    private Image image;



    public EUser() {
    }
}
