package com.example.order.application.shared;

import com.example.order.domain.model.Category;

// Supondo que CategoryResponseDTO precise de um id e um name
public record CategoryResponseDTO(
        Long id,
        String name
) {
    public static CategoryResponseDTO fromDomain(Category category) {
        return new CategoryResponseDTO(category.getId(), category.getName());
    }
}