package com.example.order.product;

public record ProductRequestDTO(
        Long id, String name, Integer price, Integer category, String description, String image, Boolean active

) {
}