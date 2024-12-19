package com.example.t2305m_springboot.repository;

import com.example.t2305m_springboot.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    List<Product> findAllByNameOrPrice(String name, Double price);// name = name or price = price
    List<Product> findAllByNameAndPrice(String name, Double price);
    // filter
    @Query("SELECT p from Product p"+
            " WHERE (:name is NULL or p.name LIKE %:name%)"+
            " AND (:minPrice is NULL or p.price >= :minPrice)"+
            " AND (:maxPrice is NULL or p.price <= :maxPrice)"
    )
    List<Product> filter(@Param("name") String name,@Param("minPrice") Double minPrice,
                         @Param("maxPrice") Double maxPrice);
}