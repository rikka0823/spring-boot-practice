package com.rikka.springBootPractice.model;

import com.rikka.springBootPractice.constant.Category;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

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
    private Date createdDate;
    private Date lastModifiedDate;
    private int s;
    private int p;
    private int total;
}
