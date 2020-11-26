package com.example.FarmMarket.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

@Repository
public class FarmMarketRepository {
    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public void newSeller(String name, String email, String username, String password, String phone) {
        String sql = "insert into seller(name, email, username, password, phone) values(:m1, :m2, :m3, :m4, :m5)";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("m1", name);
        paramMap.put("m2", email);
        paramMap.put("m3", username);
        paramMap.put("m4", password);
        paramMap.put("m5", phone);
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

    public void updateSellerName(int ID, String newName){
        String sql = "UPDATE seller SET name =:m1 WHERE id = :m2";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("m1", newName);
        paramMap.put("m2", ID);
        jdbcTemplate.update(sql, paramMap);}

    public void updateSellerPersonalInformation(int ID, String personalInformation){
        String sql = "UPDATE seller SET personal_information =:m1 WHERE id = :m2";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("m1", personalInformation);
        paramMap.put("m2", ID);
        jdbcTemplate.update(sql, paramMap);
    }

    public void updateSellerAddress(int ID, String address){
        String sql = "UPDATE seller SET address =:m1 WHERE id = :m2";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("m1", address);
        paramMap.put("m2", ID);
        jdbcTemplate.update(sql, paramMap);
    }

    public void updateSellerEmail(int ID, String email) {
        String sql = "UPDATE seller SET email =:m1 WHERE id = :m2";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("m1", email);
        paramMap.put("m2", ID);
        jdbcTemplate.update(sql, paramMap);
    }

    public void updateSellerPhone(int ID, String phone) {
        String sql = "UPDATE seller SET phone =:m1 WHERE id = :m2";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("m1", phone);
        paramMap.put("m2", ID);
        jdbcTemplate.update(sql, paramMap);
    }

    public void updateSellerUsername(int ID, String username) {
        String sql = "UPDATE seller SET username =:m1 WHERE id = :m2";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("m1", username);
        paramMap.put("m2", ID);
        jdbcTemplate.update(sql, paramMap);
    }

    public void updateSellerPassword(int ID, String password) {
        String sql = "UPDATE seller SET password =:m1 WHERE id = :m2";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("m1", password);
        paramMap.put("m2", ID);
        jdbcTemplate.update(sql, paramMap);
    }
}
