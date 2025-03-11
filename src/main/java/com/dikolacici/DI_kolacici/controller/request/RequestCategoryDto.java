package com.dikolacici.DI_kolacici.controller.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RequestCategoryDto(

        @NotBlank(message = "Category name is mandatory")
        @Size(min = 1, max = 50, message = "Category name should be at least 1 characters, and should not exceed 50 characters.")
        String categoryName) {
}
