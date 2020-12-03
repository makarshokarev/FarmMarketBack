package com.example.farmmarketback.objects;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy =
            GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "seller_id")
    private Seller seller;

    public Seller getSeller() {
        return seller;
    }

    public Product setSeller(Seller seller) {
        this.seller = seller;
        return this;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    private String productName;
    private String productDescription;
    private BigDecimal price;
    private BigDecimal amount;
    private Integer categoryId;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
