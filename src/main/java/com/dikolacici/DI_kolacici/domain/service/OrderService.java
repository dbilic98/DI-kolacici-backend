package com.dikolacici.DI_kolacici.domain.service;

import com.dikolacici.DI_kolacici.domain.model.Order;
import com.dikolacici.DI_kolacici.domain.repository.OrderItemRepository;
import com.dikolacici.DI_kolacici.domain.repository.OrderRepository;
import com.dikolacici.DI_kolacici.domain.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    private final ProductRepository productRepository;

    private final OrderItemRepository orderItemRepository;

    public OrderService(OrderRepository orderRepository, ProductRepository productRepository, OrderItemRepository orderItemRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.orderItemRepository = orderItemRepository;
    }

    public Page<Order> getAllOrders(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return orderRepository.findAll(pageable);
    }
}