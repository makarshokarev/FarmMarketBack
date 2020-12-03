package com.example.farmmarketback.repository;

import com.example.farmmarketback.objects.Product;
import com.example.farmmarketback.rowmapper.ProductRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ProductRepo {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public List<Product> getProduct() {
        String sql = "SELECT * FROM product";
        Map<String, Object> paramMap = new HashMap<>();
        return jdbcTemplate.query(sql, paramMap, new ProductRowMapper());
    }
}
