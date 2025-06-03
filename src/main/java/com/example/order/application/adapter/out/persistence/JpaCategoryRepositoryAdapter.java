package com.example.order.application.adapter.out.persistence;

import com.example.order.domain.model.Category;
import com.example.order.domain.port.out.CategoryRepositoryPort;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class JpaCategoryRepositoryAdapter implements CategoryRepositoryPort {

    private final SpringDataCategoryRepository springDataRepository;

    public JpaCategoryRepositoryAdapter(SpringDataCategoryRepository springDataRepository) {
        this.springDataRepository = springDataRepository;
    }

    @Override
    public List<Category> findAll() {
        return springDataRepository.findAll().stream()
                .map(CategoryEntity::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Category> findById(Long id) {
        return springDataRepository.findById(id)
                .map(CategoryEntity::toDomain);
    }

    @Override
    public Category save(Category category) {
        CategoryEntity categoryEntity = CategoryEntity.fromDomain(category);
        CategoryEntity savedEntity = springDataRepository.save(categoryEntity);
        return savedEntity.toDomain();
    }

    @Override
    public void deleteById(Long id) {
        springDataRepository.deleteById(id);
    }
}