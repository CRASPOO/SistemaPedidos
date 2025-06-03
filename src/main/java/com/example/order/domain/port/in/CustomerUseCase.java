package com.example.order.domain.port.in;

import com.example.order.domain.model.Customer;
import java.util.List;
import java.util.Optional;

public interface CustomerUseCase {
    Customer createCustomer(Customer customer);
    Optional<Customer> getCustomerById(Long id);
    Optional<Customer> getCustomerByCpf(Long cpf);
    List<Customer> getAllCustomers();
    Customer updateCustomer(Customer customer);
    // Adicione outros métodos conforme a necessidade do negócio, como deleteCustomer(Long id)
}