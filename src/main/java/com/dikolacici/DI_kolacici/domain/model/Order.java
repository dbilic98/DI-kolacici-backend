package com.dikolacici.DI_kolacici.domain.model;

import com.dikolacici.DI_kolacici.domain.enumeration.OrderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "orders")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate orderDate;

    private OrderStatus status;

    private String note;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customerId;
}
