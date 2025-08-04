// src/main/java/com/example/order/domain/entities/Customer.java
package com.example.order.domain.entities;

import java.util.Objects;

public class Customer {

    private Long id;
    private String name;
    private String email;
    private Long cpf;

    public Customer(String name, String email, Long cpf) {
        this.name = name;
        this.email = email;
        this.cpf = cpf;
    }

    public Customer(Long id, String name, String email, Long cpf) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.cpf = cpf;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public Long getCpf() { return cpf; }

    public void setId(Long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }
    public void setCpf(Long cpf) { this.cpf = cpf; }

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