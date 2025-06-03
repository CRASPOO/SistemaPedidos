package com.example.order.application.adapter.in.web;


import com.example.order.domain.model.Customer;
import com.example.order.domain.port.in.CustomerUseCase;
import com.example.order.application.shared.CustomerRequestDTO;
import com.example.order.application.shared.CustomerResponseDTO;
import com.example.order.shared.exceptions.CustomerNotFoundException;

import jakarta.transaction.Transactional; // Manter aqui se usar transações controladas pelo Spring MVC para o endpoint
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerUseCase customerUseCase; // Injeta a porta de entrada do domínio

    public CustomerController(CustomerUseCase customerUseCase) {
        this.customerUseCase = customerUseCase;
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public List<CustomerResponseDTO> getAll(){
        List<Customer> customers = customerUseCase.getAllCustomers();
        return customers.stream().map(CustomerResponseDTO::fromDomain).collect(Collectors.toList());
    }

    @GetMapping("/cpf/{cpf}") // Endpoint mais claro para buscar por CPF
    public ResponseEntity<CustomerResponseDTO> getByCpf(@PathVariable Long cpf){
        try {
            Customer customer = customerUseCase.getCustomerByCpf(cpf)
                    .orElseThrow(() -> new CustomerNotFoundException("Customer not found with CPF: " + cpf));
            return ResponseEntity.ok(CustomerResponseDTO.fromDomain(customer));
        } catch (CustomerNotFoundException e) {
            return ResponseEntity.notFound().build(); // Retorna 404 Not Found
        }
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // Retorna 201 Created
    public CustomerResponseDTO saveCustomer(@RequestBody CustomerRequestDTO data){
        // Mapeia DTO de requisição para objeto de domínio
        Customer newCustomer = new Customer(data.name(), data.email(), data.cpf());
        try {
            Customer createdCustomer = customerUseCase.createCustomer(newCustomer);
            return CustomerResponseDTO.fromDomain(createdCustomer);
        } catch (IllegalArgumentException e) {
            // Captura exceções de validação de negócio e as traduz em um status HTTP apropriado
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage()); // Ex: 409 Conflict para CPF duplicado
        }
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PutMapping
    @Transactional // A transação pode ser gerenciada aqui ou no serviço de domínio
    public ResponseEntity<CustomerResponseDTO> updateCustomer(@RequestBody CustomerRequestDTO data){
        if (data.id() == null) {
            return ResponseEntity.badRequest().build(); // ID é obrigatório para atualização
        }
        // Mapeia DTO de requisição para objeto de domínio
        Customer customerToUpdate = new Customer(data.id(), data.name(), data.email(), data.cpf());
        try {
            Customer updatedCustomer = customerUseCase.updateCustomer(customerToUpdate);
            return ResponseEntity.ok(CustomerResponseDTO.fromDomain(updatedCustomer));
        } catch (CustomerNotFoundException e) {
            return ResponseEntity.notFound().build(); // Retorna 404 Not Found
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage()); // Ex: 400 Bad Request para validações
        }
    }
}