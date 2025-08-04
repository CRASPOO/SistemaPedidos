package com.example.order.application.usecases;

public interface ProcessPaymentWebhookUseCase {
    void execute(Long orderId, String paymentStatus);
}
