// src/main/java/com/example/order/domain/model/Order.java
package com.example.order.domain.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Order {
    private Long id;
    private Long idcustomer;
    private String step;
    private LocalDate date;
    private LocalTime time;
    private Integer price;
    private String details;

    // Construtor completo para reconstrução de dados do repositório
    public Order(Long id, Long idcustomer, String step, LocalDate date, LocalTime time, Integer price, String details) {
        this.id = id;
        this.idcustomer = idcustomer;
        this.step = step;
        this.date = date;
        this.time = time;
        this.price = price;
        this.details = details;
    }

    // Construtor para criação de novas ordens (ID será gerado)
    public Order(Long idcustomer, String step, LocalDate date, LocalTime time, Integer price, String details) {
        this(null, idcustomer, step, date, time, price, details);
    }

    // Getters
    public Long getId() { return id; }
    public Long getIdcustomer() { return idcustomer; }
    public String getStep() { return step; }
    public LocalDate getDate() { return date; }
    public LocalTime getTime() { return time; }
    public Integer getPrice() { return price; }
    public String getDetails() { return details; }

    // Setters (necessários para modificações e JPA)
    public void setId(Long id) { this.id = id; }
    public void setStep(String step) { this.step = step; }
    // Outros setters podem ser adicionados se o domínio permitir modificações diretas

    // --- Métodos de negócio ---
    public void advanceStatus() {
        if ("Recebido".equalsIgnoreCase(this.step)) {
            this.setStep("Preparacao");
        } else if ("Preparacao".equalsIgnoreCase(this.step)) {
            this.setStep("Pronto");
        } else if ("Pronto".equalsIgnoreCase(this.step)) {
            this.setStep("Finalizado");
        } else {
            throw new IllegalArgumentException("Status atual '" + this.step + "' não permite transição ou já está finalizado.");
        }
    }

    public void cancel() {
        // Você pode adicionar validação aqui, por exemplo, não permitir cancelar se já "Finalizado"
        this.setStep("Cancelado");
    }
}