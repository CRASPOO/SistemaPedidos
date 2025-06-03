package com.example.order.application.config;

import com.example.order.application.adapter.out.persistence.JpaProductRepositoryAdapter;
import com.example.order.application.adapter.out.persistence.SpringDataProductRepository;
import com.example.order.domain.port.in.ProductUseCase;
import com.example.order.domain.port.out.ProductRepositoryPort;
import com.example.order.domain.service.ProductService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductConfig {

    @Bean
    public ProductRepositoryPort productRepositoryPort(SpringDataProductRepository springDataProductRepository) {
        return new JpaProductRepositoryAdapter(springDataProductRepository);
    }

    @Bean
    public ProductUseCase productUseCase(ProductRepositoryPort productRepositoryPort) {
        return new ProductService(productRepositoryPort);
    }
}