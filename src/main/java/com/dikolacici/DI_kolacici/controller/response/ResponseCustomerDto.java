package com.dikolacici.DI_kolacici.controller.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseCustomerDto {

    private String firstName;

    private String lastName;

    private String email;

    private String phone;

    private String street;

    private String streetNumber;

    private String city;

    private String country;
}