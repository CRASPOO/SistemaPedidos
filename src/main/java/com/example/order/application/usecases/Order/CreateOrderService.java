// src/main/java/com/example/order/application/usecases/Order/CreateOrderService.java
package com.example.order.application.usecases.Order;

import com.example.order.application.ports.in.Order.CreateOrderUseCase;
import com.example.order.application.ports.out.OrderRepositoryPort;
import com.example.order.application.ports.out.PaymentServicePort;
import com.example.order.application.usecases.ProcessPaymentWebhookUseCase;
import com.example.order.domain.entities.Order;
import com.example.order.infrastructure.adapters.in.web.Dtos.MercadoPagoPaymentResponseDTO;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import java.util.concurrent.TimeUnit;

@Service
public class CreateOrderService implements CreateOrderUseCase {

    private final OrderRepositoryPort orderRepositoryPort;
    private final PaymentServicePort paymentServicePort;
    private final ProcessPaymentWebhookUseCase processPaymentWebhookUseCase; // Adicione 'final' aqui

    // CORREÇÃO: Adicione a injeção de dependência no construtor
    public CreateOrderService(OrderRepositoryPort orderRepositoryPort, PaymentServicePort paymentServicePort, ProcessPaymentWebhookUseCase processPaymentWebhookUseCase) {
        this.orderRepositoryPort = orderRepositoryPort;
        this.paymentServicePort = paymentServicePort;
        this.processPaymentWebhookUseCase = processPaymentWebhookUseCase; // Atribua a dependência
    }

    @Override
    @Transactional
    public MercadoPagoPaymentResponseDTO execute(Order order) {
        Order savedOrder = orderRepositoryPort.save(order);



        MercadoPagoPaymentResponseDTO qrCodeResponse = paymentServicePort.createQrCodePayment(savedOrder);
        System.out.println("-> WEBHOOK MOCK: Simulará atualização para o pedido " + savedOrder.getId() + " com status " + qrCodeResponse.getStatus() + " em 10 segundos...");
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        // AQUI NÃO É O LUGAR IDEAL para chamar o webhook, pois ele deve ser assíncrono.
        // A chamada deveria vir do serviço de pagamento.
        // Se você insistir em chamar aqui, o comportamento será síncrono.
        processPaymentWebhookUseCase.execute(savedOrder.getId(), qrCodeResponse.getStatus());

        /* Código para Salvar QRCODE NA TABELA
        if (qrCodeResponse != null && qrCodeResponse.getQrData() != null) {
            savedOrder.setQrCodeData(qrCodeResponse.getQrData());
            orderRepositoryPort.save(savedOrder);
        }*/
        System.out.println("-> WEBHOOK MOCK: Finaliza atualização do perdido " + savedOrder.getId() );
        return qrCodeResponse;
    }
}