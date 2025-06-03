package com.example.order.controller;

import com.example.order.product.Product;
import com.example.order.product.ProductRepository;
import com.example.order.product.ProductRequestDTO;
import com.example.order.product.ProductResponseDTO;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class OrderController {

    @Autowired
    private ProductRepository repository;

    @CrossOrigin(origins = "*", allowedHeaders = "*")

    @GetMapping
    public List<ProductResponseDTO> getAll(){
        List<ProductResponseDTO> productlist = repository.findAll().stream().map(ProductResponseDTO::new).toList();
        return productlist;
    }

    @GetMapping("/active")
    public List<ProductResponseDTO> getAllactive(){
        List<ProductResponseDTO> productlist = repository.findAllByActiveTrue().stream().map(ProductResponseDTO::new).toList();
        return productlist;
    }

    @GetMapping("/category/{id}")
    public List<ProductResponseDTO> getAllcategory(@PathVariable String id){
        List<ProductResponseDTO> productlist = repository.findAllByCategory(Long.valueOf(id)).stream().map(ProductResponseDTO::new).toList();
        return productlist;
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping
    public void saveProduct(@RequestBody ProductRequestDTO data){
        Product productData = new Product(data);
        repository.save(productData);

    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PutMapping
    @Transactional
    public ResponseEntity updateProduct(@RequestBody ProductRequestDTO data){
        Optional<Product> optionalProduct = repository.findById(data.id());
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            product.setName(data.name());
            product.setPrice(data.price());
            product.setCategory(data.category());
            product.setDescription(data.description());
            product.setImage(data.image());
            product.setActive(data.active());
            return ResponseEntity.ok(product);
        } else {
            throw new EntityNotFoundException();
        }
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity updateProduct(@PathVariable String id){
        Optional<Product> optionalProduct = repository.findById(Long.valueOf(id));
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            product.setActive(true);
            return ResponseEntity.ok(product);
        } else {
            throw new EntityNotFoundException();
        }
    }


    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleteProduct(@PathVariable String id){
        Optional<Product> optionalProduct = repository.findById(Long.valueOf(id));
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            product.setActive(false);
            return ResponseEntity.ok(product);
        } else {
            throw new EntityNotFoundException();
        }
    }


}


