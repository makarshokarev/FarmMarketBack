package com.example.farmmarketback.service;

import com.example.farmmarketback.objects.Seller1;
import com.example.farmmarketback.repository.GetSellerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class GetSellerService {

    @Autowired
    private GetSellerRepo getSellerRepo;

    public Seller1 getSeller(){
        return getSellerRepo.getSeller();
    }
}
