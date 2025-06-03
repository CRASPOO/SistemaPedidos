package com.example.order.controller;


import com.example.order.order.OrderRepository;
import com.example.order.order.OrderResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")

public class OrderControllet {

    @Autowired
    private OrderRepository repository;

    @CrossOrigin(origins = "*", allowedHeaders = "*")

    @GetMapping
    public List<OrderResponseDTO> getAll(){
        List<OrderResponseDTO> Orderlist = repository.findAll().stream().map(OrderResponseDTO::new).toList();
        return Orderlist;
    }


}
