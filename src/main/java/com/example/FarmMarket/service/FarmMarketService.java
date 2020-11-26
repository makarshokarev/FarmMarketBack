package com.example.FarmMarket.service;

import com.example.FarmMarket.objects.PopUpWindow;
import com.example.FarmMarket.repository.FarmMarketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class FarmMarketService {
    @Autowired
    private FarmMarketRepository farmMarketRepository;

    public void newSeller(String name, String personal_information, String address, String phone) {
        farmMarketRepository.newSeller(name, personal_information, address, phone);
    }

    public void newProduct(String category, String product_name, String product_description,
                                  BigDecimal price, BigDecimal amount) {
        farmMarketRepository.newProduct(category, product_name, product_description, price, amount);
    }

}
