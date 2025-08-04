package com.example.order.application.mock;

import com.example.order.application.ports.in.Category.GetAllCategoriesUseCase;
import com.example.order.application.ports.out.PaymentServicePort;
import com.example.order.application.usecases.ProcessPaymentWebhookUseCase;
import com.example.order.domain.entities.Order;
import com.example.order.infrastructure.adapters.in.web.Dtos.MercadoPagoPaymentResponseDTO;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;


import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Service
@Profile("dev") // <-- Ativada apenas quando o perfil 'dev' está ativo
public class MockPaymentService implements PaymentServicePort {

    private final Random random = new Random(); // Instância para gerar números aleatórios
    @Override
    public MercadoPagoPaymentResponseDTO createQrCodePayment(Order order) {
        System.out.println("--- Usando o Mock de Pagamento. Nenhuma chamada à API real foi feita. ---");

        // Simula uma resposta de sucesso da API
        MercadoPagoPaymentResponseDTO mockResponse = new MercadoPagoPaymentResponseDTO();

        int randomNumber = random.nextInt(100);
        if (randomNumber < 33) { // 80% de chance

            mockResponse.setStatus("APROVADO");
            mockResponse.setMessage("Pagamento simulado criado com sucesso.");
            mockResponse.setQrData("MOCK_QR_DATA_APROVADO_" + order.getId());
        } else if (randomNumber < 66) { // 10% de chance

            mockResponse.setStatus("RECUSADO");
            mockResponse.setMessage("Pagamento simulado criado com sucesso.");
            mockResponse.setQrData("MOCK_QR_DATA_RECUSADO_" + order.getId());
        } else { // 10% de chance

            mockResponse.setStatus("PENDENTE"); // O status na resposta do pagamento pode ser nulo
            mockResponse.setMessage("Pagamento sem retorno");
            mockResponse.setQrData("MOCK_QR_DATA_PENDENTE_" + order.getId());
        }



        return mockResponse;
    }
}