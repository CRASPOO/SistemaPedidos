package com.example.order.domain.service;

import com.example.order.domain.model.Customer;
import com.example.order.domain.port.in.CustomerUseCase;
import com.example.order.domain.port.out.CustomerRepositoryPort;
import com.example.order.shared.exceptions.CustomerNotFoundException;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class CustomerService implements CustomerUseCase {

    private final CustomerRepositoryPort customerRepositoryPort; // Injeta a porta de saída

    public CustomerService(CustomerRepositoryPort customerRepositoryPort) {
        this.customerRepositoryPort = customerRepositoryPort;
    }

    @Override
    public Customer createCustomer(Customer customer) {
        // Exemplo de regra de negócio: CPF deve ser único
        if (customer.getCpf() != null && customerRepositoryPort.findByCpf(customer.getCpf()).isPresent()) {
            throw new IllegalArgumentException("Customer with this CPF already exists.");
        }
        return customerRepositoryPort.save(customer);
    }

    @Override
    public Optional<Customer> getCustomerById(Long id) {
        return customerRepositoryPort.findById(id);
    }

    @Override
    public Optional<Customer> getCustomerByCpf(Long cpf) {
        return customerRepositoryPort.findByCpf(cpf);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepositoryPort.findAll();
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        // Busca o cliente existente para garantir que não está criando um novo ou atualizando um inexistente
        Customer existingCustomer = customerRepositoryPort.findById(customer.getId())
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found with ID: " + customer.getId()));

        // Chama o método de negócio da entidade de domínio para atualizar
        existingCustomer.updateDetails(customer.getName(), customer.getEmail());

        // Se o CPF for atualizado, precisa verificar unicidade novamente
        if (!Objects.equals(existingCustomer.getCpf(), customer.getCpf())) {
            if (customer.getCpf() != null && customerRepositoryPort.findByCpf(customer.getCpf()).isPresent()) {
                throw new IllegalArgumentException("Cannot update CPF: a customer with this CPF already exists.");
            }
        }
        // O CPF não é atualizado via updateDetails, mantendo-o imutável ou exigindo um método específico.
        // Se CPF puder mudar, adicione existingCustomer.setCpf(customer.getCpf()); após a verificação de unicidade.

        return customerRepositoryPort.save(existingCustomer);
    }
}