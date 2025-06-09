package com.rikka.springBootPractice.mapper;

import com.rikka.springBootPractice.constant.Category;
import com.rikka.springBootPractice.model.Product;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class ProductFullRowMapper implements RowMapper<Product> {

    @Override
    public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
        Product product = Product.builder()
                .productId(rs.getInt("product_id"))
                .productName(rs.getString("product_name"))
                .category(Category.valueOf(rs.getString("category")))
                .imageUrl(rs.getString("image_url"))
                .price(rs.getInt("price"))
                .stock(rs.getInt("stock"))
                .description(rs.getString("description"))
                .createdDate(rs.getTimestamp("created_date"))
                .lastModifiedDate(rs.getTimestamp("last_modified_date"))
                .p(rs.getInt("p"))
                .s(rs.getInt("s"))
                .total(rs.getInt("total"))
                .build();

        return product;
    }
}
