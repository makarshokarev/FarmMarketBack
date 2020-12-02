package com.example.farmmarketback.repository;

import com.example.farmmarketback.objects.Seller_entity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerRepository extends JpaRepository<Seller_entity,Integer> {
}