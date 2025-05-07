package com.dikolacici.DI_kolacici.controller.request;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.List;

public record RequestOrderDto (

        @NotNull(message = "Order date is mandatory") @FutureOrPresent(message = "The date must be today or in the future")
        LocalDate orderDate,

        @Size(min = 4, max = 50, message = "Note should be at least 4 characters, and should not exceed 50 characters.")
        String note,

        Long customerId,

        List<RequestOrderItemDto> orderItemList) {
}