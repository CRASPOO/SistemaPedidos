package com.example.order.customer;

import com.example.order.product.ProductRequestDTO;
import jakarta.persistence.*;
import lombok.*;


@Table(name = "customer")
@Entity(name = "customer")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")

public class Customer {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private Long cpf;


    public Customer(CustomerRequestDTO data){
        this.name = data.name();
        this.email = data.email();
        this.cpf = data.cpf();


    }


}
