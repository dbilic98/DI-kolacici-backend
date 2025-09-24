package com.dikolacici.DI_kolacici.controller;

import com.dikolacici.DI_kolacici.controller.response.PaginatedResponse;
import com.dikolacici.DI_kolacici.controller.response.ResponseCategoryDto;
import com.dikolacici.DI_kolacici.domain.model.Category;
import com.dikolacici.DI_kolacici.domain.service.CategoryService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public PaginatedResponse<ResponseCategoryDto> getCategory(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "10") int pageSize) {
        Page<Category> allCategory = categoryService.getAllCategories(pageNumber, pageSize);
        Page<ResponseCategoryDto> map = allCategory.map(this::toResponseDto);
        return new PaginatedResponse<>(map);
    }

    private ResponseCategoryDto toResponseDto(Category category) {
        return new ResponseCategoryDto(
                category.getId(),
                category.getCategoryName()
        );
    }

    @GetMapping("/{id}")
    public ResponseCategoryDto getCategoryById(@PathVariable("id") Long id) {
        Category categoryById = categoryService.getCategoryById(id);
        return toResponseDto(categoryById);
    }
}
