package com.example.order.application.adapter.out.persistence;

import com.example.order.domain.model.Customer;
import com.example.order.domain.port.out.CustomerRepositoryPort;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class JpaCustomerRepositoryAdapter implements CustomerRepositoryPort {

    private final SpringDataCustomerRepository springDataRepository; // Injeta a interface Spring Data JPA

    public JpaCustomerRepositoryAdapter(SpringDataCustomerRepository springDataRepository) {
        this.springDataRepository = springDataRepository;
    }

    @Override
    public Customer save(Customer customer) {
        // Mapeia do domínio para a entidade JPA
        CustomerEntity customerEntity = CustomerEntity.fromDomain(customer);
        CustomerEntity savedEntity = springDataRepository.save(customerEntity);
        // Mapeia de volta da entidade JPA para o domínio
        return savedEntity.toDomain();
    }

    @Override
    public Optional<Customer> findById(Long id) {
        return springDataRepository.findById(id)
                .map(CustomerEntity::toDomain); // Mapeia de entidade JPA para domínio
    }

    @Override
    public Optional<Customer> findByCpf(Long cpf) {
        return springDataRepository.findByCpf(cpf)
                .map(CustomerEntity::toDomain);
    }

    @Override
    public List<Customer> findAll() {
        return springDataRepository.findAll().stream()
                .map(CustomerEntity::toDomain)
                .collect(Collectors.toList());
    }
}