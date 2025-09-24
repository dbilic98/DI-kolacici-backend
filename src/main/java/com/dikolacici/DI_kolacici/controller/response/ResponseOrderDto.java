package com.dikolacici.DI_kolacici.controller.response;

import com.dikolacici.DI_kolacici.domain.enumeration.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseOrderDto {

    private Long id;

    private LocalDate orderDate;

    private OrderStatus status;

    private String note;

    private ResponseCustomerDto customer;

    private List<ResponseProductOrderDto> orderItemList;
}