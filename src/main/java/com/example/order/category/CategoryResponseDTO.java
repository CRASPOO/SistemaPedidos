package com.example.order.category;



public record CategoryResponseDTO(Long id, String name) {
    public CategoryResponseDTO(Category category){
        this(category.getId(), category.getName());

    }
}
