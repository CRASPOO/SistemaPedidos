package com.example.order.order;

import java.time.LocalDate;
import java.time.LocalTime;

public record OrderResponseDTO(Long id, Long idcustomer, Long idstep, LocalDate date, LocalTime time, Integer price, String details){
    public OrderResponseDTO(Order order){
        this(order.getId(), order.getIdcustomer(), order.getIdstep(), order.getDate(),order.getTime(), order.getPrice(), order.getDetails());

    }



}
