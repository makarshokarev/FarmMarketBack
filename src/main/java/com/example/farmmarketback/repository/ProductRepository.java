package com.example.farmmarketback.repository;

import com.example.farmmarketback.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ProductRepository extends JpaRepository<Product, Integer> {

    List<Product> findAllByProductNameContainingIgnoreCase(String productName);
    List<Product> findAll();
    List<Product> findAllByCategoryCategoryNameContainingIgnoreCase(String name);
    void deleteProductById(int id);
}