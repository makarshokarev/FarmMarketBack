package com.example.farmmarketback.repository;


import com.example.farmmarketback.objects.Category;

import com.example.farmmarketback.rowmapper.CategoryRowMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CategoryRepo {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public List<Category> getCategory() {
        String sql = "SELECT * FROM category";
        Map<String, Object> paramMap = new HashMap<>();
        return jdbcTemplate.query(sql, paramMap, new CategoryRowMapper());
    }
}
