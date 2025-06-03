package com.example.order.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("connection")
public class ConnectionController {

    @Autowired

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public String imprimir(){

        return "ATIVO";
    }

}


