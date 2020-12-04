package com.example.farmmarketback.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class UploadRepo {

    @Autowired
    public NamedParameterJdbcTemplate jdbcTemplate;

    public void uploadFile(byte[] file){
        String sql = "INSERT INTO files (file) value (:m1)";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("m1", file);
        jdbcTemplate.update(sql, paramMap);
    }
}
