package com.dikolacici.DI_kolacici.controller;

import com.dikolacici.DI_kolacici.controller.request.RequestProductOrderDto;
import com.dikolacici.DI_kolacici.controller.response.PaginatedResponse;
import com.dikolacici.DI_kolacici.controller.response.ResponseProductDto;
import com.dikolacici.DI_kolacici.domain.model.Product;
import com.dikolacici.DI_kolacici.domain.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public PaginatedResponse<ResponseProductDto> getProduct(
        @RequestParam(defaultValue = "0") int pageNumber,
        @RequestParam(defaultValue = "10") int pageSize) {
        Page<Product> allProducts = productService.getAllProducts(pageNumber, pageSize);
        Page<ResponseProductDto> map = allProducts.map(this::toResponseDto);
        return new PaginatedResponse<>(map);
    }

    private ResponseProductDto toResponseDto(Product product) {
        return new ResponseProductDto(
                product.getId(),
                product.getProductName(),
                product.getDescription(),
                product.getPrice(),
                product.getImageType(),
                product.getCategoryId().getId(),
                product.getImageData()
        );
    }

    @GetMapping("/{id}")
    public ResponseProductDto getProductById(@PathVariable Long id) {
        Product productById = productService.getProductById(id);
        return toResponseDto(productById);
    }
}
