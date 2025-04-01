package com.dikolacici.DI_kolacici.controller.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ResponseProductDto {

    private Long id;

    private String productName;

    private String description;

    private double price;

    private String imageUrl;

    private long categoryId;
}
