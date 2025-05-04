package com.rikka.springBootPractice.service;

public interface ProductService {

    void saveCsvData(String dataSource);

    void saveStockAndPrice(String dataSource);

    void updateTotal();

    void saveCsvDataByJdbc(String dataSource);

    void saveStockAndPriceByJdbc(String dateSource);
}
