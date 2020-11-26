package com.example.FarmMarket.controller;


import com.example.FarmMarket.Seller;
import com.example.FarmMarket.service.FarmMarketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;

@RestController
public class FarmMarketController {
    @Autowired
    private FarmMarketService farmMarketService;

    @PostMapping("newSeller")
    public void newSeller (@RequestBody Seller seller) {
        farmMarketService.newSeller(seller.getName(), seller.getEmail(), seller.getUsername(), seller.getPassword(), seller.getPhone());
    }

    @PostMapping("newProduct")
    public void newProduct (String category, String product_name, String product_description,
                            BigInteger price, BigInteger amount) {
        farmMarketService.newProduct(category, product_name, product_description, price, amount);
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
