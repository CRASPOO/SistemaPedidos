// src/main/java/com/example/order/application/usecases/FindOrderService.java
package com.example.order.application.usecases;

import com.example.order.application.ports.in.FindOrderUseCase;
import com.example.order.application.ports.out.OrderRepositoryPort;
import com.example.order.domain.entities.Order;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FindOrderService implements FindOrderUseCase {

    private final OrderRepositoryPort orderRepositoryPort;

    public FindOrderService(OrderRepositoryPort orderRepositoryPort) {
        this.orderRepositoryPort = orderRepositoryPort;
    }

    @Override
    public Optional<Order> execute(Long orderId) {
        return orderRepositoryPort.findById(orderId);
    }
}