package com.dikolacici.DI_kolacici.controller.request;

import jakarta.validation.constraints.*;

public record RequestProductOrderDto(

        @NotBlank(message = "Quantity is mandatory")
        @Size(min = 1, max = 6, message = "Quantity should be at least 1 characters, and should not exceed 5 characters.")
        long quantity) {

}
