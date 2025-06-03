package com.example.order.customer;



public record CustomerResponseDTO(Long id, String name, String email, Long cpf) {
    public CustomerResponseDTO(Customer customer){
        this(customer.getId(), customer.getName(), customer.getEmail(), customer.getCpf());

    }
}
