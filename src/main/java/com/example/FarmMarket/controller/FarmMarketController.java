package com.example.FarmMarket.controller;


import com.example.FarmMarket.service.FarmMarketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;

@RestController
public class FarmMarketController {
    @Autowired
    private FarmMarketService farmMarketService;

    @PostMapping("newSeller")
    public void newSeller (String name, String personal_information, String address, String phone) {
        farmMarketService.newSeller(name, personal_information, address, phone);
    }

    @PostMapping("newProduct")
    public void newProduct (String category, String product_name, String product_description,
                            BigInteger price, BigInteger amount) {
        farmMarketService.newProduct(category, product_name, product_description, price, amount);
    }

import java.util.List;



}
