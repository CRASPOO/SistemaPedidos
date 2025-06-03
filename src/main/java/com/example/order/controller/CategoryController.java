package com.example.order.controller;


import com.example.order.category.CategoryRepository;

import com.example.order.category.CategoryResponseDTO;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/category")



public class CategoryController {

    @Autowired
    private CategoryRepository repository;

    @CrossOrigin(origins = "*", allowedHeaders = "*")

    @GetMapping
    public List<CategoryResponseDTO> getAll(){


        List<CategoryResponseDTO> categorylist = repository.findAll().stream().map(CategoryResponseDTO::new).toList();
        return categorylist;
    }

}
