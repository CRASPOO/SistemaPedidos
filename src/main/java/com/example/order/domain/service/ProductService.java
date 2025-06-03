package com.example.order.domain.service;

import com.example.order.domain.model.Product;
import com.example.order.domain.port.in.ProductUseCase;
import com.example.order.domain.port.out.ProductRepositoryPort;
import com.example.order.shared.exceptions.ProductNotFoundException; // Usando a exceção compartilhada

import java.util.List;
import java.util.Optional;

public class ProductService implements ProductUseCase {

    private final ProductRepositoryPort productRepositoryPort; // Injeta a porta de saída

    public ProductService(ProductRepositoryPort productRepositoryPort) {
        this.productRepositoryPort = productRepositoryPort;
    }

    @Override
    public Product createProduct(Product product) {
        // Regras de negócio para criação podem ir aqui
        return productRepositoryPort.save(product);
    }

    @Override
    public Optional<Product> getProductById(Long id) {
        return productRepositoryPort.findById(id);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepositoryPort.findAll();
    }

    @Override
    public List<Product> getAllActiveProducts() {
        return productRepositoryPort.findAllByActiveTrue();
    }

    @Override
    public List<Product> getAllProductsByCategory(Long categoryId) {
        return productRepositoryPort.findAllByCategory(categoryId);
    }

    @Override
    public Product updateProduct(Product product) {
        Product existingProduct = productRepositoryPort.findById(product.getId())
                .orElseThrow(() -> new ProductNotFoundException("Product not found with ID: " + product.getId()));

        existingProduct.updateDetails(
                product.getName(),
                product.getPrice(),
                product.getCategory(),
                product.getDescription(),
                product.getImage(),
                product.isActive() // Permite atualizar o status ativo
        );

        return productRepositoryPort.save(existingProduct);
    }

    @Override
    public void activateProduct(Long id) {
        Product product = productRepositoryPort.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with ID: " + id));
        product.activate();
        productRepositoryPort.save(product);
    }

    @Override
    public void deactivateProduct(Long id) {
        Product product = productRepositoryPort.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with ID: " + id));
        product.deactivate();
        productRepositoryPort.save(product);
    }
}
