package com.dikolacici.DI_kolacici.domain.repository;

import com.dikolacici.DI_kolacici.domain.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}