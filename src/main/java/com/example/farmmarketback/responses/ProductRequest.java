package com.example.farmmarketback.responses;

import java.math.BigDecimal;


public class ProductRequest {

    private Integer id;
    private Integer categoryId;
    private String productName;
    private String productDescription;
    private BigDecimal price;
    private BigDecimal amount;
    private byte[] productPicture;

    public byte[] getProductPicture() {
        return productPicture;
    }

    public void setProductPicture(byte[] productPicture) {
        this.productPicture = productPicture;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}