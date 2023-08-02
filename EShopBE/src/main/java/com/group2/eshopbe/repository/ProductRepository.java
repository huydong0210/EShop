package com.group2.eshopbe.repository;

import com.group2.eshopbe.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query(value = "select * from Product", nativeQuery = true)
    List<Product> findAllProducts();
}
