package com.example.order.controller;

import com.example.order.product.Product;
import com.example.order.product.ProductRepository;
import com.example.order.product.ProductRequestDTO;
import com.example.order.product.ProductResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("product")
public class OrderController {

    @Autowired
    private ProductRepository repository;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/hello")
    public String imprimir(){

        return "HELLO";
    }
    @GetMapping
    public List<ProductResponseDTO> getAll(){
        List<ProductResponseDTO> productlist = repository.findAll().stream().map(ProductResponseDTO::new).toList();
        return productlist;
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping
    public void saveProduct(@RequestBody ProductRequestDTO data){
        Product productData = new Product(data);
        repository.save(productData);

    }
}


