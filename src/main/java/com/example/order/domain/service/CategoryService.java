package com.example.order.domain.service;

import com.example.order.domain.model.Category;
import com.example.order.domain.port.in.CategoryUseCase;
import com.example.order.domain.port.out.CategoryRepositoryPort;
import com.example.order.shared.exceptions.CategoryNotFoundException; // Precisamos de uma exceção específica

import java.util.List;
import java.util.Optional;

public class CategoryService implements CategoryUseCase {

    private final CategoryRepositoryPort categoryRepositoryPort;

    public CategoryService(CategoryRepositoryPort categoryRepositoryPort) {
        this.categoryRepositoryPort = categoryRepositoryPort;
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepositoryPort.findAll();
    }

    @Override
    public Optional<Category> getCategoryById(Long id) {
        return categoryRepositoryPort.findById(id);
    }

    @Override
    public Category createCategory(Category category) {
        // Exemplo de regra de negócio: categoria não pode ter nome duplicado (simplificado)
        // Isso exigiria um método findByName na porta
        return categoryRepositoryPort.save(category);
    }

    @Override
    public Category updateCategory(Category category) {
        Category existingCategory = categoryRepositoryPort.findById(category.getId())
                .orElseThrow(() -> new CategoryNotFoundException("Category not found with ID: " + category.getId()));

        existingCategory.updateName(category.getName()); // Usa o método de negócio da entidade

        return categoryRepositoryPort.save(existingCategory);
    }

    @Override
    public void deleteCategory(Long id) {
        if (!categoryRepositoryPort.findById(id).isPresent()) {
            throw new CategoryNotFoundException("Category not found with ID: " + id);
        }
        categoryRepositoryPort.deleteById(id);
    }
}