package com.example.order.domain.port.out;

import com.example.order.domain.model.Customer;
import java.util.List;
import java.util.Optional;

public interface CustomerRepositoryPort {
    Customer save(Customer customer);
    Optional<Customer> findById(Long id);
    Optional<Customer> findByCpf(Long cpf);
    List<Customer> findAll();
    // void deleteById(Long id); // Se houver a necessidade de exclusão real
}