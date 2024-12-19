package com.example.t2305m_springboot.repository;

import com.example.t2305m_springboot.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
    List<Category> findAllByNameContains(String name); //name like %search%
}