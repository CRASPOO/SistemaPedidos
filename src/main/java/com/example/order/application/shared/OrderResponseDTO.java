// src/main/java/com/example/order/shared/OrderResponseDTO.java
package com.example.order.application.shared;

import com.example.order.domain.model.Order;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

public record OrderResponseDTO(
        Long id,
        Long idcustomer,
        String step,
        LocalDate date,
        LocalTime time,
        Integer price,
        Duration timeDifference
) {
    // Construtor principal para criar a partir de um objeto de domínio Order
    public OrderResponseDTO(Order order) {
        this(
                order.getId(),
                order.getIdcustomer(),
                order.getStep(),
                order.getDate(),
                order.getTime(),
                order.getPrice(),
                calculateTimeDifference(order.getTime())
        );
    }

    // Método estático para mapear do modelo de domínio (melhor para uso em streams)
    public static OrderResponseDTO fromDomain(Order order) {
        return new OrderResponseDTO(order);
    }

    // Construtor para mensagens de erro ou respostas parciais
    public OrderResponseDTO(Long id, String errorMessage) {
        this(id, null, errorMessage, null, null, null, null);
    }

    private static Duration calculateTimeDifference(LocalTime orderTime) {
        if (orderTime == null) {
            return null; // Ou Duration.ZERO, dependendo do requisito
        }
        LocalTime currentTime = LocalTime.now();
        return Duration.between(orderTime, currentTime);
    }
}