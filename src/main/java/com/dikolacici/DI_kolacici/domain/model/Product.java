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

    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category categoryId;

    public Product(String productName, String description, double price, String imageUrl, Category categoryId) {
        this.productName = productName;
        this.description = description;
        this.price = price;
        this.imageUrl = imageUrl;
        this.categoryId = categoryId;
    }
}
