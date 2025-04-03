package com.dikolacici.DI_kolacici.controller.request;

import jakarta.validation.constraints.*;

public record RequestProductDto (

        @NotBlank(message = "Product name is mandatory")
        @Size(min = 1, max = 50, message = "Product name should be at least 1 characters, and should not exceed 50 characters.")
        String productName,

        @NotBlank(message = "Description is mandatory.")
        @Size(min = 1, max = 50, message = "Description should be at least 1 characters, and should not exceed 50 characters.")
        String description,

        @NotNull(message = "Price is mandatory")
        @DecimalMin(value = "0.0", message = "Price must be greater than or equal to 0.0")
        @DecimalMax(value = "100.0", message = "Price cannot exceed 100.0")
        double price,

        @NotNull(message = "Category ID is mandatory")
        Long categoryId) {
}
