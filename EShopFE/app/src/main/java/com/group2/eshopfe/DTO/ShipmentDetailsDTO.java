package com.group2.eshopfe.DTO;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
