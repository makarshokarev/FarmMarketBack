package com.example.farmmarketback.repository;

import com.example.farmmarketback.objects.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerRepository extends JpaRepository<Seller,Integer> {
}