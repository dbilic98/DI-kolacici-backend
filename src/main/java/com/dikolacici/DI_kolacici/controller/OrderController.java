package com.dikolacici.DI_kolacici.controller;

import com.dikolacici.DI_kolacici.controller.response.*;
import com.dikolacici.DI_kolacici.domain.model.Customer;
import com.dikolacici.DI_kolacici.domain.model.Order;
import com.dikolacici.DI_kolacici.domain.model.OrderItem;
import com.dikolacici.DI_kolacici.domain.service.OrderService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;


    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }
    private List<ResponseOrderItemDto> mapOrderItemsToResponseDtos(List<OrderItem> orderItems) {
        List<ResponseOrderItemDto> responseOrderItemDtos = new ArrayList<>();

        for (OrderItem orderItem : orderItems) {
            ResponseOrderItemDto responseOrderItemDto = new ResponseOrderItemDto(
                    orderItem.getQuantity(),
                    orderItem.getProduct().getProductName());
            responseOrderItemDtos.add(responseOrderItemDto);
        }
        return responseOrderItemDtos;
    }

    private ResponseOrderDto toResponseDto(Order order) {
        List<ResponseOrderItemDto> responseOrderItemDtos = mapOrderItemsToResponseDtos(order.getOrderItems());

        Customer customer = order.getCustomer();
        ResponseCustomerDto responseCustomerDto = new ResponseCustomerDto(
                customer.getFirstName(),
                customer.getLastName(),
                customer.getEmail(),
                customer.getPhone(),
                customer.getStreet(),
                customer.getStreetNumber(),
                customer.getCity(),
                customer.getCountry()
        );

        return new ResponseOrderDto(
                order.getId(),
                order.getOrderDate(),
                order.getStatus(),
                order.getNote(),
                responseCustomerDto,
                responseOrderItemDtos);
    }

    @GetMapping
    public PaginatedResponse<ResponseOrderDto> getOrders(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "10") int pageSize) {
        Page<Order> allOrders = orderService.getAllOrders(pageNumber, pageSize);
        Page<ResponseOrderDto> map = allOrders.map(this::toResponseDto);
        return new PaginatedResponse<>(map);
    }
}
