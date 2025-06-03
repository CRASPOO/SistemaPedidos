// src/main/java/com/example/order/domain/port/in/OrderUseCase.java
package com.example.order.domain.port.in;

import com.example.order.domain.model.Order; // O use case trabalha com o modelo de domínio
import java.util.List;

public interface OrderUseCase {
    List<Order> getAllOrders();
    List<Order> getOrdersByStep(String step);
    Order createOrder(Order order); // Recebe o modelo de domínio
    Order advanceOrderStatus(Long id);
    Order cancelOrder(Long id);
    Order getOrderById(Long id);
}