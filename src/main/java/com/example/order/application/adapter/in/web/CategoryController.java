package com.example.order.application.adapter.in.web;

import com.example.order.domain.model.Category;
import com.example.order.domain.port.in.CategoryUseCase;
import com.example.order.application.shared.CategoryResponseDTO;
import com.example.order.shared.exceptions.CategoryNotFoundException;
//import com.example.order.shared.CategoryRequestDTO; // Se você tivesse operações de POST/PUT

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(description = "Retorna lista de todas as categorias")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna lista de categorias"),
            @ApiResponse(responseCode = "400", description = "Não existem categorias")
    })

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public List<CategoryResponseDTO> getAll(){
        List<Category> categories = categoryUseCase.getAllCategories();
        return categories.stream()
                .map(CategoryResponseDTO::fromDomain)
                .collect(Collectors.toList());
    }


}