package com.example.order.application.shared;

import com.example.order.domain.model.Product; // Importa a entidade de domínio

public record ProductResponseDTO(
        Long id,
        String name,
        Integer price,
        Long category,
        String description,
        String image,
        boolean active
) {
    // Método estático para mapear de entidade de domínio para DTO
    public static ProductResponseDTO fromDomain(Product product) {
        return new ProductResponseDTO(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getCategory(),
                product.getDescription(),
                product.getImage(),
                product.isActive()
        );
    }
}