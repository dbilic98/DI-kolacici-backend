package com.dikolacici.DI_kolacici.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "products")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String productName;

    private String description;

    @Column(name = "price", columnDefinition = "DECIMAL(5,2)")
    private double price;

    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] imageData;

    private String imageType;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category categoryId;
}
