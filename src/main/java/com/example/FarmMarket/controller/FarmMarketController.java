package com.example.FarmMarket.controller;



import com.example.FarmMarket.objects.Category;
import com.example.FarmMarket.objects.Seller;
import com.example.FarmMarket.service.CategoryService;
import com.example.FarmMarket.service.ProductService;
import liquibase.pro.packaged.A;
import org.springframework.web.bind.annotation.*;
import com.example.FarmMarket.objects.PopUpWindow;
import com.example.FarmMarket.objects.Product;
import com.example.FarmMarket.service.FarmMarketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class FarmMarketController {
    @Autowired
    private FarmMarketService farmMarketService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductService productService;

    @CrossOrigin
    @PostMapping("newSeller")
    public PopUpWindow newSeller (@RequestBody Seller seller) {
        farmMarketService.newSeller(seller.getName(), seller.getEmail(), seller.getUsername(), seller.getPassword(), seller.getPhone());
        return new PopUpWindow("Thank you for registration.");
    }

    @CrossOrigin
    @PostMapping("newProduct")
    public PopUpWindow newProduct (@RequestBody Product product) {
        farmMarketService.newProduct(product.getCategoryId(), product.getProductName(), product.getProductDescription(), product.getPrice(), product.getAmount());
        return new PopUpWindow("You have added new product: " + product.getProductName());
    }

    @GetMapping("category")
    @CrossOrigin
    public List<Category> getAccount() {
        List<Category> result = categoryService.getCategory();
        return result;
    }

    @CrossOrigin
    @GetMapping("product")
    public List<Product> getProduct(){
        List<Product> result = productService.getProduct();
        return result;
    }

    @PutMapping("updateSellerName")
    public void updateSellerName(@RequestBody Seller seller){
        farmMarketService.updateSellerName(seller.getId(), seller.getName());
    }

    @PutMapping("updateSellerEmail")
    public void updateSellerEmail(@RequestBody Seller seller){
        farmMarketService.updateSellerEmail(seller.getId(), seller.getEmail());
    }

    @PutMapping("updateSellerUsername")
    public void updateSellerUsername(@RequestBody Seller seller){
        farmMarketService.updateSellerUsername(seller.getId(), seller.getUsername());
    }


    @PutMapping("updateSellerPersonalInformation")
    public void updateSellerPersonalInformation(@RequestBody Seller seller){
        farmMarketService.updateSellerPersonalInformation(seller.getId(), seller.getPersonalInformation());
    }

    @PutMapping("updateSellerAddress")
    public void updateSellerAddress(@RequestBody Seller seller){
        farmMarketService.updateSellerAddress(seller.getId(), seller.getAddress());
    }

    @PutMapping("updateSellerPhone")
    public void updateSellerPhone(@RequestBody Seller seller){
        farmMarketService.updateSellerPhone(seller.getId(), seller.getPhone());
    }

    @PutMapping("updateSellerPassword")
    public void updateSellerPassword(@RequestBody Seller seller){
        farmMarketService.updateSellerPassword(seller.getId(), seller.getPassword());
    }

}
