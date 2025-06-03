package com.example.order.application.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SpringDataProductRepository extends JpaRepository<ProductEntity, Long> {
    List<ProductEntity> findAllByActiveTrue();
    List<ProductEntity> findAllByCategory(Long categoryId);
}