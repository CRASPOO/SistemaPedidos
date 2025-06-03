package com.example.order.domain.port.in;

import com.example.order.domain.model.Product;
import java.util.List;
import java.util.Optional;

public interface ProductUseCase {
    Product createProduct(Product product);
    Optional<Product> getProductById(Long id);
    List<Product> getAllProducts();
    List<Product> getAllActiveProducts();
    List<Product> getAllProductsByCategory(Long categoryId);
    Product updateProduct(Product product); // Aceita o Product de domínio
    void activateProduct(Long id);
    void deactivateProduct(Long id);
}
