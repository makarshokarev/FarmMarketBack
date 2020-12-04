package com.example.farmmarketback.rowmapper;

import com.example.farmmarketback.entity.Product;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductRowMapper implements RowMapper<Product> {
    @Override
    public Product mapRow(ResultSet resultSet, int i) throws SQLException {
        Product product = new Product();
        product.setId(resultSet.getInt("id"));
        //product.setCategoryId(resultSet.getInt("category_id")); // TODO
        product.setProductName(resultSet.getString("product_name"));
        product.setProductDescription(resultSet.getString("product_description"));
        product.setPrice(resultSet.getBigDecimal("price"));
        product.setAmount(resultSet.getBigDecimal("amount"));
        return product;
    }
}
