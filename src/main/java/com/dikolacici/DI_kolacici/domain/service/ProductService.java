package com.dikolacici.DI_kolacici.domain.service;

import com.dikolacici.DI_kolacici.domain.model.Product;
import com.dikolacici.DI_kolacici.domain.repository.ProductRepository;
import com.dikolacici.DI_kolacici.exception.ProductNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;}

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
}
