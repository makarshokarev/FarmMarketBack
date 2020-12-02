package com.example.farmmarketback.rowmapper;

import com.example.farmmarketback.objects.Category;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoryRowMapper implements RowMapper<Category> {

    @Override
    public Category mapRow(ResultSet resultSet, int i) throws SQLException {
        Category category = new Category();
        category.setId(resultSet.getInt("id"));
        category.setCategoryName(resultSet.getString("category_name"));
        return category;
    }

}
