package com.example.order.application.adapter.out.persistence;

import com.example.order.domain.model.Category;
import jakarta.persistence.*;

@Entity
@Table(name = "category")
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    public CategoryEntity() {
    }

    public CategoryEntity(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    // Mapeamento de Domínio para JPA
    public static CategoryEntity fromDomain(Category domainCategory) {
        return new CategoryEntity(domainCategory.getId(), domainCategory.getName());
    }

    // Mapeamento de JPA para Domínio
    public Category toDomain() {
        return new Category(this.id, this.name);
    }

    // Getters e Setters (necessários para JPA)
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}