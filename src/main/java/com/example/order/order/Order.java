package com.example.order.order;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Table(name = "orderqueue")
@Entity(name = "orderqueue")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")

public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long idcustomer;
    private Long idstep;
    private LocalDate date;
    private LocalTime time;
    private Integer price;
    private String details;

    public Order(OrderRequestDTO data){
        this.idcustomer = data.idcustomer();
        this.idstep = data.idstep();
        this.date = data.date();
        this.time = data.time();
        this.price = data.price();
        this.details = data.details();

    }
}
