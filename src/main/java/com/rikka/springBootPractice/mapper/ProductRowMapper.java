package com.rikka.springBootPractice.mapper;

import com.rikka.springBootPractice.model.Product;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class ProductRowMapper implements RowMapper<Product> {

    @Override
    public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
        Product product = Product.builder()
                .productId(rs.getInt("product_id"))
                .p(rs.getInt("p"))
                .s(rs.getInt("s"))
                .build();

        return product;
    }
}
