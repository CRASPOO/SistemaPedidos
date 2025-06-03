package com.example.order.application.config;

import com.example.order.application.adapter.out.persistence.JpaCustomerRepositoryAdapter;
import com.example.order.application.adapter.out.persistence.SpringDataCustomerRepository;
import com.example.order.domain.port.in.CustomerUseCase;
import com.example.order.domain.port.out.CustomerRepositoryPort;
import com.example.order.domain.service.CustomerService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomerConfig {

    @Bean
    public CustomerRepositoryPort customerRepositoryPort(SpringDataCustomerRepository springDataCustomerRepository) {
        return new JpaCustomerRepositoryAdapter(springDataCustomerRepository);
    }

    @Bean
    public CustomerUseCase customerUseCase(CustomerRepositoryPort customerRepositoryPort) {
        return new CustomerService(customerRepositoryPort);
    }
}