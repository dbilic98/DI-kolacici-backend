package com.dikolacici.DI_kolacici.controller.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record RequestCustomerDto(

        @NotBlank(message = "First name is mandatory")
        @Size(min = 3, max = 20, message = "First name should be at least 3 characters, and should not exceed 20 characters.")
        String firstName,

        @NotBlank(message = "Last name is mandatory")
        @Size(min = 3, max = 20, message = "Last name should be at least 3 characters, and should not exceed 20 characters.")
        String lastName,

        @NotBlank(message = "Email is mandatory")
        @Email(message = "Email must be valid and contain @")
        String email,

        @NotBlank(message = "Phone is mandatory")
        @Pattern(regexp = "^[0-9+\\-\\s]*$", message = "Phone can only contain numbers, spaces, + and -")
        String phone,

        @NotBlank(message = "Street is mandatory\"")
        String street,

        @NotBlank(message = "Street number is mandatory\"")
        String streetNumber,

        @NotBlank(message = "City is mandatory\"")
        String city
) {
}
