package com.example.order.application.adapter.in.web;

import com.example.order.domain.model.Product;
import com.example.order.domain.port.in.ProductUseCase;
import com.example.order.application.shared.ProductRequestDTO;
import com.example.order.application.shared.ProductResponseDTO;
import com.example.order.shared.exceptions.ProductNotFoundException; // Usando a exceção de domínio

import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductUseCase productUseCase; // Injeta a porta de entrada do domínio

    public ProductController(ProductUseCase productUseCase) {
        this.productUseCase = productUseCase;
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public List<ProductResponseDTO> getAll() {
        List<Product> products = productUseCase.getAllProducts();
        return products.stream().map(ProductResponseDTO::fromDomain).collect(Collectors.toList());
    }

    @GetMapping("/active")
    public List<ProductResponseDTO> getAllActive() {
        List<Product> products = productUseCase.getAllActiveProducts();
        return products.stream().map(ProductResponseDTO::fromDomain).collect(Collectors.toList());
    }

    @GetMapping("/category/{id}")
    public List<ProductResponseDTO> getAllCategory(@PathVariable Long id) { // Mudado para Long
        List<Product> products = productUseCase.getAllProductsByCategory(id);
        return products.stream().map(ProductResponseDTO::fromDomain).collect(Collectors.toList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // Retorna 201 Created
    public ProductResponseDTO saveProduct(@RequestBody ProductRequestDTO data) {
        // Mapeia DTO para o objeto de domínio
        Product newProduct = new Product(
                null, // ID nulo para criação
                data.name(),
                data.price(),
                data.category(),
                data.description(),
                data.image(),
                data.active() != null ? data.active() : true // Garante um valor padrão se não informado
        );
        Product createdProduct = productUseCase.createProduct(newProduct);
        return ProductResponseDTO.fromDomain(createdProduct);
    }

    @PutMapping
    @Transactional // A transação é controlada na camada de serviço ou via Spring Data JPA
    public ResponseEntity<ProductResponseDTO> updateProduct(@RequestBody ProductRequestDTO data) {
        if (data.id() == null) {
            return ResponseEntity.badRequest().build(); // ID é obrigatório para atualização
        }
        Product productToUpdate = new Product(data.id(), data.name(), data.price(), data.category(), data.description(), data.image(), data.active());
        try {
            Product updatedProduct = productUseCase.updateProduct(productToUpdate);
            return ResponseEntity.ok(ProductResponseDTO.fromDomain(updatedProduct));
        } catch (ProductNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}/activate") // endpoint mais claro para ativar
    @Transactional
    public ResponseEntity<Void> activateProduct(@PathVariable Long id) {
        try {
            productUseCase.activateProduct(id);
            return ResponseEntity.ok().build();
        } catch (ProductNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}") // Semanticamente mais correto para desativação ou exclusão lógica
    @Transactional
    public ResponseEntity<Void> deactivateProduct(@PathVariable Long id) { // Renomeado para refletir a ação de desativação
        try {
            productUseCase.deactivateProduct(id);
            return ResponseEntity.ok().build();
        } catch (ProductNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}