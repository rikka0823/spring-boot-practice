package com.rikka.springBootPractice.dao;

import com.rikka.springBootPractice.model.Product;

import java.util.List;

public interface ProductDao {

    void saveCsvData(List<Product> productList);

    void saveStockAndPriceByJdbc(List <Product> productList);
}
