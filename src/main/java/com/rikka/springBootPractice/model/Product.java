package com.rikka.springBootPractice.model;

import com.rikka.springBootPractice.constant.Category;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Builder
@Data
public class Product {

    private int productId;
    private String productName;
    private Category category;
    private String imageUrl;
    private int price;
    private int stock;
    private String description;
    private Timestamp createdDate;
    private Timestamp lastModifiedDate;
    private int s;
    private int p;
}
