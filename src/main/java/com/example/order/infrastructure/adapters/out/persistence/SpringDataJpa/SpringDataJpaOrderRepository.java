// src/main/java/com/example/order/infrastructure/adapters/out/persistence/SpringDataJpaOrderRepository.java
package com.example.order.infrastructure.adapters.out.persistence.SpringDataJpa;

import com.example.order.infrastructure.adapters.out.persistence.JpaEntity.OrderJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SpringDataJpaOrderRepository extends JpaRepository<OrderJpaEntity, Long> {
    List<OrderJpaEntity> findByStep(String step); // findByStep em vez de findByStatus
}