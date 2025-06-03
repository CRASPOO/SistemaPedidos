package com.example.order.category;

import com.example.order.category.CategoryRequestDTO;
import jakarta.persistence.*;
import lombok.*;


@Table(name = "category")
@Entity(name = "category")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")

public class Category {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;


    public Category(CategoryRequestDTO data){
        this.name = data.name();


    }


}
