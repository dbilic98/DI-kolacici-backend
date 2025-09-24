package com.dikolacici.DI_kolacici.domain.service;

import com.dikolacici.DI_kolacici.domain.enumeration.OrderStatus;
import com.dikolacici.DI_kolacici.domain.model.Customer;
import com.dikolacici.DI_kolacici.domain.model.Order;
import com.dikolacici.DI_kolacici.domain.repository.OrderRepository;
import com.dikolacici.DI_kolacici.exception.OrderNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;
import java.util.Optional;

import static com.dikolacici.DI_kolacici.domain.enumeration.OrderStatus.PENDING;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    private final EmailService emailService;

    public OrderService(OrderRepository orderRepository, EmailService emailService) {
        this.orderRepository = orderRepository;
        this.emailService = emailService;
    }

    public Page<Order> getAllOrders(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return orderRepository.findAll(pageable);
    }

    public Order getOrderById(Long id) {
        Optional<Order> optionalOrder = orderRepository.findById(id);
        if (optionalOrder.isEmpty()) {
            throw new OrderNotFoundException("Order with id " + id + " not found");
        }
        return optionalOrder.get();
    }

    public Order createOrder(Customer customer) {
        Order createdOrder = new Order(LocalDateTime.now(), PENDING, customer);
        Order savedOrder = orderRepository.save(createdOrder);
        emailService.sendOrderNotificationToAdmin(savedOrder);
        return savedOrder;
    }


    public void acceptOrder(@PathVariable("id") Long id) {
       Optional<Order> optionalOrder = orderRepository.findById(id);
       if (optionalOrder.isEmpty()) {
           throw new OrderNotFoundException("Order with id " + id + " not found");
       }
       Order order = optionalOrder.get();
       order.setStatus(OrderStatus.ACCEPTED);
       orderRepository.save(order);
       emailService.sendOrderStatusEmail(order);
    }

    public void rejectOrder(@PathVariable("id") Long id) {
        Optional<Order> optionalOrder = orderRepository.findById(id);
        if (optionalOrder.isEmpty()) {
            throw new OrderNotFoundException("Order with id " + id + " not found");
        }
        Order order = optionalOrder.get();
        order.setStatus(OrderStatus.REJECTED);
        orderRepository.save(order);
        emailService.sendOrderStatusEmail(order);
    }
}