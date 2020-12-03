package com.example.farmmarketback.repository;

import com.example.farmmarketback.objects.Product;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductRepository extends JpaRepository<Product, Integer> {
}
