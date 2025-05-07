package com.dikolacici.DI_kolacici.controller.request;

import jakarta.validation.constraints.*;

public record RequestOrderItemDto(

        @NotNull(message = "Quantity is mandatory")
        @Min(value = 1, message = "Quantity must be greater than or equal to 1")
        @Max(value = 6, message = "Quantity cannot exceed 6")
        Long quantity,

        Long orderId,

        Long productId) {
}