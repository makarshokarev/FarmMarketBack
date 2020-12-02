package com.example.farmmarketback.rowmapper;

import com.example.farmmarketback.objects.Seller1;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SellerRowMapper implements RowMapper<Seller1> {
    @Override
    public Seller1 mapRow(ResultSet resultSet, int i) throws SQLException {
        Seller1 seller = new Seller1();
        seller.setId(resultSet.getInt("id"));
        seller.setName(resultSet.getString("name"));
        seller.setEmail(resultSet.getString("email"));
        seller.setUsername(resultSet.getString("username"));
        seller.setAddress(resultSet.getString("address"));
        seller.setPassword(resultSet.getString("password"));
        seller.setPersonalInformation(resultSet.getString("personal_information"));
        seller.setPhone(resultSet.getString("phone"));
        return seller;
    }
}
