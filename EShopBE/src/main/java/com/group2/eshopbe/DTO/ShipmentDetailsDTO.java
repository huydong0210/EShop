package com.group2.eshopbe.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShipmentDetailsDTO {
    private Long id;
    private String address;
    private String phone;
    private String fullName;

    public ShipmentDetailsDTO() {
    }

    public ShipmentDetailsDTO(Long id, String address, String phone, String fullName) {
        this.id = id;
        this.address = address;
        this.phone = phone;
        this.fullName = fullName;
    }
}
