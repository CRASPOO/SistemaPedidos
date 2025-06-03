package com.example.order.application.shared;
// Usando records do Java 14+ para DTOs imutáveis e concisos
public record ProductRequestDTO(
        Long id,
        String name,
        Integer price,
        Long category,
        String description,
        String image,
        Boolean active // Usar Boolean para permitir null na requisição de criação
) { }