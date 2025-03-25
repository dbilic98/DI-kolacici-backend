package com.dikolacici.DI_kolacici.controller;

import com.dikolacici.DI_kolacici.controller.request.RequestCategoryDto;
import com.dikolacici.DI_kolacici.controller.response.PaginatedResponse;
import com.dikolacici.DI_kolacici.controller.response.ResponseCategoryDto;
import com.dikolacici.DI_kolacici.domain.model.Category;
import com.dikolacici.DI_kolacici.domain.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
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
        Page<Category> allCategory = categoryService.getAllCategory(pageNumber, pageSize);
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

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseCategoryDto createCategory(@Valid @RequestBody RequestCategoryDto requestCategoryDto) {
        Category createdCategory = categoryService.createCategory(requestCategoryDto);
        return toResponseDto(createdCategory);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseCategoryDto updateCategory (@PathVariable("id") Long id, @Valid @RequestBody RequestCategoryDto requestCategoryDto) {
        Category updatedCategory = categoryService.updateCategory(id, requestCategoryDto);
        return toResponseDto(updatedCategory);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCategory(@PathVariable("id") Long id) {
        categoryService.deleteCategory(id);
    }
}
