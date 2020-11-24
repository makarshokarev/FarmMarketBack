package com.example.FarmMarket.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

@Repository
public class FarmMarketRepository {
    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public void newSeller(String name, String personal_information, String address, String phone) {
        String sql = "insert into seller(name, personal_information, address, phone) values(:name, :personal_information, :address, :phone)";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("name", name);
        paramMap.put("personal_information", personal_information);
        paramMap.put("address", address);
        paramMap.put("phone", phone);
        jdbcTemplate.update(sql, paramMap);
    }

    public void newProduct(String category, String product_name, String product_description,
                           BigInteger price, BigInteger amount) {
        String sql = "insert into product (category, product_name, product_description, price, amount) values " +
                "(:category, :product_name, :product_description, :price, :amount)";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("category", category);
        paramMap.put("product_name", product_name);
        paramMap.put("product_description", product_description);
        paramMap.put("price", price);
        paramMap.put("amount", amount);
        jdbcTemplate.update(sql, paramMap);
    }
}
