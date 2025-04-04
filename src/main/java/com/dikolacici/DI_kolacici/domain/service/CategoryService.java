package com.dikolacici.DI_kolacici.domain.service;

import com.dikolacici.DI_kolacici.controller.request.RequestCategoryDto;
import com.dikolacici.DI_kolacici.domain.model.Category;
import com.dikolacici.DI_kolacici.domain.repository.CategoryRepository;
import com.dikolacici.DI_kolacici.exception.CategoryNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Page<Category> getAllCategories(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return categoryRepository.findAll(pageable);
    }

    public Category getCategoryById(Long id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isEmpty()) {
            throw new CategoryNotFoundException("Category with ID " + id + " is not found.");
        }
        return optionalCategory.get();
    }

    public Category createCategory(RequestCategoryDto requestCategoryDto) {
        Category createdCategory = new Category(requestCategoryDto.categoryName());
        return categoryRepository.save(createdCategory);
    }

    public Category updateCategory(Long id, RequestCategoryDto requestCategoryDto) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isEmpty()) {
            throw new CategoryNotFoundException("Category with ID " + id + " is not found.");
        }
        Category updatedCategory = optionalCategory.get();
        updatedCategory.setCategoryName(requestCategoryDto.categoryName());
        return categoryRepository.save(updatedCategory);
    }

    public void deleteCategory(Long id) {
        if(categoryRepository.existsById(id)) {
            categoryRepository.deleteById(id);
        } else
            throw new CategoryNotFoundException("Category with ID " + id + " is not found.");
    }
}