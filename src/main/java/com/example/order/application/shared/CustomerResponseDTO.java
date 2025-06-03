package com.example.order.application.shared;

import com.example.order.domain.model.Customer;

public record CustomerResponseDTO(
        Long id,
        String name,
        String email,
        Long cpf
) {
    // Método estático para mapear de entidade de domínio para DTO
    public static CustomerResponseDTO fromDomain(Customer customer) {
        return new CustomerResponseDTO(
                customer.getId(),
                customer.getName(),
                customer.getEmail(),
                customer.getCpf()
        );
    }
}