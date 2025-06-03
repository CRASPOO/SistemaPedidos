package com.example.order.domain.model;

import com.example.order.application.shared.ProductRequestDTO; // Ainda aceita DTO para simplicidade na criação

import java.util.Objects;
import java.util.Optional;

public class Product {
    private Long id;
    private String name;
    private Integer price;
    private Long category;
    private String description;
    private String image;
    private boolean active;

    // Construtor para criação de um novo produto (sem ID inicial)
    public Product(String name, Integer price, Long category, String description, String image) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.description = description;
        this.image = image;
        this.active = true; // Por padrão, ativo ao criar
    }

    // Construtor para reconstruir o produto do banco (com ID)
    public Product(Long id, String name, Integer price, Long category, String description, String image, boolean active) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
        this.description = description;
        this.image = image;
        this.active = active;
    }

    // Construtor para simplificar a criação a partir de um DTO de requisição (pode ser substituído por um mapper)
    public Product(ProductRequestDTO data) {
        this.id = data.id();
        this.name = data.name();
        this.price = data.price();
        this.category = data.category();
        this.description = data.description();
        this.image = data.image();
        this.active = data.active();
    }


    // Métodos de negócio (lógica que pertence à entidade)
    public void activate() {
        this.active = true;
    }

    public void deactivate() {
        this.active = false;
    }

    public void updateDetails(String name, Integer price, Long category, String description, String image, Boolean active) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.description = description;
        this.image = image;
        // Permite que o 'active' seja opcionalmente atualizado aqui ou via activate/deactivate
        Optional.ofNullable(active).ifPresent(a -> this.active = a);
    }

    // Getters
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getPrice() {
        return price;
    }

    public Long getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }

    public boolean isActive() {
        return active;
    }

    // Setters (apenas se realmente necessários e com cuidado, preferindo métodos de negócio)
    public void setId(Long id) { // Setters para ID são comuns ao carregar do BD
        this.id = id;
    }

    // Para fins de demonstração, outros setters foram removidos para incentivar métodos de negócio
    // Se precisar de setters para outras propriedades, adicione-os, mas sempre que possível, encapsule a lógica.
}