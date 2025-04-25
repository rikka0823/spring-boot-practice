package com.rikka.springBootPractice.entity;

import com.rikka.springBootPractice.constant.Category;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "product")
public class Product {

    @Id
    @Column(name = "product_id")
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productId;

    @Column(name = "product_name")
    private String productName;

    @Enumerated(EnumType.STRING)
    @Column(name = "category")
    private Category category;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "price")
    private int price;

    @Column(name = "stock")
    private int stock;

    @Column(name = "description")
    private String description;

    @Column(name = "created_date")
    private Timestamp createdDate;

    @Column(name = "last_modified_date")
    private Timestamp lastModifiedDate;

    @Column(name = "s")
    private Integer s;

    @Column(name = "p")
    private Integer p;
}

