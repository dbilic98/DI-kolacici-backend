package com.dikolacici.DI_kolacici.controller;

import com.dikolacici.DI_kolacici.controller.response.PaginatedResponse;
import com.dikolacici.DI_kolacici.controller.response.ResponseOrderDto;
import com.dikolacici.DI_kolacici.domain.model.Order;
import com.dikolacici.DI_kolacici.domain.service.OrderService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public PaginatedResponse<ResponseOrderDto> getOrders(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "10") int pageSize) {
        Page<Order> allOrders = orderService.getAllOrders(pageNumber, pageSize);
        Page<ResponseOrderDto> map = allOrders.map(this::toResponseDto);
        return new PaginatedResponse<>(map);
    }

    public ResponseOrderDto toResponseDto(Order order) {
        return new ResponseOrderDto(
                order.getId(),
                order.getOrderDate(),
                order.getStatus(),
                order.getNote(),
                order.getCustomerId().getId()
        );
    }
}
