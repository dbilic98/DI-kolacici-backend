package com.dikolacici.DI_kolacici.domain.service;

import com.dikolacici.DI_kolacici.controller.request.RequestProductDto;
import com.dikolacici.DI_kolacici.domain.model.Category;
import com.dikolacici.DI_kolacici.domain.model.Product;
import com.dikolacici.DI_kolacici.domain.repository.CategoryRepository;
import com.dikolacici.DI_kolacici.domain.repository.ProductRepository;
import com.dikolacici.DI_kolacici.exception.CategoryNotFoundException;
import com.dikolacici.DI_kolacici.exception.ProductNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    private final CategoryRepository categoryRepository;

    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;}

    public Page<Product> getAllProducts(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return productRepository.findAll(pageable);
    }

    public Product getProductById(Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if(optionalProduct.isEmpty()) {
            throw  new ProductNotFoundException("Product with ID " + id + " is not found.");
        }
        return optionalProduct.get();
    }

    public Product createProduct(RequestProductDto requestProductDto) {
        Optional<Category> optionalCategory = categoryRepository.findById(requestProductDto.categoryId());
        if(optionalCategory.isEmpty()) {
            throw new CategoryNotFoundException("Category with the given ID is not found.");
        }
        Product createdProduct = new Product(requestProductDto.productName(), requestProductDto.description(), requestProductDto.price(), requestProductDto.imageUrl(), optionalCategory.get());
        return productRepository.save(createdProduct);
    }

    public Product updateProduct(Long id, RequestProductDto requestProductDto) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if(optionalProduct.isEmpty()) {
            throw new ProductNotFoundException("Product with ID " + id + " is not found.");
        }
        Product updatedProduct = optionalProduct.get();
        updatedProduct.setProductName(requestProductDto.productName());
        updatedProduct.setDescription(requestProductDto.description());
        updatedProduct.setPrice(requestProductDto.price());
        updatedProduct.setImageUrl(requestProductDto.imageUrl());
        return productRepository.save(updatedProduct);
    }

    public void deleteProduct(Long id) {
        if(productRepository.existsById(id)) {
            productRepository.deleteById(id);
        } else
            throw new ProductNotFoundException("Product with ID " + id + " is not found.");
    }
}
