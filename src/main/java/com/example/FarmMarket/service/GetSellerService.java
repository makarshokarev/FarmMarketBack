package com.example.FarmMarket.service;

import com.example.FarmMarket.objects.Seller;
import com.example.FarmMarket.objects.Seller1;
import com.example.FarmMarket.repository.GetSellerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class GetSellerService {

    @Autowired
    private GetSellerRepo getSellerRepo;

    public Seller1 getSeller(){
        Seller1 seller = getSellerRepo.getSeller();
        return seller;
    }
}
