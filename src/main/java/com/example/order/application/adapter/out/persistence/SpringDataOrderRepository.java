// src/main/java/com/example/order/application/adapter/out/persistence/SpringDataOrderRepository.java
package com.example.order.application.adapter.out.persistence;

import com.example.order.domain.model.Order; // Importe o modelo de domínio
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpringDataOrderRepository extends JpaRepository<OrderEntity, Long> {
    List<OrderEntity> findAllByStep(String step);
}