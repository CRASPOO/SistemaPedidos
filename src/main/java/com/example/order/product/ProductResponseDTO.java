package com.example.order.product;

public record ProductResponseDTO(Long id, String name, Integer price, Integer category, String description, String Image, Boolean active ) {

    public ProductResponseDTO(Product product){
        this(product.getId(), product.getName(), product.getPrice(), product.getCategory(), product.getDescription(), product.getImage(), product.getActive());

    }


}