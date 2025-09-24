package com.dikolacici.DI_kolacici.controller.request;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.List;

public record RequestOrderDto (

        @Size(min = 4, max = 50, message = "Note should be at least 4 characters, and should not exceed 50 characters.")
        String note) {
}