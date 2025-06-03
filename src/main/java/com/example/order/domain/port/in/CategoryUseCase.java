package com.example.order.domain.port.in;

import com.example.order.domain.model.Category;
import java.util.List;
import java.util.Optional;

public interface CategoryUseCase {
    List<Category> getAllCategories();
    Optional<Category> getCategoryById(Long id); // Adicionado para completude, embora não esteja no controller original
    Category createCategory(Category category); // Adicionado para completude
    Category updateCategory(Category category); // Adicionado para completude
    void deleteCategory(Long id); // Adicionado para completude
}