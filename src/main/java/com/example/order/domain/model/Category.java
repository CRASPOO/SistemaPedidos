package com.example.order.domain.model;

import com.example.order.application.shared.CategoryResponseDTO;

public class Category {
    private Long id;
    private String name;

    // Construtor para criação de nova categoria (sem ID inicial)
    public Category(String name) {
        this.name = name;
    }

    // Construtor para reconstruir a categoria do banco (com ID)
    public Category(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    // Método de negócio (se houver lógica específica para categoria, adicione aqui)
    public void updateName(String newName) {
        if (newName == null || newName.trim().isEmpty()) {
            throw new IllegalArgumentException("Category name cannot be empty.");
        }
        this.name = newName;
    }

    // Getters
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    // Setter para ID é comum ao carregar do BD.
    public void setId(Long id) {
        this.id = id;
    }
}