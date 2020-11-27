package com.example.FarmMarket.repository;

import com.example.FarmMarket.objects.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
