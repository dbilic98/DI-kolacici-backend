package com.dikolacici.DI_kolacici.controller.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseOrderItemDto {

    private long quantity;

    private String productName;
}