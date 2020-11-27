package com.example.FarmMarket.repository;

import com.example.FarmMarket.objects.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository <Product, Integer> {
}
