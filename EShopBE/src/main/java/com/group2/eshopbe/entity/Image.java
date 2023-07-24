package com.group2.eshopbe.entity;

import lombok.*;
import org.springframework.security.core.userdetails.User;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Builder
@Table(name = "image")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Lob
    @Column(name = "image_data", length = 1000)
    private byte[] imageData;
    @Column(name = "name")
    private String name;
    @Column(name = "type")
    private String type;

    @OneToOne(mappedBy = "image")
    private EUser eUser;

    @OneToOne(mappedBy = "image")
    private Product product;

    public Image() {

    }
}
