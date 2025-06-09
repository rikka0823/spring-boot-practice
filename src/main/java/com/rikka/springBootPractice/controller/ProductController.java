package com.rikka.springBootPractice.controller;

import com.opencsv.CSVWriter;
import com.rikka.springBootPractice.model.Product;
import com.rikka.springBootPractice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/downloadCSV")
    public ResponseEntity<byte[]> downloadCSV() throws IOException {
        List<Product> productList = productService.getProductList();

        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
             CSVWriter csvWriter = new CSVWriter(new OutputStreamWriter(byteArrayOutputStream, StandardCharsets.UTF_8))) {
            csvWriter.writeNext(new String[]{"product_id", "product_name", "category", "image_url",
                    "price", "stock", "description", "created_date", "last_modified_date", "p", "s", "total"});

            for (Product product : productList) {
                csvWriter.writeNext(new String[]{
                        String.valueOf(product.getProductId()),
                        product.getProductName(),
                        product.getCategory().name(),
                        product.getImageUrl(),
                        String.valueOf(product.getPrice()),
                        String.valueOf(product.getStock()),
                        product.getDescription(),
                        product.getCreatedDate().toString(),
                        product.getLastModifiedDate().toString(),
                        String.valueOf(product.getP()),
                        String.valueOf(product.getS()),
                        String.valueOf(product.getTotal())
                });
            }

            csvWriter.flush();
            byte[] csvData = byteArrayOutputStream.toByteArray();

            if (csvData.length == 0) {
                System.out.println("CSV data is empty.");
            }

            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"products.csv\"");
            headers.add(HttpHeaders.CONTENT_TYPE, "text/csv");

            return new ResponseEntity<>(csvData, headers, HttpStatus.OK);
        }
    }
}
