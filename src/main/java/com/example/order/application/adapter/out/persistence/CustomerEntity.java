package com.example.order.application.adapter.out.persistence;

import com.example.order.domain.model.Customer; // Importa a entidade de domínio

import jakarta.persistence.*;

@Entity
@Table(name = "customer")
public class CustomerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    @Column(unique = true) // CPF geralmente é único no banco de dados
    private Long cpf;

    // Construtor padrão exigido pelo JPA
    public CustomerEntity() {
    }

    // Construtor para mapeamento do domínio para a entidade JPA
    public CustomerEntity(Long id, String name, String email, Long cpf) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.cpf = cpf;
    }

    // Método para mapear de entidade de domínio para entidade JPA
    public static CustomerEntity fromDomain(Customer domainCustomer) {
        return new CustomerEntity(
                domainCustomer.getId(),
                domainCustomer.getName(),
                domainCustomer.getEmail(),
                domainCustomer.getCpf()
        );
    }

    // Método para mapear de entidade JPA para entidade de domínio
    public Customer toDomain() {
        return new Customer(
                this.id,
                this.name,
                this.email,
                this.cpf
        );
    }

    // Getters e Setters (necessários para JPA)
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public Long getCpf() { return cpf; }
    public void setCpf(Long cpf) { this.cpf = cpf; }
}