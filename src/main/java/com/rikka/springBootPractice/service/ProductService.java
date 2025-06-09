package com.rikka.springBootPractice.service;

import com.rikka.springBootPractice.model.Product;

import java.util.List;

public interface ProductService {

    void saveCsvData(String dataSource);

    void saveStockAndPrice(String dataSource);

    void updateTotal();

    void saveCsvDataByJdbc(String dataSource);

    void saveStockAndPriceByJdbc(String dateSource);

    void updateTotalByJdbc();

    List<Product> getProductList();
}
