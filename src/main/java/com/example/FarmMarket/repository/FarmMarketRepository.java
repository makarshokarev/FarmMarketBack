package com.example.FarmMarket.repository;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class FarmMarketRepository {
    private NamedParameterJdbcTemplate jdbcTemplate;
}
