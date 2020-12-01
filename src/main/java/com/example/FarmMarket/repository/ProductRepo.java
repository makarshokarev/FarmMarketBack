package com.example.FarmMarket.repository;

import com.example.FarmMarket.objects.Product;
import com.example.FarmMarket.rowmapper.ProductRowMapper;
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
        List<Product> result = jdbcTemplate.query(sql, paramMap, new ProductRowMapper());
        return result;
    }
}
