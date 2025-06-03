package com.example.order.application.adapter.out.persistence;

import com.example.order.domain.model.Product; // Importa a entidade de domínio

import jakarta.persistence.*;
import java.time.LocalDate; // Exemplo, se tivesse campo de data

@Entity
@Table(name = "product") // Nome da tabela no banco
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer price;
    private Long category;
    private String description;
    private String image;
    private boolean active;

    // Construtor padrão exigido pelo JPA
    public ProductEntity() {
    }

    // Construtor para mapeamento do domínio para a entidade JPA
    public ProductEntity(Long id, String name, Integer price, Long category, String description, String image, boolean active) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
        this.description = description;
        this.image = image;
        this.active = active;
    }

    // Método para mapear de entidade de domínio para entidade JPA
    public static ProductEntity fromDomain(Product domainProduct) {
        return new ProductEntity(
                domainProduct.getId(),
                domainProduct.getName(),
                domainProduct.getPrice(),
                domainProduct.getCategory(),
                domainProduct.getDescription(),
                domainProduct.getImage(),
                domainProduct.isActive()
        );
    }

    // Método para mapear de entidade JPA para entidade de domínio
    public Product toDomain() {
        return new Product(
                this.id,
                this.name,
                this.price,
                this.category,
                this.description,
                this.image,
                this.active
        );
    }

    // Getters e Setters (necessários para JPA)
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Integer getPrice() { return price; }
    public void setPrice(Integer price) { this.price = price; }
    public Long getCategory() { return category; }
    public void setCategory(Long category) { this.category = category; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getImage() { return image; }
    public void setImage(String image) { this.image = image; }
    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }
}