package com.example.order.order;

import java.time.LocalDate;
import java.time.LocalTime;

public record OrderRequestDTO(Long id, Long idcustomer, Long idstep, LocalDate date, LocalTime time, Integer price, String details){
}
