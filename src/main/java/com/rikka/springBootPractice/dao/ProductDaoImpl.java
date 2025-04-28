package com.rikka.springBootPractice.dao;

import com.rikka.springBootPractice.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductDaoImpl implements ProductDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public void saveCsvData(List<Product> productList) {
        String sql = """
                INSERT INTO product (product_id, product_name, category, image_url, price, stock,
                description, created_date, last_modified_date)
                VALUES (:productId, :productName, :category, :imageUrl, :price, :stock,
                :description, :createdDate, :lastModifiedDate)
                """;

        MapSqlParameterSource[] params = new MapSqlParameterSource[productList.size()];
        for (int i = 0; i < productList.size(); i++) {
            Product product = productList.get(i);
            params[i] = new MapSqlParameterSource()
                    .addValue("productId", product.getProductId())
                    .addValue("productName", product.getProductName())
                    .addValue("category", product.getCategory().name())
                    .addValue("imageUrl", product.getImageUrl())
                    .addValue("price", product.getPrice())
                    .addValue("stock", product.getStock())
                    .addValue("description", product.getDescription())
                    .addValue("createdDate", product.getCreatedDate())
                    .addValue("lastModifiedDate", product.getLastModifiedDate());
        }

        namedParameterJdbcTemplate.batchUpdate(sql, params);
    }
}
