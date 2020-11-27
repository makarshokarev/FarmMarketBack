package com.example.FarmMarket.repository;

import com.example.FarmMarket.objects.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerRepository extends JpaRepository<Seller,Integer> {
}
