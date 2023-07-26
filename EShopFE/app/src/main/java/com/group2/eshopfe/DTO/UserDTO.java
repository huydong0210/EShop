package com.group2.eshopfe.DTO;

public class UserDTO {
    private long id;
    private String username;
    private String email;
    private String phone;
    private byte[] image;
    public UserDTO(){

    }

//    public UserDTO(long id, String username, String email, String phone) {
//        this.id = id;
//        this.username = username;
//        this.email = email;
//        this.phone = phone;
//    }

    public UserDTO(long id, String username, String email, String phone, byte[] image) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.image = image;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
