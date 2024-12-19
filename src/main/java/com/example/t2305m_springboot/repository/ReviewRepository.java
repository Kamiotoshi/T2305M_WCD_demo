package com.example.t2305m_springboot.repository;

import com.example.t2305m_springboot.entity.Reviews;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Reviews, Long> {
}
