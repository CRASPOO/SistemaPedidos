// src/main/java/com/example/order/domain/service/OrderService.java
package com.example.order.domain.service;

import com.example.order.domain.model.Order;
import com.example.order.domain.port.in.OrderUseCase;
import com.example.order.domain.port.out.OrderRepositoryPort; // Importe a porta de saída
import com.example.order.application.shared.exceptions.OrderNotFoundException; // Sua exceção de domínio
import jakarta.transaction.Transactional; // Use jakarta.transaction.Transactional
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService implements OrderUseCase {

    private final OrderRepositoryPort orderRepositoryPort; // Injeta a porta de saída

    public OrderService(OrderRepositoryPort orderRepositoryPort) {
        this.orderRepositoryPort = orderRepositoryPort;
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepositoryPort.findAll();
    }

    @Override
    public List<Order> getOrdersByStep(String step) {
        return orderRepositoryPort.findAllByStep(step);
    }

    @Override
    @Transactional
    public Order createOrder(Order order) { // Recebe um objeto de domínio
        return orderRepositoryPort.save(order);
    }

    @Override
    @Transactional
    public Order advanceOrderStatus(Long id) {
        Order order = orderRepositoryPort.findById(id)
                .orElseThrow(() -> new OrderNotFoundException("Order with ID " + id + " not found."));
        order.advanceStatus(); // Lógica de negócio no próprio modelo de domínio
        return orderRepositoryPort.save(order);
    }

    @Override
    @Transactional
    public Order cancelOrder(Long id) {
        Order order = orderRepositoryPort.findById(id)
                .orElseThrow(() -> new OrderNotFoundException("Order with ID " + id + " not found for cancellation."));
        order.cancel(); // Lógica de negócio no próprio modelo de domínio
        return orderRepositoryPort.save(order);
    }

    @Override
    public Order getOrderById(Long id) {
        return orderRepositoryPort.findById(id)
                .orElseThrow(() -> new OrderNotFoundException("Order with ID " + id + " not found."));
    }
}