package com.example.order.application.ports.in.Order;

import com.example.order.domain.entities.Order;
import com.example.order.infrastructure.adapters.in.web.Dtos.MercadoPagoPaymentResponseDTO; // <-- Importar o DTO

public interface CreateOrderUseCase {
    MercadoPagoPaymentResponseDTO execute(Order order); // <-- Tipo de retorno alterado
}