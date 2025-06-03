package com.example.order.application.shared;

// Usando records do Java 14+ para DTOs imutáveis e concisos
public record CustomerRequestDTO(
        Long id,
        String name,
        String email,
        Long cpf
) { }