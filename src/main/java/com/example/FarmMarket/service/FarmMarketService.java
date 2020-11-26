package com.example.FarmMarket.service;

import com.example.FarmMarket.repository.FarmMarketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service
public class FarmMarketService {
    @Autowired
    private FarmMarketRepository farmMarketRepository;

    public void newSeller(String name, String email,String username, String password, String phone) {
        farmMarketRepository.newSeller(name, email, username, password, phone);
    }

    public void newProduct(String category, String product_name, String product_description,
                           BigInteger price, BigInteger amount) {
        farmMarketRepository.newProduct(category, product_name, product_description, price, amount);
    }

    public void updateSellerName(int ID, String newName){
        farmMarketRepository.updateSellerName(ID, newName);
    }

    public void updateSellerUsername(int ID, String username){
        farmMarketRepository.updateSellerUsername(ID, username);
    }

    public void updateSellerPersonalInformation(int ID, String personalInformation){
        farmMarketRepository.updateSellerPersonalInformation(ID, personalInformation);
    }

    public void updateSellerAddress(int ID, String address){
        farmMarketRepository.updateSellerAddress(ID, address);
    }
    public void updateSellerPhone(int ID, String phone){
        farmMarketRepository.updateSellerPhone(ID, phone);
    }

    public void updateSellerEmail(int ID, String email){
        farmMarketRepository.updateSellerEmail(ID, email);
    }
    public void updateSellerPassword(int ID, String password){
        farmMarketRepository.updateSellerPassword(ID, password);
    }

}
