package com.example.order.domain.port.out;

import com.example.order.domain.model.Category;
import java.util.List;
import java.util.Optional;

public interface CategoryRepositoryPort {
    List<Category> findAll();
    Optional<Category> findById(Long id);
    Category save(Category category);
    void deleteById(Long id); // Para exclusão "física", se houver
}
