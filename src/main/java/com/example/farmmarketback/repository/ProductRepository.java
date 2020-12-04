package com.example.farmmarketback.repository;

import com.example.farmmarketback.Responses.ProductGetFullInfo;
import com.example.farmmarketback.objects.Product;
import com.example.farmmarketback.objects.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;
import java.util.List;


public interface ProductRepository extends JpaRepository<Product, Integer> {

    List<Product> findAllByProductName(String productName);
    List<Product> findAll();
}