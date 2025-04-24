package com.rikka.springBootPractice.service;

import com.rikka.springBootPractice.entity.Product;
import com.rikka.springBootPractice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Transactional
    @Override
    public void saveData(List<Product> productList) {
        productRepository.saveAll(productList);
    }
}
