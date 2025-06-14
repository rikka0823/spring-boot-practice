package com.rikka.springBootPractice.model;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public class BookDto {

    @NotBlank
    private String title;

    @NotBlank
    private String author;

    @NotNull
    @DecimalMin(value = "0", inclusive = false)
    private BigDecimal price;

    @NotNull
    private LocalDate publishedDate;

    public BookDto() {
    }

    public BookDto(String title, String author, BigDecimal price, LocalDate publishedDate) {
        this.title = title;
        this.author = author;
        this.price = price;
        this.publishedDate = publishedDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public LocalDate getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(LocalDate publishedDate) {
        this.publishedDate = publishedDate;
    }
}
