package com.dikolacici.DI_kolacici.controller;

import com.dikolacici.DI_kolacici.controller.response.*;
import com.dikolacici.DI_kolacici.domain.model.Customer;
import com.dikolacici.DI_kolacici.domain.model.Order;
import com.dikolacici.DI_kolacici.domain.model.ProductOrder;
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
    private List<ResponseProductOrderDto> mapOrderItemsToResponseDtos(List<ProductOrder> orderItems) {
        List<ResponseProductOrderDto> responseProductOrderDtos = new ArrayList<>();

        for (ProductOrder orderItem : orderItems) {
            ResponseProductOrderDto responseProductOrderDto = new ResponseProductOrderDto(
                    orderItem.getQuantity(),
                    orderItem.getProduct().getProductName());
            responseProductOrderDtos.add(responseProductOrderDto);
        }
        return responseProductOrderDtos;
    }

    private ResponseOrderDto toResponseDto(Order order) {
        List<ResponseProductOrderDto> responseProductOrderDtos = mapOrderItemsToResponseDtos(order.getOrderItems());

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
                responseProductOrderDtos);
    }

    @GetMapping
    public PaginatedResponse<ResponseOrderDto> getOrders(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "10") int pageSize) {
        Page<Order> allOrders = orderService.getAllOrders(pageNumber, pageSize);
        Page<ResponseOrderDto> map = allOrders.map(this::toResponseDto);
        return new PaginatedResponse<>(map);
    }

    @GetMapping("/{id}")
    public ResponseOrderDto getOrderById(@PathVariable("id") Long id) {
        Order orderById = orderService.getOrderById(id);
        return toResponseDto(orderById);

    }
    @PutMapping("/{id}/accept")
    public ResponseOrderDto acceptOrder(@PathVariable("id") Long id) {
        orderService.acceptOrder(id);
        return toResponseDto(orderService.getOrderById(id));
    }

    @PutMapping("/{id}/reject")
    public ResponseOrderDto rejectOrder(@PathVariable("id") Long id) {
        orderService.rejectOrder(id);
        return toResponseDto(orderService.getOrderById(id));
    }
}
