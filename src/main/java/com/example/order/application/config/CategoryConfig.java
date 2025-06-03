package com.example.order.application.config;

import com.example.order.application.adapter.out.persistence.JpaCategoryRepositoryAdapter;
import com.example.order.application.adapter.out.persistence.SpringDataCategoryRepository;
import com.example.order.domain.port.in.CategoryUseCase;
import com.example.order.domain.port.out.CategoryRepositoryPort;
import com.example.order.domain.service.CategoryService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CategoryConfig {

    @Bean
    public CategoryRepositoryPort categoryRepositoryPort(SpringDataCategoryRepository springDataCategoryRepository) {
        return new JpaCategoryRepositoryAdapter(springDataCategoryRepository);
    }

    @Bean
    public CategoryUseCase categoryUseCase(CategoryRepositoryPort categoryRepositoryPort) {
        return new CategoryService(categoryRepositoryPort);
    }
}