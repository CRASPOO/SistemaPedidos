// src/main/java/com/example/order/infrastructure/adapters/out/persistence/CustomerJpaEntity.java
package com.example.order.infrastructure.adapters.out.persistence.JpaEntity;

import com.example.order.domain.entities.Customer;
import jakarta.persistence.*;

@Entity
@Table(name = "customer")
public class CustomerJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private Long cpf;

    public CustomerJpaEntity() {}

    public CustomerJpaEntity(Long id, String name, String email, Long cpf) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.cpf = cpf;
    }

    public Customer toDomain() {
        return new Customer(this.id, this.name, this.email, this.cpf);
    }

    public static CustomerJpaEntity fromDomain(Customer domain) {
        return new CustomerJpaEntity(domain.getId(), domain.getName(), domain.getEmail(), domain.getCpf());
    }

    // Getters e setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public Long getCpf() { return cpf; }
    public void setCpf(Long cpf) { this.cpf = cpf; }
}