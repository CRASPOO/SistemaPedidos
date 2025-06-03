// src/main/java/com/example/order/shared/OrderRequestDTO.java
package com.example.order.application.shared;

import com.example.order.domain.model.Order;
import java.time.LocalDate;
import java.time.LocalTime;

public record OrderRequestDTO(
        Long idcustomer,
        String step,
        LocalDate date,
        LocalTime time,
        Integer price,
        String details
) {
    // Método para converter DTO de requisição para o modelo de domínio
    public Order toDomain() {
        // Ao criar, o ID é nulo, pois será gerado pelo banco de dados
        return new Order(this.idcustomer(), this.step(), this.date(), this.time(), this.price(), this.details());
    }
}