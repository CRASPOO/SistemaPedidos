package com.example.order.application.adapter.out.persistence;

import com.example.order.application.adapter.out.persistence.ProductEntity;
import com.example.order.application.adapter.out.persistence.SpringDataProductRepository;
import com.example.order.domain.model.Product;
import com.example.order.domain.port.out.ProductRepositoryPort;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class JpaProductRepositoryAdapter implements ProductRepositoryPort {

    private final SpringDataProductRepository springDataRepository; // Injeta a interface Spring Data JPA

    public JpaProductRepositoryAdapter(SpringDataProductRepository springDataRepository) {
        this.springDataRepository = springDataRepository;
    }

    @Override
    public Product save(Product product) {
        // Mapeia do domínio para a entidade JPA
        ProductEntity productEntity = ProductEntity.fromDomain(product);
        ProductEntity savedEntity = springDataRepository.save(productEntity);
        // Mapeia de volta da entidade JPA para o domínio
        return savedEntity.toDomain();
    }

    @Override
    public Optional<Product> findById(Long id) {
        return springDataRepository.findById(id)
                .map(ProductEntity::toDomain); // Mapeia de entidade JPA para domínio
    }

    @Override
    public List<Product> findAll() {
        return springDataRepository.findAll().stream()
                .map(ProductEntity::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Product> findAllByActiveTrue() {
        return springDataRepository.findAllByActiveTrue().stream()
                .map(ProductEntity::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Product> findAllByCategory(Long categoryId) {
        return springDataRepository.findAllByCategory(categoryId).stream()
                .map(ProductEntity::toDomain)
                .collect(Collectors.toList());
    }
}