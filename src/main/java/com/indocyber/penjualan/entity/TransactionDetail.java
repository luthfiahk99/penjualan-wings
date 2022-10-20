package com.indocyber.penjualan.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@Setter
@Entity
@Table(name = "TransactionDetails")
public class TransactionDetail {

    @EmbeddedId
    private TransactionDetailId transactionDetailId;

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;

    @ManyToOne
    @MapsId("productCode")
    @JoinColumn(name = "ProductCode")
    private Product product;

    @ManyToOne
    @MapsId("documentNumber")
    @JoinColumn(name = "DocumentNumber")
    private Transaction transaction;

    @Column(name = "Price")
    private BigDecimal price;

    @Column(name = "Quantity")
    private Long quantity;

    @Column(name = "Unit")
    private String unit;

    @Column(name = "Currency")
    private String currency;

    @Column(name = "SubTotal")
    private BigDecimal subTotal;

}
