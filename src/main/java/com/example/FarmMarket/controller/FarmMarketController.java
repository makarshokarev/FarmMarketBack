package com.example.FarmMarket.controller;


import com.example.FarmMarket.objects.PopUpWindow;
import com.example.FarmMarket.objects.Product;
import com.example.FarmMarket.service.FarmMarketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    @CrossOrigin
    @PostMapping("newProduct")
    public PopUpWindow newProduct (@RequestBody Product product) {
        farmMarketService.newProduct(product.getCategory(), product.getProductName(), product.getProductDescription(), product.getPrice(), product.getAmount());
        return new PopUpWindow("You have added new product: " + product.getProductName());
    }



}
