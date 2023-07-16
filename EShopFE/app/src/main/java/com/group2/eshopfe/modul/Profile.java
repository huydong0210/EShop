package com.group2.eshopfe.modul;

import java.io.Serializable;

public class Profile implements Serializable {
    private int id;
    private String email;
    private Integer phone;
    private String address;


    public Profile(int id, String email, Integer phone) {
        this.id = id;
        this.email = email;
        this.phone = phone;
    }

    public Profile(String email, Integer phone, String address) {
        this.email = email;
        this.phone = phone;
        this.address = address;
    }

    public Profile(int id, String email, Integer phone, String address) {
        this.id = id;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
