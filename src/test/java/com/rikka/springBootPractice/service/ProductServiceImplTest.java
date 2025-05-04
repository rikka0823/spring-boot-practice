package com.rikka.springBootPractice.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ProductServiceImplTest {

    @Autowired
    private ProductService productService;

    @Test
    public void saveCsvData() {
        productService.saveCsvData("data/product.csv");
    }

    @Test
    public void saveStockAndPrice() {
        productService.saveStockAndPrice("data/stockAndPrice.csv");
    }

    @Test
    public void updateTotal() {
        productService.updateTotal();
    }

    @Test
    public void saveCsvDataByJdbc() {
        productService.saveCsvDataByJdbc("data/product.csv");
    }

    @Test
    public void saveStockAndPriceByJdbc() {
        productService.saveStockAndPriceByJdbc("data/stockAndPrice.csv");
    }
}