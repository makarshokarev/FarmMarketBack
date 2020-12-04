package com.example.farmmarketback.Responses;

import com.example.farmmarketback.objects.Product;

import java.math.BigDecimal;

public class ProductGetFullInfo {
    private String productName;
    private String productDescription;
    private BigDecimal price;
    private BigDecimal amount;
    private String sellerName;
    private String sellerAddress;
    private String sellerEmail;
    private String sellerPhone;
    private String categoryPicture;

    public ProductGetFullInfo(Product product) {
        this.productName = product.getProductName();
        this.productDescription = product.getProductDescription();
        this.price = product.getPrice();
        this.amount = product.getAmount();
        this.sellerName = product.getSeller().getName();
        this.sellerAddress = product.getSeller().getAddress();
        this.sellerEmail = product.getSeller().getEmail();
        this.sellerPhone = product.getSeller().getPhone();
        this.categoryPicture = product.getCategory().getCategoryPicture();
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

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public String getSellerAddress() {
        return sellerAddress;
    }

    public void setSellerAddress(String sellerAddress) {
        this.sellerAddress = sellerAddress;
    }

    public String getSellerEmail() {
        return sellerEmail;
    }

    public void setSellerEmail(String sellerEmail) {
        this.sellerEmail = sellerEmail;
    }

    public String getSellerPhone() {
        return sellerPhone;
    }

    public void setSellerPhone(String sellerPhone) {
        this.sellerPhone = sellerPhone;
    }

    public String getCategoryPicture() {
        return categoryPicture;
    }

    public void setCategoryPicture(String categoryPicture) {
        this.categoryPicture = categoryPicture;
    }
}
