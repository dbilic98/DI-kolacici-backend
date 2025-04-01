package com.dikolacici.DI_kolacici.domain.repository;

import com.dikolacici.DI_kolacici.domain.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
