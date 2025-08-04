// src/main/java/com/example/order/infrastructure/adapters/out/persistence/OrderJpaAdapter.java
package com.example.order.infrastructure.adapters.out.persistence.JpaAdapter;
import com.example.order.application.ports.out.OrderRepositoryPort;
import com.example.order.domain.entities.Order;
import com.example.order.infrastructure.adapters.out.persistence.JpaEntity.OrderJpaEntity;
import com.example.order.infrastructure.adapters.out.persistence.SpringDataJpa.SpringDataJpaOrderRepository;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class OrderJpaAdapter implements OrderRepositoryPort {
    private final SpringDataJpaOrderRepository jpaRepository;

    public OrderJpaAdapter(SpringDataJpaOrderRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }
    @Override
    public List<Order> findAll() {
        return jpaRepository.findAll().stream().map(OrderJpaEntity::toDomain).collect(Collectors.toList());
    }
    @Override
    public List<Order> findByStep(String step) {
        return jpaRepository.findByStep(step).stream().map(OrderJpaEntity::toDomain).collect(Collectors.toList());
    }
    @Override
    public Optional<Order> findById(Long id) {
        return jpaRepository.findById(id).map(OrderJpaEntity::toDomain);
    }
    @Override
    public Order save(Order order) {
        OrderJpaEntity jpaEntity = OrderJpaEntity.fromDomain(order);
        OrderJpaEntity savedEntity = jpaRepository.save(jpaEntity);
        return savedEntity.toDomain();
    }
}