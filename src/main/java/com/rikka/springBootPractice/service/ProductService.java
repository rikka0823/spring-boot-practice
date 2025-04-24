package com.rikka.springBootPractice.service;

import com.rikka.springBootPractice.entity.Product;

import java.util.List;

public interface ProductService {

    void saveData(List<Product> productList);
}
