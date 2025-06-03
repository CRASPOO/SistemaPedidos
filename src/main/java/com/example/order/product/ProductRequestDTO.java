package com.example.order.product;

public record ProductRequestDTO(String name, Integer price, Integer category, String description, String Image, Boolean active) {
}