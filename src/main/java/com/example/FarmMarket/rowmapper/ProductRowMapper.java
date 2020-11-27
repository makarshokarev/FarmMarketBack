package com.example.FarmMarket.rowmapper;

import com.example.FarmMarket.objects.Product;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductRowMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet resultSet, int i) throws SQLException {
        Product product = new Product();
        product.setId(resultSet.getInt("id"));
        product.setCategoryId(resultSet.getInt("categoryId"));
        product.setProductName(resultSet.getString("categoryName"));
        product.setProductDescription(resultSet.getString("productDescription"));
        product.setPrice(resultSet.getBigDecimal("price"));
        product.setAmount(resultSet.getBigDecimal("amount"));
        return product;
    }
}
