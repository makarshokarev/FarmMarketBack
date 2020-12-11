package com.example.farmmarketback.responses;

import com.example.farmmarketback.entity.Seller;

public class SellerGetDetailInfo {
    private String userName;
    private String name;
    private String email;
    private String phone;
    private Integer productCount;

    public SellerGetDetailInfo(Seller seller) {
        this.name = seller.getName();
        this.email = seller.getEmail();
        this.phone = seller.getPhone();
        this.productCount = seller.getProducts().size();
        this.userName = seller.getUsername();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Integer getProductCount() {
        return productCount;
    }

    public void setProductCount(Integer productCount) {
        this.productCount = productCount;
    }
}
