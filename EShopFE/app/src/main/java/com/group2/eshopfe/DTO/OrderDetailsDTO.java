package com.group2.eshopfe.DTO;

public class OrderDetailsDTO {
    private Long id;
    private int amount;
    private String status;
    private UserDTO userDTO;
    private ProductDTO productDTO;

    public OrderDetailsDTO() {
    }

    public OrderDetailsDTO(Long id, int amount, String status, UserDTO userDTO, ProductDTO productDTO) {
        this.id = id;
        this.amount = amount;
        this.status = status;
        this.userDTO = userDTO;
        this.productDTO = productDTO;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    public ProductDTO getProductDTO() {
        return productDTO;
    }

    public void setProductDTO(ProductDTO productDTO) {
        this.productDTO = productDTO;
    }
}
