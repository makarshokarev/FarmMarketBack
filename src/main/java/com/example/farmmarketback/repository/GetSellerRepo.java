package com.example.farmmarketback.repository;

import com.example.farmmarketback.objects.Seller1;
import com.example.farmmarketback.rowmapper.SellerRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class GetSellerRepo {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public Seller1 getSeller(int sellerId){
        String sql = "SELECT * FROM seller WHERE id = :m1";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("m1", sellerId);
        return jdbcTemplate.queryForObject(sql, paramMap, new SellerRowMapper());
    }
}
