package com.dikolacici.DI_kolacici.domain.repository;

import com.dikolacici.DI_kolacici.domain.model.ProductOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductOrderRepository extends JpaRepository<ProductOrder, Long> {
}