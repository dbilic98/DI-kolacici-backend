package com.dikolacici.DI_kolacici.controller.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseProductDto {

    private Long id;

    private String productName;

    private String description;

    private double price;

    private String imageType;

    private Long categoryId;

    private byte[] imageData;
}
