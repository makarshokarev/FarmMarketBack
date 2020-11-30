package com.example.FarmMarket.service;

import com.example.FarmMarket.objects.Product;
import com.example.FarmMarket.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepo productRepo;

    public List<Product> getProduct(){
        List<Product> result = productRepo.getProduct();
        return result;
    }
}
