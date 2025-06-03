package com.example.order.product;

import jakarta.persistence.*;
import lombok.*;


@Table(name = "product")
@Entity(name = "product")
@Getter
@Setter
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
    private String image;
    private Boolean active;

    public Product(ProductRequestDTO data){
        this.name = data.name();
        this.price = data.price();
        this.category = data.category();
        this.description = data.description();
        this.image = data.image();
        this.active = data.active();

    }
}