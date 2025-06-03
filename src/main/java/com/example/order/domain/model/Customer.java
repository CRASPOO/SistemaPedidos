package com.example.order.domain.model;

import com.example.order.application.shared.CustomerRequestDTO;
import java.util.Objects;

public class Customer {
    private Long id;
    private String name;
    private String email;
    private Long cpf;

    // Construtor para criação de um novo cliente (sem ID inicial)
    public Customer(String name, String email, Long cpf) {
        this(null, name, email, cpf);
    }

    // Construtor para reconstruir o cliente do banco (com ID)
    public Customer(Long id, String name, String email, Long cpf) {
        // Validações básicas podem ser feitas no construtor
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Customer name cannot be empty.");
        }
        if (cpf == null) {
            throw new IllegalArgumentException("Customer CPF cannot be null.");
        }
        this.id = id;
        this.name = name;
        this.email = email;
        this.cpf = cpf;
    }

    // Construtor para simplificar a criação a partir de um DTO de requisição (pode ser substituído por um mapper)
    public Customer(CustomerRequestDTO data) {
        this(data.id(), data.name(), data.email(), data.cpf());
    }

    // Métodos de negócio do cliente
    public void updateDetails(String name, String email) {
        if (name != null && !name.trim().isEmpty()) {
            this.name = name;
        }
        if (email != null && !email.trim().isEmpty()) {
            this.email = email;
        }
        // Poderia ter mais lógica de validação ou regras aqui
    }

    // Getters
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Long getCpf() {
        return cpf;
    }

    // Setter para ID é comum ao carregar do BD. Outros setters apenas se a lógica permitir.
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(id, customer.id) && Objects.equals(cpf, customer.cpf);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cpf);
    }
}