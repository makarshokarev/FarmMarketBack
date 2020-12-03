package com.example.farmmarketback.service;

import com.example.farmmarketback.objects.Product;
import com.example.farmmarketback.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepo productRepo;

    public List<Product> getProduct(){
        return productRepo.getProduct();
    }
}
