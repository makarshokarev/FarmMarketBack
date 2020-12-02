package com.example.FarmMarket.repository;

import com.example.FarmMarket.objects.Seller;
import com.example.FarmMarket.objects.Seller1;
import com.example.FarmMarket.rowmapper.SellerRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class GetSellerRepo {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public Seller1 getSeller(){
        String sql = "SELECT * FROM seller WHERE id = :m1";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("m1", 1);
        Seller1 seller =jdbcTemplate.queryForObject(sql, paramMap, new SellerRowMapper());
        return seller;
    }
}
