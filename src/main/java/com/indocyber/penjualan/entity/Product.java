package com.indocyber.penjualan.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@Setter
@Entity
@Table(name = "Products")
public class Product {

    @Id
    @Column(name = "ProductCode")
    private String productCode;

    @Column(name = "ProductName")
    private String productName;

    @Column(name = "Price")
    private BigDecimal price;

    @Column(name = "Currency")
    private String currency;

    @Column(name = "Discount")
    private Long discount;

    @Column(name = "Dimension")
    private String dimension;

    @Column(name = "Unit")
    private String unit;

    @ManyToMany
    @JoinTable(name = "Cart",
            joinColumns=@JoinColumn(name="ProductCode"),
            inverseJoinColumns=@JoinColumn(name="Username"))
    private List<Account> accountList;

    public Product(String productCode, String productName, BigDecimal price, String currency, Long discount, String dimension, String unit) {
        this.productCode = productCode;
        this.productName = productName;
        this.price = price;
        this.currency = currency;
        this.discount = discount;
        this.dimension = dimension;
        this.unit = unit;
    }
}
