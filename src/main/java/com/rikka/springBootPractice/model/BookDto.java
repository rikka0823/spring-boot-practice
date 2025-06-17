package com.rikka.springBootPractice.model;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public class BookDto {

    @NotBlank(message = "not blank")
    private String title;

    @NotBlank(message = "not blank")
    private String author;

    @NotNull(message = "not null")
    @DecimalMin(value = "0", inclusive = false, message = "The value must be greater than 0")
    private BigDecimal price;

    @NotNull(message = "not null")
    private LocalDate publishedDate;

    public BookDto() {
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
