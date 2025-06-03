// src/main/java/com/example/order/domain/port/out/OrderRepositoryPort.java
package com.example.order.domain.port.out;

import com.example.order.domain.model.Order;
import java.util.List;
import java.util.Optional;

public interface OrderRepositoryPort {
    List<Order> findAll();
    Optional<Order> findById(Long id);
    List<Order> findAllByStep(String step);
    Order save(Order order);
    // Void delete(Long id); // Se a exclusão física for necessária
}