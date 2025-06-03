package com.example.order.controller;



import com.example.order.customer.Customer;
import com.example.order.customer.CustomerRepository;
import com.example.order.customer.CustomerRequestDTO;
import com.example.order.customer.CustomerResponseDTO;

import com.example.order.product.Product;
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
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private CustomerRepository repository;

    @CrossOrigin(origins = "*", allowedHeaders = "*")

    @GetMapping
    public List<CustomerResponseDTO> getAll(){
        List<CustomerResponseDTO> customerlist = repository.findAll().stream().map(CustomerResponseDTO::new).toList();
        return customerlist;
    }

    @GetMapping("/cpf/{id}")
    public  ResponseEntity getcpf(@PathVariable String id){
        Optional<Customer> optionalCustomer = repository.findByCpf(Long.valueOf(id));
        if (optionalCustomer.isPresent()) {
            Customer customer = optionalCustomer.get();
            return ResponseEntity.ok(customer);
        } else {
            throw new EntityNotFoundException();
        }

    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping
    public void saveProduct(@RequestBody CustomerRequestDTO data){
        Customer customerData = new Customer(data);
        repository.save(customerData);

    }
}
