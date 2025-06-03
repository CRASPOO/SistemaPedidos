// src/main/java/com/example/order/application/config/OrderConfig.java
package com.example.order.application.config;

import com.example.order.application.adapter.out.persistence.JpaOrderRepositoryAdapter; // Novo nome
import com.example.order.application.adapter.out.persistence.SpringDataOrderRepository; // Seu repositório JPA
import com.example.order.domain.port.in.OrderUseCase;
import com.example.order.domain.port.out.OrderRepositoryPort; // Porta de saída (repositório)
import com.example.order.domain.service.OrderService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderConfig {

    @Bean
    public OrderRepositoryPort orderRepositoryPort(SpringDataOrderRepository springDataOrderRepository) {
        // Cria e expõe o adaptador de persistência como um bean da porta de saída
        return new JpaOrderRepositoryAdapter(springDataOrderRepository);
    }

    @Bean
    public OrderUseCase orderUseCase(OrderRepositoryPort orderRepositoryPort) {
        // Cria e expõe o serviço de domínio como um bean da porta de entrada
        return new OrderService(orderRepositoryPort);
    }
}