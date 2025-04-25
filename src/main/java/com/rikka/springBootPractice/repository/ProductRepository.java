package com.rikka.springBootPractice.repository;

import com.rikka.springBootPractice.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Modifying
    @Query(value = """
            UPDATE product
            SET s = ?2, p = ?3
            WHERE product_id = ?1
            """, nativeQuery = true)
    void updateStockAndPrice(Integer id, Integer stock, Integer price);

    @Query(value = """
            SELECT product_id, p, s
            FROM product
            """, nativeQuery = true)
    List<Object[]> getPriceAndStock();

    @Modifying
    @Query(value = """
            UPDATE product
            SET total = ?2 * ?3
            WHERE product_id = ?1
            """, nativeQuery = true)
    void updateTotal(Integer id, Integer stock, Integer price);
}
