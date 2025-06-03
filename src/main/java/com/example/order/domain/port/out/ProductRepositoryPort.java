package com.example.order.domain.port.out;

import com.example.order.domain.model.Product;
import java.util.List;
import java.util.Optional;

public interface ProductRepositoryPort {
    Product save(Product product);
    Optional<Product> findById(Long id);
    List<Product> findAll();
    List<Product> findAllByActiveTrue();
    List<Product> findAllByCategory(Long categoryId);
    // Não temos um delete "físico", mas sim uma desativação.
    // Se tivesse, seria um método aqui.
}