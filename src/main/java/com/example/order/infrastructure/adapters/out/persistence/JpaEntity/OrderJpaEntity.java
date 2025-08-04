// src/main/java/com/example/order/infrastructure/adapters/out/persistence/OrderJpaEntity.java
package com.example.order.infrastructure.adapters.out.persistence.JpaEntity;

import com.example.order.domain.entities.Order;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "orderqueue")
public class OrderJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "idcustomer")
    private Long idcustomer;
    private String step;
    private LocalDate date;
    private LocalTime time;
    private Integer price;
    private String details;
    private String paymentStatus; // <-- Novo campo adicionado

    public OrderJpaEntity() { }

    // Construtor completo para a reconstituição do objeto a partir do banco de dados
    public OrderJpaEntity(Long id, Long idcustomer, String step, LocalDate date, LocalTime time, Integer price, String details, String paymentStatus) {
        this.id = id;
        this.idcustomer = idcustomer;
        this.step = step;
        this.date = date;
        this.time = time;
        this.price = price;
        this.details = details;
        this.paymentStatus = paymentStatus;
    }

    // Construtor sem o ID, usado para criar uma nova entidade
    public OrderJpaEntity(Long idcustomer, String step, LocalDate date, LocalTime time, Integer price, String details, String paymentStatus) {
        this.idcustomer = idcustomer;
        this.step = step;
        this.date = date;
        this.time = time;
        this.price = price;
        this.details = details;
        this.paymentStatus = paymentStatus;
    }

    // Métodos de conversão (agora incluem o novo campo)
    public Order toDomain() {
        return new Order(this.id, this.idcustomer, this.step, this.date, this.time, this.price, this.details, this.paymentStatus);
    }

    public static OrderJpaEntity fromDomain(Order domain) {
        return new OrderJpaEntity(domain.getId(), domain.getIdcustomer(), domain.getStep(), domain.getDate(), domain.getTime(), domain.getPrice(), domain.getDetails(), domain.getPaymentStatus());
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getIdcustomer() { return idcustomer; }
    public void setIdcustomer(Long idcustomer) { this.idcustomer = idcustomer; }
    public String getStep() { return step; }
    public void setStep(String step) { this.step = step; }
    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }
    public LocalTime getTime() { return time; }
    public void setTime(LocalTime time) { this.time = time; }
    public Integer getPrice() { return price; }
    public void setPrice(Integer price) { this.price = price; }
    public String getDetails() { return details; }
    public void setDetails(String details) { this.details = details; }
    public String getPaymentStatus() { return paymentStatus; } // <-- Novo getter
    public void setPaymentStatus(String paymentStatus) { this.paymentStatus = paymentStatus; } // <-- Novo setter
}