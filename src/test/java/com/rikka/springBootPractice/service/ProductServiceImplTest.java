package com.rikka.springBootPractice.service;

import com.opencsv.CSVReader;
import com.rikka.springBootPractice.constant.Category;
import com.rikka.springBootPractice.entity.Product;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
public class ProductServiceImplTest {

    @Autowired
    private ProductService productService;

    @Test
    public void saveData() {
        try (
                InputStream inputStream = getClass().getClassLoader().getResourceAsStream("data/product.csv");
                InputStreamReader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
                CSVReader csvReader = new CSVReader(reader)
        ) {
            List<Product> productList = new ArrayList<>();

            csvReader.readNext();
            String[] row;
            while ((row = csvReader.readNext()) != null) {
                Product product = Product.builder()
                        .productId(Integer.parseInt(row[0]))
                        .productName(row[1])
                        .category(Category.valueOf(row[2]))
                        .imageUrl(row[3])
                        .price(Integer.parseInt(row[4]))
                        .stock(Integer.parseInt(row[5]))
                        .description(row[6])
                        .createdDate(Timestamp.valueOf(row[7]))
                        .lastModifiedDate(Timestamp.valueOf(row[8]))
                        .build();
                productList.add(product);
            }

            productService.saveData(productList);
        } catch (Exception e) {
            log.warn("exception:", e);
        }
    }
}