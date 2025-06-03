package com.example.order.product;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Table(name = "product")
@Entity(name = "product")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Product {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer price;
    private Integer category;
    private String description;
    private String Image;
    private Boolean active;

    public Product(ProductRequestDTO data){
        this.name = data.name();
        this.price = data.price();
        this.category = data.category();
        this.description = data.description();
        this.Image = data.Image();
        this.active = data.active();

    }
}