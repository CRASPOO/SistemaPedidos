// src/main/java/com/example/order/application/usecases/CancelOrderService.java
package com.example.order.application.usecases.Order;
import com.example.order.application.ports.in.Order.CancelOrderUseCase;
import com.example.order.application.ports.out.OrderRepositoryPort;
import com.example.order.domain.entities.Order;
import com.example.order.shared.exceptions.OrderNotFoundException;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

@Service
public class CancelOrderService implements CancelOrderUseCase {
    private final OrderRepositoryPort orderRepositoryPort;
    public CancelOrderService(OrderRepositoryPort orderRepositoryPort) {
        this.orderRepositoryPort = orderRepositoryPort;
    }
    @Override
    @Transactional
    public Order execute(Long orderId) {
        Order order = orderRepositoryPort.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Pedido não encontrado com ID: " + orderId));
        if ("Cancelado".equals(order.getStep())) {
            throw new IllegalArgumentException("Pedido Já Cancelado.");
        }
        order.setStep("Cancelado");

        return orderRepositoryPort.save(order);
    }
}