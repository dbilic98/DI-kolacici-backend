package com.dikolacici.DI_kolacici.controller;

import com.dikolacici.DI_kolacici.controller.request.RequestProductDto;
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

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseProductDto createProduct(@Valid @ModelAttribute RequestProductDto requestProductDto, @RequestParam("imageFile") MultipartFile imageFile) throws IOException {
        Product createdProduct = productService.createProduct(requestProductDto , imageFile);
        return toResponseDto(createdProduct);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseProductDto updateProduct(@PathVariable("id") Long id, @Valid @ModelAttribute RequestProductDto requestProductDto, @RequestParam(value = "imageFile", required = false) MultipartFile imageFile) throws IOException {
        Product updatedProduct = productService.updateProduct(id, requestProductDto, imageFile);
        return toResponseDto(updatedProduct);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable("id") Long id) {
        productService.deleteProduct(id);
    }
}
