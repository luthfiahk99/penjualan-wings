package com.indocyber.penjualan.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@Setter
@Entity
@Table(name = "Transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DocumentNumber")
    private Long DocumentNumber;

    @Column(name = "DocumentCode")
    private String documentCode;

    @ManyToOne
    @JoinColumn(name = "Username")
    private Account account;

    @Column(name = "Total")
    private BigDecimal total;

    @Column(name = "Date")
    private LocalDateTime date;

}
