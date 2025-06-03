package com.example.order.application.adapter.in.web;

import com.example.order.domain.model.Category;
import com.example.order.domain.port.in.CategoryUseCase;
import com.example.order.application.shared.CategoryResponseDTO;
import com.example.order.shared.exceptions.CategoryNotFoundException;
//import com.example.order.shared.CategoryRequestDTO; // Se você tivesse operações de POST/PUT

import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/category")
public class CategoryController {

    private final CategoryUseCase categoryUseCase; // Injeta a porta de entrada do domínio

    public CategoryController(CategoryUseCase categoryUseCase) {
        this.categoryUseCase = categoryUseCase;
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public List<CategoryResponseDTO> getAll(){
        List<Category> categories = categoryUseCase.getAllCategories();
        return categories.stream()
                .map(CategoryResponseDTO::fromDomain)
                .collect(Collectors.toList());
    }

    // Exemplo de como você adicionaria um POST (se a necessidade surgir)
    /*
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryResponseDTO createCategory(@RequestBody CategoryRequestDTO request) {
        Category newCategory = new Category(request.name());
        Category createdCategory = categoryUseCase.createCategory(newCategory);
        return CategoryResponseDTO.fromDomain(createdCategory);
    }
    */

    // Exemplo de como você adicionaria um GET por ID (se a necessidade surgir)
    /*
    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponseDTO> getCategoryById(@PathVariable Long id) {
        try {
            Category category = categoryUseCase.getCategoryById(id)
                    .orElseThrow(() -> new CategoryNotFoundException("Category not found with ID: " + id));
            return ResponseEntity.ok(CategoryResponseDTO.fromDomain(category));
        } catch (CategoryNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
    */
}