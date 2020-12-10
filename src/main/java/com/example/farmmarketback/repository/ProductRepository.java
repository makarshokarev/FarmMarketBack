package com.example.farmmarketback.repository;

import com.example.farmmarketback.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.parameters.P;

import java.util.List;


public interface ProductRepository extends JpaRepository<Product, Integer> {

    List<Product> findAllByProductNameContainingIgnoreCase(String searchWord);
    List<Product> findAll();
    List<Product> findAllByCategoryCategoryNameContainingIgnoreCase(String name);
    List<Product> findAllByProductNameContainingIgnoreCaseAndCategoryCategoryNameContainingIgnoreCase (String searchName, String searchCategory);
//    Query (SELECT * FROM product where product_name ILIKE :searchName and category_id = :searchCategory)


}
