package com.example.order.application.ports.out;

import com.example.order.domain.entities.Order;
import com.example.order.infrastructure.adapters.in.web.Dtos.MercadoPagoPaymentResponseDTO;


public interface PaymentServicePort {
    MercadoPagoPaymentResponseDTO createQrCodePayment(Order order);
}